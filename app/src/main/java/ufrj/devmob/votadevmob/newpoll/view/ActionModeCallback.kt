package ufrj.devmob.votadevmob.newpoll.view

import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import ufrj.devmob.votadevmob.R

class ActionModeCallback(private val newPollActivity: NewPollActivity): ActionMode.Callback {

    private var mode: ActionMode? = null

    fun startActionMode(view: View) {
        view.startActionMode(this)
    }

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        Log.i("Action mode state", "Created")
        this.mode = mode
        mode.menuInflater.inflate(R.menu.context_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        Log.i("Action mode state", "Prepared")
        return false
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        Log.i("Action mode state", "Item clicked")
        return when (item?.itemId) {
            R.id.delete_options -> {
                newPollActivity.deleteSelectedOptions()
                mode?.finish()
                true
            }
            else -> false
        }
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        Log.i("Action mode state", "Destroy")
        newPollActivity.unselectAllOptions()
        this.mode = null
    }

    fun finishActionMode() {
        Log.i("Action mode state", "Finish")
        mode?.finish()
    }
}