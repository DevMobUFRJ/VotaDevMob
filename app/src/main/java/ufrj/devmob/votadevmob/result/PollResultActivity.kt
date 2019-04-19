package ufrj.devmob.votadevmob.result

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poll_result.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.core.model.Poll

class PollResultActivity : AppCompatActivity(), PollResultContract.View {

    internal lateinit var presenter: PollResultContract.Presenter

    companion object {
        const val POLL_KEY = "current_poll"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val poll = intent?.extras?.get(POLL_KEY) as Poll?
        val poll = Poll(id = 123456789, optionsList = listOf("sim", "nao", "talvez"))

        if (poll == null) showMajorErrorMessage()
        else presenter = PollResultPresenter(this, poll)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showResult(result: Map<String, Int>) {
        resulttxt.text = result.toString()
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showMajorErrorMessage() {
        Toast.makeText(this, "Major error", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        resulttxt.visibility = View.GONE
        pollResultLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        resulttxt.visibility = View.VISIBLE
        pollResultLoading.visibility = View.GONE
    }
}
