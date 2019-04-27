package ufrj.devmob.votadevmob.result

import com.github.mikephil.charting.data.PieEntry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PollResultValueFormatterTest {

    private lateinit var valueFormatter: PollResultValueFormatter

    @Before
    fun setup() {
        valueFormatter = PollResultValueFormatter()
    }

    @Test
    fun `Should format`() {
        val actual = valueFormatter.getFormattedValue(
            value = 5f,
            entry = PieEntry(6f, "teste"),
            dataSetIndex =  0,
            viewPortHandler = null)
        assertEquals("teste (5)", actual)
    }

}