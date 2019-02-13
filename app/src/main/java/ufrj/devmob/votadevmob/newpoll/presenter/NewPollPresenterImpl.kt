package ufrj.devmob.votadevmob.newpoll.presenter

import ufrj.devmob.votadevmob.core.entities.PollDocument
import ufrj.devmob.votadevmob.newpoll.model.NewPollModel

class NewPollPresenterImpl : NewPollPresenter{

    val options = mutableMapOf<String,Int>()

    override fun addOptionToMap(option: String): String{
        options.put(option, 0)

        return options.keys.joinToString(separator = System.getProperty("line.separator"))
    }

    override fun mountPollDocument(title: String, password: String, options: Map<String, Int>){

        val poll = PollDocument(title, password, options)

        val newPollModel = NewPollModel()
        newPollModel.createNewPoll(poll)
        //newPollModel.createNewPollWithId("idunico", poll)
    }
}