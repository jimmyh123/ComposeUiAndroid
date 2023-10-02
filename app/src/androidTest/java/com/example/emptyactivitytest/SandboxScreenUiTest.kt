package com.example.emptyactivitytest

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.emptyactivitytest.util.TestTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SandboxScreenUiTest {

    @get:Rule(order = 0)
    val composeTestRule = createComposeRule()

    @Before
    fun setup() { }

    @Test
    fun landlordsTotalProfitBanner_displaysCorrectCurrencyAndValue() {
        composeTestRule.setContent {
            EmptyActivityApp()
        }

        composeTestRule.onNodeWithTag(TestTags.NAVIGATION_BAR_BUTTON)
            .assertExists()
            .performClick()

        composeTestRule.onNodeWithTag(TestTags.TOOLBAR_PLUS_SIGN)
            .assertExists()
            .performClick()

        composeTestRule.onNodeWithTag(TestTags.NEW_NOTE_BUTTON)
            .assertExists()
            .performClick()

        composeTestRule.onNodeWithTag(TestTags.LIST_OF_NOTES_TEXT)
            .assertExists()

        composeTestRule.onNodeWithTag(TestTags.LIST_OF_NOTES_GOES_HERE_TEXT)
            .assertExists()

        composeTestRule.onNodeWithTag(TestTags.SHOW_SNACKBAR_BUTTON)
            .assertExists()
            .performClick()

        composeTestRule.onNodeWithTag(TestTags.BOTTOM_APP_BAR)
            .assertExists()
            .performClick()
    }
}