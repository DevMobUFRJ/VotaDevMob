package ufrj.devmob.votadevmob.newpoll.view

import android.view.View

interface NewPollView {
    fun addOption(v: View)
    fun requestNewPoll(v: View)
    fun navigateToVoting()
}