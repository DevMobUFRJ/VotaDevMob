package ufrj.devmob.votadevmob.newpoll

import android.support.v7.app.AppCompatActivity
import ufrj.devmob.votadevmob.core.model.Poll

interface NewPollContract {

    interface View {
        fun createPoll()
        fun goToVoteActivity(activity: Class<out AppCompatActivity>, poll: Poll)
        fun showToast(errorMessage: String)
        fun showMajorErrorMessage()
        fun hideCreateButton()
    }

    interface Presenter {
        val options: MutableList<String>
        fun addOptionToMap(option: String)
        fun mountPollDocument(id: Int, password: String, title: String, options: List<String>)
    }
}