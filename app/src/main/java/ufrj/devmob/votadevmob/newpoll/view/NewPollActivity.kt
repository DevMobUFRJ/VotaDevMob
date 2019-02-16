package ufrj.devmob.votadevmob.newpoll.view
import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_poll.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.newpoll.presenter.NewPollPresenterImpl
import ufrj.devmob.votadevmob.newpoll.presenter.adapter.PollOptionAdapter

class NewPollActivity : AppCompatActivity(), NewPollView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_poll)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        options_recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)
        options_recyclerView.adapter = PollOptionAdapter(newPollPresenter.options, { optionItem: View -> optionItemSelected(optionItem) }, applicationContext)
        initListeners()
    }

    private val newPollPresenter = NewPollPresenterImpl()

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
                item.setBackgroundResource(R.drawable.selected_view)
                item.isSelected = true
            }
            true -> {
                item.setBackgroundResource(R.drawable.unselected_view)
                item.isSelected = false
            }
        }
    }

    private fun initListeners(){
        button_addOption.setOnClickListener {
            if (field_pollOption.isBlank()) {
                applicationContext.toast("Digite uma opção")
            } else {
                val option = field_pollOption.fieldToString()
                newPollPresenter.addOptionToMap(option)
                field_pollOption.text.clear()
                applicationContext.hideKeyboard(button_addOption)
            }
        }

        button_createPoll.setOnClickListener {
            Log.i("view showing: ", options_recyclerView.layoutManager?.childCount.toString())
            val options = newPollPresenter.options
            if (field_pollTitle.isBlank()) {
                applicationContext.toast("Digite um título")
            } else if (options.size < 2) {
                applicationContext.toast("A votação deve ter duas ou mais opções")
            } else {
                val title = field_pollTitle.fieldToString()
                val password = field_pollPassword.fieldToString()

                val newPollPresenterImpl = NewPollPresenterImpl()
                newPollPresenterImpl.mountPollDocument(title, password, options)
                navigateToVoting()
            }
        }
    }

    // TODO Delete option

    override fun navigateToVoting() {
        val isMultiple = toggle_answers.isChecked
        Log.i("Called", "to vote")
        // TODO Start voting activity
        // val intent = Intent(this, VotingActivity::class.java)
        // startActivity(intent)
    }
}
