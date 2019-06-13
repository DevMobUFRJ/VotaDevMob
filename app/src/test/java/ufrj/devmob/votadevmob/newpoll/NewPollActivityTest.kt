package ufrj.devmob.votadevmob.newpoll

import android.view.View
import android.widget.EditText
import io.mockk.registerInstanceFactory
import kotlinx.android.synthetic.main.activity_new_poll.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NewPollActivityTest {

    private lateinit var activity: NewPollActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(NewPollActivity::class.java).create().get()
    }

    @Test
    fun `Should create presenter`() {
        assertNotNull(activity.presenter)
    }

    @Test
    fun `Should create on click listeners`() {
        assertNotNull(activity.button_addOption.hasOnClickListeners())
        assertNotNull(activity.button_createPoll.hasOnClickListeners())
        assertNotNull(activity.button_vote.hasOnClickListeners())
        assertNotNull(activity.button_backMain.hasOnClickListeners())
    }

    @Test
    fun `Should hide create button`() {
        activity.hideCreateButton()
        assertEquals(View.GONE, activity.button_createPoll.visibility)
        assertEquals(View.VISIBLE, activity.linear_backVote.visibility)
        assertEquals(View.VISIBLE, activity.linear_voteId.visibility)
    }

    @Test
    fun `Should clear field`() {
        activity.field_pollOption.setText("opcao_teste")
        activity.addOption()
        assertEquals("", activity.field_pollOption.text.toString())
    }

    @Test
    fun `Should showMajorErrorMessage`() {
        activity.showMajorErrorMessage()
        assertEquals(View.GONE, activity.text_pollKey.visibility)
        assertEquals(View.GONE, activity.field_pollTitle.visibility)
        assertEquals(View.GONE, activity.field_pollPassword.visibility)
        assertEquals(View.GONE, activity.field_pollOption.visibility)
        assertEquals(View.GONE, activity.button_addOption.visibility)
        assertEquals(View.GONE, activity.button_createPoll.visibility)
        assertEquals(View.GONE, activity.options_recyclerView.visibility)
        assertEquals(View.VISIBLE, activity.pollMajorErrorMessage.visibility)
    }

    @Test
    fun `Shoulds`() {

    }
}