package ufrj.devmob.votadevmob.result

import android.view.View
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_poll_result.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PollResultActivityTest{

    private lateinit var activity: PollResultActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(PollResultActivity::class.java).create().get()
    }

    @Test
    fun `Should show loading layout`() {
        activity.showLoading()
        assertEquals(View.VISIBLE, activity.resultLoading.visibility)
        assertEquals(View.GONE, activity.resultContent.visibility)
    }

    @Test
    fun `Should hide loading layout`() {
        activity.hideLoading()
        assertEquals(View.GONE, activity.resultLoading.visibility)
        assertEquals(View.VISIBLE, activity.resultContent.visibility)
    }

    @Test
    fun `Should show major error layout`() {
        activity.showMajorErrorMessage()
        assertEquals(View.GONE, activity.resultLoading.visibility)
        assertEquals(View.GONE, activity.resultContent.visibility)
        assertEquals(View.VISIBLE, activity.resultMajorErrorMessage.visibility)
    }

    @Test
    fun `Should major error layout be visible`() {
        assertEquals(View.VISIBLE, activity.resultMajorErrorMessage.visibility)
    }

    @Test
    fun `Should configure chart`() {
        activity = Robolectric.buildActivity(PollResultActivity::class.java).create().get()
        assertFalse(activity.pieChart.isHighlightPerTapEnabled)
        assertFalse(activity.pieChart.description.isEnabled)
        assertFalse(activity.pieChart.legend.isEnabled)
        assertFalse(activity.pieChart.isDrawEntryLabelsEnabled)
    }

    @Test
    fun `Should get most voted option text case not draw`() {
        val result = mutableMapOf<String, Int>()
        result["sim"] = 3
        result["nao"] = 2

        val text = activity.getMostVotedOptionText(result)
        assertEquals("sim (3 votos)", text)
    }

    @Test
    fun `Should get most voted option text case draw`() {
        val result = mutableMapOf<String, Int>()
        result["sim"] = 3
        result["nao"] = 3

        val text = activity.getMostVotedOptionText(result)
        assertEquals("Empate", text)
    }

    @Test
    fun `Should show poll result`() {
        val result = mutableMapOf<String, Int>()
        result["sim"] = 4
        result["nao"] = 3

        activity.showResult(result)
        assertEquals("sim (4 votos)", activity.resultValue.text)
        assertEquals("7 votos", activity.resultTotalVotesValue.text)
        assertEquals(5f, activity.chartDataSet.sliceSpace)
    }
}