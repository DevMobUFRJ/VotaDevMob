package ufrj.devmob.votadevmob.newpoll.view
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_poll.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.newpoll.presenter.NewPollPresenterImpl

class NewPollActivity : AppCompatActivity(), NewPollView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_poll)
    }

    private fun EditText.fieldToString() = this.text.toString()
    private fun EditText.isBlank() = this.text.toString().isBlank()
    private fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private val options = mutableMapOf<String, Int>()
//    button_addOption.setOnClickListener {}

    override fun addOption(v: View){
        if (field_pollOption.isBlank()) {
            applicationContext.toast("Digite uma opção")
            return
        }

        options.put(field_pollOption.fieldToString(), 0)
        field_pollOption.text.clear()
        text_options.text = options.keys.joinToString(separator = System.getProperty("line.separator"))
    }

    override fun requestNewPoll(v: View){
        if (field_pollTitle.isBlank()) {
            applicationContext.toast("Digite um título")
            return
        }
        if (options.size < 2) {
            applicationContext.toast("A votação deve ter duas ou mais opções")
            return
        }

        val title = field_pollTitle.fieldToString()
        val password = field_pollPassword.fieldToString()

        val newPollPresenterImpl = NewPollPresenterImpl()
        newPollPresenterImpl.mountPollDocument(title, password, options)
        navigateToVoting()
    }

    override fun navigateToVoting() {
        val isMultiple = toggle_answers.isChecked
        Log.i("Called", "to vote")
        // TODO Start voting activity
        // val intent = Intent(this, VotingActivity::class.java)
        // startActivity(intent)
    }
}
