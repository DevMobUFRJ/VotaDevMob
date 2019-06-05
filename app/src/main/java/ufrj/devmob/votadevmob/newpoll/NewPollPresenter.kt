package ufrj.devmob.votadevmob.newpoll

import ufrj.devmob.votadevmob.core.callback.Callback
import ufrj.devmob.votadevmob.core.model.Poll
import java.lang.Exception

class NewPollPresenter(val view: NewPollContract.View) : NewPollContract.Presenter {

    internal val model by lazy { NewPollModel() }
    override val options = mutableListOf<String>()

    override fun addOptionToMap(option: String){
        options.add(option)
    }

    override fun mountPollDocument(id: Int, password: String, title: String, options: List<String>){

        val poll = Poll(id, password, title, options)

        model.createNewPoll(poll = poll,  callback = object : Callback<Poll> {
            override fun onSuccess(result: Poll) {
                view.hideCreateButton()
                view.showToast("Votação criada com sucesso!")
            }

            override fun onError(exception: Exception) {
                view.showToast(exception.toString())
            }
        })
    }
}