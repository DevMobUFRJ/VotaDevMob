package ufrj.devmob.votadevmob.newpoll.view

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import ufrj.devmob.votadevmob.R

class ActionModeCallback: ActionMode.Callback {

    private var mode: ActionMode? = null

    fun startActionMode(view: View) {
        view.startActionMode(this)
    }

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        this.mode = mode
        mode.menuInflater.inflate(R.menu.context_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_options -> {
                NewPollActivity().deleteSelectedOptions()
                mode?.finish()
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        this.mode = null
    }

    fun finishActionMode() {
        mode?.finish()
    }
}