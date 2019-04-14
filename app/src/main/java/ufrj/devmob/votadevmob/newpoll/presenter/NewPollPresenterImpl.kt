package ufrj.devmob.votadevmob.newpoll.presenter

import ufrj.devmob.votadevmob.core.entities.PollDocument
import ufrj.devmob.votadevmob.newpoll.model.NewPollModel

class NewPollPresenterImpl : NewPollPresenter{

    val options = mutableListOf<String>()

    override fun addOptionToMap(option: String){
        options.add(option)
    }

    override fun mountPollDocument(password: String, title: String, options: List<String>){

        val poll = PollDocument(null, password, title, options)

        val newPollModel = NewPollModel()
        newPollModel.createNewPoll(poll)
        //newPollModel.createNewPollWithId("idunico", poll)
    }
}