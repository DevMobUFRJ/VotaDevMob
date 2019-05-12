package ufrj.devmob.votadevmob.newpoll

import ufrj.devmob.votadevmob.core.model.Poll

interface NewPollContract {

    interface View {
        fun createPoll()
        fun goToVoteActivity(poll: Poll)
        fun showToastError(errorMessage: String)
        fun showMajorErrorMessage()
    }

    interface Presenter {
        val options: MutableList<String>
        fun addOptionToMap(option: String)
        fun mountPollDocument(id: Int, password: String, title: String, options: List<String>)
    }
}