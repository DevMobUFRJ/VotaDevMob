package ufrj.devmob.votadevmob.newpoll.view
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_new_poll.*
import ufrj.devmob.votadevmob.R

class NewPollActivity : AppCompatActivity(), NewPollView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_poll)

        val options = mutableSetOf<String>()
        button_addOption.setOnClickListener {
            options.add(field_pollOption.text.toString())
            field_pollOption.text.clear()
            text_options.text = options.joinToString(separator = System.getProperty("line.separator"))
        }
    }
    override fun navigateToVoting() {
        val isMultiple = toggle_answers.isChecked
        // TODO Start voting activity
    }
}
