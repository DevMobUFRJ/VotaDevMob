package ufrj.devmob.votadevmob.poll

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_poll.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.model.Poll

class PollActivity : AppCompatActivity(), PollContract.View {

    internal lateinit var presenter: PollContract.Presenter

    internal var selectedRadioButton : RadioButton? = null

    companion object {
        const val POLL_KEY = "current_poll"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll)

        val poll = intent?.extras?.get(POLL_KEY) as Poll?
                ?: Poll(
                    id = 123456789,
                    password = "senha",
                    title = "Me vota",
                    optionsList = listOf(
                        "sim",
                        "nao",
                        "talvez"
                    )
                )

        presenter = PollPresenter(this, poll)

        pollRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            selectedRadioButton = findViewById(checkedId)
        }

        pollVoteButton.setOnClickListener {
            presenter.registerVote(selectedRadioButton?.text.toString())
        }
    }

    override fun showLoading() {
        pollLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pollLoading.visibility = View.GONE
    }

    override fun showToastSuccess() {
        // ir para a tela de resultado
        Toast.makeText(this, "foi", Toast.LENGTH_SHORT).show()
    }

    override fun showToastError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun setupPollLayout(title: String, options: List<String>) {
        pollTitle.text = title

        for (option in options) {
            val radioButton = getConfiguredRadioButton(option)
            pollRadioGroup.addView(radioButton)
        }
    }

    internal fun getConfiguredRadioButton(radioButtonText: String) : RadioButton {
        return RadioButton(this).apply {
            text = radioButtonText
            id = View.generateViewId()
            setPadding(10, 5, 10, 5)
            textSize = 20f
        }
    }
}
