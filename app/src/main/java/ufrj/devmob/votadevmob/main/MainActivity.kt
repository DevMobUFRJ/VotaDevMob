package ufrj.devmob.votadevmob.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_main_input.view.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.core.model.Poll
import ufrj.devmob.votadevmob.newpoll.NewPollActivity
import ufrj.devmob.votadevmob.poll.PollActivity
import ufrj.devmob.votadevmob.result.PollResultActivity

class MainActivity : AppCompatActivity(), MainContract.View {

    internal lateinit var presenter: MainContract.Presenter

    private var currentId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        createPollButton.setOnClickListener { startActivity(Intent(this, NewPollActivity::class.java)) }
        voteButton.setOnClickListener { showInputDialog(PollActivity::class.java) }
        resultPollButton.setOnClickListener { showInputDialog(PollResultActivity::class.java) }
    }

    override fun showInputDialog(activity: Class<out AppCompatActivity>, withId: Boolean) {
        val view = layoutInflater.inflate(R.layout.dialog_main_input, null)
        AlertDialog.Builder(this).run {
            setView(view)
            setTitle(getString(R.string.main_dialog_title))
            setPositiveButton(getString(android.R.string.ok)) { _, _ ->
                currentId = view.idEditText.text.toString()
                presenter.getPollForActivity(id = currentId,
                    password = view.passwordEditText.text.toString(),
                    activity = activity)
            }
            setNegativeButton(getString(android.R.string.cancel)) { _, _ -> }
            show()
        }
        if (withId) view.idInput.editText?.setText(currentId)
    }

    override fun goToActivity(activity: Class<out AppCompatActivity>, poll: Poll) {
        startActivity(Intent(this, activity).putExtra(getString(R.string.poll_intent_key), poll))
    }

    override fun showLoading() {
        mainContent.visibility = View.GONE
        mainLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mainContent.visibility = View.VISIBLE
        mainLoading.visibility = View.GONE
    }

    override fun showToastError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showIncorrectPasswordError() {
        showToastError(getString(R.string.main_error_password))
    }
}
