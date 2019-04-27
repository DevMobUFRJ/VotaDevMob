package ufrj.devmob.votadevmob.poll

import io.mockk.confirmVerified
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import ufrj.devmob.votadevmob.core.model.Poll

@RunWith(RobolectricTestRunner::class)
class PollPresenterTest {

    private lateinit var presenter : PollPresenter
    private lateinit var view : PollContract.View
    private lateinit var model: PollModel

    @Before
    fun setup() {
        view = mockkClass(PollContract.View::class, relaxed = true)
        model = mockkClass(PollModel::class, relaxed = true)
        presenter = PollPresenter(view = view, currentPoll = Poll(id = 1))
    }

    @Test
    fun `Should create model`() {
        assertNotNull(presenter.model)
    }

    @Test
    fun `Should call setup layout`() {
        presenter = PollPresenter(view = view, currentPoll = Poll(id = 1))
        verify { view.setupPollLayout("", emptyList()) }
        confirmVerified(view)
    }
}