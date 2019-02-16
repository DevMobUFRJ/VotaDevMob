package ufrj.devmob.votadevmob.newpoll.presenter

interface NewPollPresenter {
    fun addOptionToMap(option: String)
    fun mountPollDocument(title: String, password: String, options: Map<String, Int>)
}