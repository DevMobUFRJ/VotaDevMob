package ufrj.devmob.votadevmob.main

interface MainContract {

    interface View {
        fun setupPollLayout(title: String, options: List<String>)
        fun showLoading()
        fun hideLoading()
        fun showToastSuccess()
        fun showToastError(errorMessage: String)
        fun showMajorErrorMessage()
    }

    interface Presenter {
        fun getPoll(id: String, password: String)
    }

}