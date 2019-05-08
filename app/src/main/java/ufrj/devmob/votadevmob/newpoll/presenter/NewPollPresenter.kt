package ufrj.devmob.votadevmob.newpoll.presenter

interface NewPollPresenter {
    fun addOptionToMap(option: String)
    fun mountPollDocument(password: String, title: String, options: List<String>)
}