package ufrj.devmob.votadevmob.poll

import ufrj.devmob.votadevmob.core.model.Poll

interface PollContract {

    interface View {
        fun setupPollLayout(title: String, options: List<String>)
        fun showLoading()
        fun hideLoading()
        fun showToastError(errorMessage: String)
        fun showMajorErrorMessage()
        fun goToResultActivity(poll: Poll)
    }

    interface Presenter {
        fun registerVote(chosenOption: String)
    }
}