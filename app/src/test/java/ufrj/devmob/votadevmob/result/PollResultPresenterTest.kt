package ufrj.devmob.votadevmob.result

import io.mockk.confirmVerified
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import ufrj.devmob.votadevmob.core.model.Poll

@RunWith(RobolectricTestRunner::class)
class PollResultPresenterTest {

    private lateinit var presenter : PollResultPresenter
    private lateinit var view : PollResultContract.View
    private lateinit var model: PollResultModel

    @Before
    fun setup() {
        view = mockkClass(PollResultContract.View::class, relaxed = true)
        model = mockkClass(PollResultModel::class, relaxed = true)
        presenter = PollResultPresenter(view = view, currentPoll = Poll(id = 1))
    }

    @Test
    fun `Should create model`() {
        Assert.assertNotNull(presenter.model)
    }

    @Test
    fun `Should create current result map`() {
        Assert.assertNotNull(presenter.currentResult)
    }

    @Test
    fun `Should call show loading`() {
        presenter = PollResultPresenter(view = view, currentPoll = Poll(id = 1))
        verify { view.showLoading() }
        confirmVerified(view)
    }

    @Test
    fun `Should call get poll result`() {
        presenter.getPollResult()
    }
}