package ufrj.devmob.votadevmob.main

import android.support.v7.app.AppCompatActivity
import ufrj.devmob.votadevmob.core.callback.Callback
import ufrj.devmob.votadevmob.core.model.Poll
import java.lang.Exception

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    internal val model by lazy { MainModel() }

    override fun getPollForActivity(id: String, password: String, activity: Class<out AppCompatActivity>) {
        view.showLoading()
        model.getPoll(pollId = id, callback = object : Callback<Poll> {
            override fun onSuccess(result: Poll) {
                view.hideLoading()
                if (result.password == password)
                    view.goToActivity(activity, result)
                else
                    view.showIncorrectPasswordError()
            }
            override fun onError(exception: Exception) {
                view.hideLoading()
                view.showToastError(exception.message.toString())
            }
        })
    }
}