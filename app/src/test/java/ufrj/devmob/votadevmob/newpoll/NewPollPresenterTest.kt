package ufrj.devmob.votadevmob.newpoll

import io.mockk.mockkClass
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NewPollPresenterTest {

    private lateinit var presenter: NewPollPresenter
    private lateinit var view: NewPollContract.View
    private lateinit var model: NewPollModel

    @Before
    fun setup() {
        view = mockkClass(NewPollContract.View::class, relaxed = true)
        model = mockkClass(NewPollModel::class, relaxed = true)
        presenter = NewPollPresenter(view = view)
    }

    @Test
    fun `Should create model`() {
        assertNotNull(presenter.model)
    }

    @Test
    fun `Should add option to map`() {
        presenter.addOptionToMap("teste")
        assertEquals(mutableListOf("teste"), presenter.options)
    }
}