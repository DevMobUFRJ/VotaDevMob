package ufrj.devmob.votadevmob.result

import ufrj.devmob.votadevmob.core.callback.Callback
import ufrj.devmob.votadevmob.core.model.Poll
import java.lang.Exception

class PollResultPresenter(val view: PollResultContract.View, private val currentPoll: Poll): PollResultContract.Presenter {

    internal val model by lazy { PollResultModel() }
    internal val currentResult = mutableMapOf<String, Int>()

    init {
        view.showLoading()
        getPollResult()
    }

    override fun getPollResult() {
        for (option in currentPoll.optionsList ?: emptyList()) {

            model.getOptionNumberOfVotes(pollId = currentPoll.id ?: 0,
                                        currentOption = option,
                                        callback = object : Callback<Int> {
                    override fun onSuccess(result: Int) {
                        currentResult[option] = result
                        view.hideLoading()
                        view.showResult(currentResult)
                    }

                    override fun onError(exception: Exception) {
                        view.showError(exception.toString())
                    }
                })
        }
    }
}