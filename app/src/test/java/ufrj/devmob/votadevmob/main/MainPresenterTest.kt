package ufrj.devmob.votadevmob.main

import io.mockk.mockkClass
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by victor.cruz on 29/04/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class MainPresenterTest {

    private lateinit var presenter : MainPresenter
    private lateinit var view : MainContract.View
    private lateinit var model: MainModel

    @Before
    fun setup() {
        view = mockkClass(MainContract.View::class, relaxed = true)
        model = mockkClass(MainModel::class, relaxed = true)
        presenter = MainPresenter(view = view)
    }

    @Test
    fun `Should create model`() {
        Assert.assertNotNull(presenter.model)
    }

    @Test
    fun `Should call get poll for activity`() {
        presenter.getPollForActivity("123", "senha", MainActivity::class.java)
    }
}