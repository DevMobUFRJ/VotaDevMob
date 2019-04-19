package ufrj.devmob.votadevmob.core.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ufrj.devmob.votadevmob.core.model.Poll

abstract class BasePollingActivity: AppCompatActivity() {

    companion object {
        const val POLL_KEY = "current_poll"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val poll = intent?.extras?.get(POLL_KEY) as Poll?
        if (poll == null) showMajorErrorMessage() else createPresenter(poll)
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

    open fun showMajorErrorMessage() {}
    open fun createPresenter(poll: Poll) {}
}