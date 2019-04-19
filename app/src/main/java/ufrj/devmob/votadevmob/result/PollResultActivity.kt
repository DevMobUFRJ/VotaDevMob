package ufrj.devmob.votadevmob.result

import android.os.Bundle
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.core.activities.BasePollingActivity
import ufrj.devmob.votadevmob.core.model.Poll

class PollResultActivity : BasePollingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll_result)
    }

    override fun createPresenter(poll: Poll) {

    }

    override fun showMajorErrorMessage() {

    }
}
