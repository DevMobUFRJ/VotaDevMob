package ufrj.devmob.votadevmob.main

import android.support.v7.app.AppCompatActivity
import ufrj.devmob.votadevmob.core.model.Poll

interface MainContract {

    interface View {
        fun goToActivity(activity: Class<out AppCompatActivity>, poll: Poll)
        fun showLoading()
        fun hideLoading()
        fun showToastError(errorMessage: String)
    }

    interface Presenter {
        fun getPollForActivity(id: String, password: String, activity: Class<out AppCompatActivity>)
    }

}