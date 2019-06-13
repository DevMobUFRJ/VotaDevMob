package ufrj.devmob.votadevmob.result

interface PollResultContract {

    interface View {
        fun showResult(result: Map<String, Int>)
        fun showLoading()
        fun hideLoading()
        fun showError(errorMessage: String)
        fun showMajorErrorMessage()
    }

    interface Presenter {
        fun getPollResult()
    }
}