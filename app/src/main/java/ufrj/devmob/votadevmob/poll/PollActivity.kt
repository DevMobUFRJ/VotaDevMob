package ufrj.devmob.votadevmob.poll

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poll.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.core.model.Poll

class PollActivity : AppCompatActivity(), PollContract.View {

    internal lateinit var presenter: PollContract.Presenter

    internal var selectedRadioButton : RadioButton? = null
        set(value) {
            field = value
            pollVoteButton.run { if (!isEnabled) isEnabled = true }
        }

    companion object {
        const val POLL_KEY = "current_poll"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll)

        val poll = intent?.extras?.get(POLL_KEY) as Poll?
        if (poll == null) showMajorErrorMessage() else presenter = PollPresenter(this, poll)

        setListeners()
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
