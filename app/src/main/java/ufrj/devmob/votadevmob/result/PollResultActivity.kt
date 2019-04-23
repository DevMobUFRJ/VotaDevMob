package ufrj.devmob.votadevmob.result

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_poll_result.*
import ufrj.devmob.votadevmob.R
import ufrj.devmob.votadevmob.core.model.Poll

class PollResultActivity : AppCompatActivity(), PollResultContract.View {

    internal lateinit var presenter: PollResultContract.Presenter

    companion object {
        const val POLL_KEY = "current_poll"
    }

    // chart variables
    private var chartEntries = emptyList<PieEntry>()
    private var chartDataSet = PieDataSet(chartEntries, "")
    private var chartData = PieData(chartDataSet)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll_result)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        val poll = intent?.extras?.get(POLL_KEY) as Poll?
        val poll = Poll(id = 123456789, optionsList = listOf("sim", "nao", "talvez"))

        if (poll == null) showMajorErrorMessage() else presenter = PollResultPresenter(this, poll)

        pieChart.run {
            isHighlightPerTapEnabled = false
            description.isEnabled = false
            legend.isEnabled = false
            setDrawEntryLabels(false)
        }
    }

    override fun showResult(result: Map<String, Int>) {
        resultValue.text = getMostVotedOptionText(result)
        resultTotalVotesValue.text = result.values.sum().toString()

        // chart setup
        chartEntries = result.map { PieEntry(it.value.toFloat(), it.key) }

        chartDataSet = PieDataSet(chartEntries, "").apply {
            sliceSpace = 5f
            ColorTemplate.JOYFUL_COLORS.forEach { colors.add(it) }
        }

        pieChart.data = chartData.apply {
            dataSet = chartDataSet
            setValueFormatter(PollResultValueFormatter())
            setValueTextSize(15f)
        }

        pieChart.invalidate()
    }

    internal fun getMostVotedOptionText(result: Map<String, Int>): String {
        val sortedResult= result.toList().sortedBy{ it.second }
        val mostVotedOption = sortedResult.last()
        val isDraw = sortedResult.count { it.second == mostVotedOption.second } > 1
        return if (isDraw)
            getString(R.string.result_draw)
        else
            getString(R.string.result_most_voted_option_value, mostVotedOption.first, mostVotedOption.second)
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showMajorErrorMessage() {
        resultContent.visibility = View.GONE
        resultLoading.visibility = View.GONE
        resultMajorErrorMessage.visibility = View.VISIBLE
    }

    override fun showLoading() {
        resultContent.visibility = View.GONE
        resultLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        resultContent.visibility = View.VISIBLE
        resultLoading.visibility = View.GONE
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
}
