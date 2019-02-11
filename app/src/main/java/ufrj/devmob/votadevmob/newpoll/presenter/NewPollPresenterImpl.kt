package ufrj.devmob.votadevmob.newpoll.presenter

import ufrj.devmob.votadevmob.core.entities.PollDocument
import ufrj.devmob.votadevmob.newpoll.model.NewPollModel

class NewPollPresenterImpl {

    fun mountPollDocument(title: String, password: String, options: Map<String, Int>){

        val poll = PollDocument(title, password, options)

        val newPollModel = NewPollModel()
        newPollModel.createNewPoll(poll)
        //newPollModel.createNewPollWithId("idunico", poll)
    }
}