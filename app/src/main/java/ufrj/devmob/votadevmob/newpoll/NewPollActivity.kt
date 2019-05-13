package ufrj.devmob.votadevmob.newpoll

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_poll.*
import kotlinx.android.synthetic.main.activity_new_poll.pollMajorErrorMessage
import kotlinx.android.synthetic.main.card_poll_option.view.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.core.model.Poll
import ufrj.devmob.votadevmob.poll.PollActivity

class NewPollActivity : AppCompatActivity(), NewPollContract.View {

    internal lateinit var presenter: NewPollContract.Presenter
    internal lateinit var poll: Poll

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_poll)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        presenter = NewPollPresenter(this)

        options_recyclerView.layoutManager = GridLayoutManager(this.applicationContext, 2)
        options_recyclerView.adapter = PollOptionAdapter(
            presenter.options,
            { optionItem: View -> optionItemSelected(optionItem) },
            applicationContext
        )
        setListeners()
    }

    private fun setListeners(){
        button_addOption.setOnClickListener { addOption() }
        button_createPoll.setOnClickListener { createPoll() }
        field_pollOption.setOnEditorActionListener { v, actionId, event -> sendOption(v, actionId, event) }
        button_vote.setOnClickListener { goToVoteActivity(PollActivity::class.java, poll) }
        button_backMain.setOnClickListener { this.finish() }
    }

    private val selectedOptions = mutableSetOf<View>()
    private val actionModeCallback = ActionModeCallback(this)

    // Extension functions
    private fun EditText.fieldToString() = this.text.toString()
    private fun EditText.isBlank() = this.text.toString().isBlank()
    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun optionItemSelected(item: View) {
        when (item.isSelected) {
            false -> {
                item.isSelected = true
                item.setBackgroundResource(R.drawable.selected_view)
                selectedOptions.add(item)
                if (selectedOptions.size == 1) actionModeCallback.startActionMode(item)
            }
            true -> {
                item.isSelected = false
                item.setBackgroundResource(R.drawable.unselected_view)
                selectedOptions.remove(item)
            }
        }

        if (selectedOptions.isEmpty()) {
            actionModeCallback.finishActionMode()
        }
    }

    fun addOption() {
        if (field_pollOption.isBlank()) {
            showToast("Digite uma opção")
        } else {
            val option = field_pollOption.fieldToString()
            presenter.addOptionToMap(option)
            field_pollOption.text?.clear()
            applicationContext.hideKeyboard(button_addOption)
        }
    }

    fun deleteSelectedOptions() {
        for (option in selectedOptions) {
            presenter.options.remove(option.text_card_poll.text.toString())
            option.isSelected = false
            option.setBackgroundResource(R.drawable.unselected_view)
        }
        selectedOptions.clear()
        options_recyclerView.adapter?.notifyDataSetChanged()
    }

    fun unselectAllOptions() {
        for (option in selectedOptions) {
            option.isSelected = false
            option.setBackgroundResource(R.drawable.unselected_view)
        }
        selectedOptions.clear()
    }

    private fun sendOption(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            addOption()
            return true
        }
        return false
    }

    override fun createPoll() {
        val options = presenter.options
        when {
            field_pollTitle.isBlank() -> showToast("Digite um título")
            options.size < 2 -> showToast("A votação deve ter duas ou mais opções")

            else -> {
                val title = field_pollTitle.fieldToString()
                val password = field_pollPassword.fieldToString()

                val id = (1..7).map { kotlin.random.Random.nextInt(10) }.joinToString("").toInt()
                presenter.mountPollDocument(id, password, title, options)
                text_pollKey.text = id.toString()
                poll = Poll(id = id, password = password, title = title, optionsList = options)
            }
        }
    }

    override fun goToVoteActivity(activity: Class<out AppCompatActivity>, poll: Poll) {
        startActivity(
            Intent(this, activity)
            .putExtra(getString(R.string.poll_intent_key), poll))
        finish()
    }

    override fun hideCreateButton() {
        button_createPoll.visibility = View.GONE
        linear_voteId.visibility = View.VISIBLE
        linear_backVote.visibility = View.VISIBLE
    }

    override fun showMajorErrorMessage() {
        text_pollKey.visibility = View.GONE
        field_pollTitle.visibility = View.GONE
        field_pollPassword.visibility = View.GONE
        field_pollOption.visibility = View.GONE
        button_addOption.visibility = View.GONE
        button_createPoll.visibility = View.GONE
        options_recyclerView.visibility = View.GONE
        this.pollMajorErrorMessage.visibility = View.VISIBLE
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}