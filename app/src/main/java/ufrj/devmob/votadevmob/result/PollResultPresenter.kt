package ufrj.devmob.votadevmob.result

import ufrj.devmob.votadevmob.Callback
import ufrj.devmob.votadevmob.core.model.Poll
import java.lang.Exception

class PollResultPresenter(val view: PollResultContract.View, private val currentPoll: Poll): PollResultContract.Presenter {

    internal val model = PollResultModel()
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
//                        if (hasAllResults()) {
                            view.hideLoading()
                            view.showResult(currentResult)
//                        }
                    }

                    override fun onError(exception: Exception) {
                        view.showError(exception.toString())
                    }
                })
        }
    }

//    private fun hasAllResults() = currentResult.size == currentPoll.optionsList?.size
}