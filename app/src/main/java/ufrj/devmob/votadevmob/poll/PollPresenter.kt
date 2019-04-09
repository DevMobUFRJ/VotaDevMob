package ufrj.devmob.votadevmob.poll

import ufrj.devmob.votadevmob.Callback
import ufrj.devmob.votadevmob.model.Poll
import java.lang.Exception

class PollPresenter(val view: PollContract.View, private val currentPoll: Poll) : PollContract.Presenter {

    private val model = PollModel()

    init {
        view.setupPollLayout(currentPoll.title ?: "", currentPoll.optionsList?: emptyList())
    }

    override fun registerVote(chosenOption: String) {
        view.showLoading()
        model.vote(currentPoll.id ?: 0, chosenOption, object : Callback {
            override fun onSuccess() {
                view.hideLoading()
                view.showToastSuccess()
            }
            override fun onError(exception: Exception) {
                view.hideLoading()
                view.showToastError(exception.toString())
            }
        })
    }
}