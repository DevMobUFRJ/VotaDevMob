package ufrj.devmob.votadevmob.newpoll.view
import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_poll.*
import kotlinx.android.synthetic.main.card_poll_option.view.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.newpoll.presenter.NewPollPresenterImpl

class NewPollActivity : AppCompatActivity(), NewPollView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_poll)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        options_recyclerView.layoutManager = GridLayoutManager(this.applicationContext, 2)
        options_recyclerView.adapter = PollOptionAdapter(newPollPresenter.options, { optionItem: View -> optionItemSelected(optionItem) }, applicationContext)
        initListeners()
    }

    private val selectedOptions = mutableSetOf<View>()
    private val newPollPresenter = NewPollPresenterImpl()
    private val actionModeCallback = ActionModeCallback(this)

    // Extension functions
    private fun EditText.fieldToString() = this.text.toString()
    private fun EditText.isBlank() = this.text.toString().isBlank()
    private fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

    private fun addOption() {
        if (field_pollOption.isBlank()) {
            applicationContext.toast("Digite uma opção")
        } else {
            val option = field_pollOption.fieldToString()
            newPollPresenter.addOptionToMap(option)
            field_pollOption.text?.clear()
            applicationContext.hideKeyboard(button_addOption)
        }
    }

    fun deleteSelectedOptions() {
        Log.i("deletado", selectedOptions.toString())
        for (option in selectedOptions) {
            newPollPresenter.options.remove(option.text_card_poll.text.toString())
            option.isSelected = false
            option.setBackgroundResource(R.drawable.unselected_view)
        }
        selectedOptions.clear()
        options_recyclerView.adapter?.notifyDataSetChanged()
    }

    fun unselectAllOptions() {
        Log.i("deselecionar",selectedOptions.size.toString())
        for (option in selectedOptions) {
            option.isSelected = false
            option.setBackgroundResource(R.drawable.unselected_view)
        }
        selectedOptions.clear()
    }

    private fun createPoll() {
        val options = newPollPresenter.options
        when {
            field_pollTitle.isBlank() -> applicationContext.toast("Digite um título")
            options.size < 2 -> applicationContext.toast("A votação deve ter duas ou mais opções")

            else -> {
                val title = field_pollTitle.fieldToString()
                val password = field_pollPassword.fieldToString()

                newPollPresenter.mountPollDocument(password, title, options)
                navigateToVoting()
            }
        }
    }

    private fun sendOption(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            addOption()
            return true
        }
        return false
    }

    private fun initListeners(){
        button_addOption.setOnClickListener { addOption() }
        button_createPoll.setOnClickListener { createPoll() }
        field_pollOption.setOnEditorActionListener { v, actionId, event -> sendOption(v, actionId, event) }
    }

    override fun navigateToVoting() {
        //val isMultiple = toggle_answers.isChecked
        Log.i("Called", "to vote")
        // TODO Start voting activity
        // val intent = Intent(this, VotingActivity::class.java)
        // startActivity(intent)
    }
}