package ufrj.devmob.votadevmob.poll

interface PollContract {

    interface View {
        fun setupPollLayout(title: String, options: List<String>)
        fun showLoading()
        fun hideLoading()
        fun showToastSuccess()
        fun showToastError(errorMessage: String)
        fun showMajorErrorMessage()
    }

    interface Presenter {
        fun registerVote(chosenOption: String)
    }
}