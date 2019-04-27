package ufrj.devmob.votadevmob.main

import ufrj.devmob.votadevmob.Callback
import java.lang.Exception

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    internal val model by lazy { MainModel() }

    override fun getPoll(id: String, password: String) {
        model.getPoll(pollId = id, password = password, callback = object : Callback {
            override fun onSuccess() {

            }
            override fun onError(exception: Exception) {

            }
        })
    }
}