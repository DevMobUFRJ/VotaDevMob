package ufrj.devmob.votadevmob.newpoll

import org.junit.Assert
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
        Assert.assertNotNull(activity.presenter)
    }

}