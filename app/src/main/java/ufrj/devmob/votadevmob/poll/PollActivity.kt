package ufrj.devmob.votadevmob.poll

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poll.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.core.activities.BasePollingActivity
import ufrj.devmob.votadevmob.core.model.Poll

class PollActivity : BasePollingActivity(), PollContract.View {

    internal lateinit var presenter: PollContract.Presenter

    internal var selectedRadioButton : RadioButton? = null
        set(value) {
            field = value
            pollVoteButton.run { if (!isEnabled) isEnabled = true }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll)

        setListeners()
    }

    override fun createPresenter(poll: Poll) {
        presenter = PollPresenter(this, poll)
    }

    private fun setListeners() {
        pollRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedRadioButton = findViewById(checkedId)
        }

        pollVoteButton.setOnClickListener {
            if (selectedRadioButton == null) showToastError(getString(R.string.poll_error_no_option_selected))
            else presenter.registerVote(selectedRadioButton?.text.toString())
        }
    }

    override fun setupPollLayout(title: String, options: List<String>) {
        pollTitle.text = if (title.isNotEmpty()) title else getString(R.string.poll_empty_title)

        for (option in options) {
            if (option.isNotEmpty()) {
                val radioButton = getConfiguredRadioButton(option)
                pollRadioGroup.addView(radioButton)
            }
        }
    }

    internal fun getConfiguredRadioButton(radioButtonText: String) : RadioButton {
        return RadioButton(this).apply {
            text = radioButtonText
            id = View.generateViewId()
            resources.run {
                setPadding(getDimensionPixelSize(R.dimen.poll_option_padding_start),
                    getDimensionPixelSize(R.dimen.poll_option_padding_top),
                    getDimensionPixelSize(R.dimen.poll_option_padding_start),
                    getDimensionPixelSize(R.dimen.poll_option_padding_top))
                textSize = getDimension(R.dimen.poll_option_text_size)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showToastSuccess() {
        // ir para a tela de resultado
        Toast.makeText(this, "foi", Toast.LENGTH_SHORT).show()
    }

    override fun showToastError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showMajorErrorMessage() {
        pollTitle.visibility = View.GONE
        pollRadioGroup.visibility = View.GONE
        pollVoteButton.visibility = View.GONE
        pollLoading.visibility = View.GONE
        pollMajorErrorMessage.visibility = View.VISIBLE
    }

    override fun showLoading() {
        pollTitle.visibility = View.GONE
        pollRadioGroup.visibility = View.GONE
        pollVoteButton.visibility = View.GONE
        pollLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pollTitle.visibility = View.VISIBLE
        pollRadioGroup.visibility = View.VISIBLE
        pollVoteButton.visibility = View.VISIBLE
        pollLoading.visibility = View.GONE
    }
}
