package ufrj.devmob.votadevmob.main

import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowAlertDialog

/**
 * Created by victor.cruz on 29/04/2019.
 * Copyright (c) Stone Co. All rights reserved.
 * victor.cruz@stone.com.br
 */
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().get()
    }

    @Test
    fun `Should create presenter`() {
        assertNotNull(activity.presenter)
    }

    @Test
    fun `Should show dialog`() {
        activity.showInputDialog(MainActivity::class.java)
        val dialogs = ShadowAlertDialog.getShownDialogs()
        assertEquals(1, dialogs.size)
    }

    @Test
    fun `Should create on click listeners`() {
        assertNotNull(activity.createPollButton.hasOnClickListeners())
        assertNotNull(activity.voteButton.hasOnClickListeners())
        assertNotNull(activity.resultPollButton.hasOnClickListeners())
    }

    @Test
    fun `Should show loading layout`() {
        activity.showLoading()
        assertEquals(View.VISIBLE, activity.mainLoading.visibility)
        assertEquals(View.GONE, activity.mainContent.visibility)
    }

    @Test
    fun `Should hide loading layout`() {
        activity.hideLoading()
        assertEquals(View.GONE, activity.mainLoading.visibility)
        assertEquals(View.VISIBLE, activity.mainContent.visibility)
    }
}