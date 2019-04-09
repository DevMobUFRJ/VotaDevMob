package ufrj.devmob.votadevmob.poll

import android.view.View
import kotlinx.android.synthetic.main.activity_poll.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PollActivityTest{

    private lateinit var activity: PollActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(PollActivity::class.java).create().get()
    }

    @Test
    fun `Should show loading layout`() {
        activity.showLoading()
        assertEquals(View.VISIBLE, activity.pollLoading.visibility)
    }

    @Test
    fun `Should hide loading layout`() {
        activity.hideLoading()
        assertEquals(View.GONE, activity.pollLoading.visibility)
    }

    @Test
    fun `Should create presenter`() {
        assertNotNull(activity.presenter)
    }

    @Test
    fun `Should set selectedRadioButton`() {
        activity.setupPollLayout("", listOf(""))
        activity.pollRadioGroup.check(View.generateViewId())
        assertNotNull(activity.selectedRadioButton)
    }

    @Test
    fun `Should have click listeners VoteButton`() {
        assertTrue(activity.pollVoteButton.hasOnClickListeners())
    }

    @Test
    fun `Should setup layout`() {
        activity.pollRadioGroup.removeAllViews()
        activity.setupPollLayout("title", listOf("sim", "nao"))
        assertEquals("title", activity.pollTitle.text)
        assertEquals(2, activity.pollRadioGroup.childCount)
    }

    @Test
    fun `Should return configured RadioButton`() {
        val radioButton = activity.getConfiguredRadioButton("text")
        assertEquals("text", radioButton.text)
        assertNotNull(radioButton.id)
        assertEquals(10, radioButton.paddingStart)
        assertEquals(10, radioButton.paddingEnd)
        assertEquals(5, radioButton.paddingTop)
        assertEquals(5, radioButton.paddingBottom)
        assertEquals(20f, radioButton.textSize)
    }
}