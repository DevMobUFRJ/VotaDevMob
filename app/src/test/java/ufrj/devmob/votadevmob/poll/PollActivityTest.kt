package ufrj.devmob.votadevmob.poll

import android.content.Intent
import android.view.View
import android.widget.RadioButton
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import ufrj.devmob.votadevmob.core.model.Poll

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
        assertEquals(View.GONE, activity.pollTitle.visibility)
        assertEquals(View.GONE, activity.pollRadioGroup.visibility)
        assertEquals(View.GONE, activity.pollVoteButton.visibility)
    }

    @Test
    fun `Should hide loading layout`() {
        activity.hideLoading()
        assertEquals(View.GONE, activity.pollLoading.visibility)
        assertEquals(View.VISIBLE, activity.pollTitle.visibility)
        assertEquals(View.VISIBLE, activity.pollRadioGroup.visibility)
        assertEquals(View.VISIBLE, activity.pollVoteButton.visibility)
    }

    @Test
    fun `Should major error layout be visible`() {
        activity.showMajorErrorMessage()
        assertEquals(View.GONE, activity.pollLoading.visibility)
        assertEquals(View.GONE, activity.pollTitle.visibility)
        assertEquals(View.GONE, activity.pollRadioGroup.visibility)
        assertEquals(View.GONE, activity.pollVoteButton.visibility)
        assertEquals(View.VISIBLE, activity.pollMajorErrorMessage.visibility)
    }

    @Test
    fun `Should show major error layout`() {
        assertEquals(View.VISIBLE, activity.pollMajorErrorMessage.visibility)
    }

    @Test
    fun `Should create presenter`() {
        val poll = Poll(
            id = 123456789,
            password = "senha",
            title = "Me vota",
            optionsList = listOf(
                "sim",
                "nao",
                "talvez"
            )
        )
        val intent = Intent().putExtra(BasePollingActivity.POLL_KEY, poll)
        activity = Robolectric.buildActivity(PollActivity::class.java, intent).create().get()
        assertNotNull(activity.presenter)
    }

    @Test
    fun `Should be disable vote button`() {
        assertFalse(activity.pollVoteButton.isEnabled)
    }

    @Test
    fun `Should enable vote button`() {
        activity.selectedRadioButton = RadioButton(RuntimeEnvironment.systemContext)
        assertTrue(activity.pollVoteButton.isEnabled)
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
    fun `Should setup layout empty`() {
        activity.pollRadioGroup.removeAllViews()
        activity.setupPollLayout("", listOf("sim", "nao", ""))
        assertEquals("- Votação sem título -", activity.pollTitle.text)
        assertEquals(2, activity.pollRadioGroup.childCount)
    }

    @Test
    fun `Should return configured RadioButton`() {
        val radioButton = activity.getConfiguredRadioButton("text")
        assertEquals("text", radioButton.text)
        assertNotNull(radioButton.id)
        assertEquals(8, radioButton.paddingStart)
        assertEquals(8, radioButton.paddingEnd)
        assertEquals(4, radioButton.paddingTop)
        assertEquals(4, radioButton.paddingBottom)
        assertEquals(5f, radioButton.textSize)
    }
}