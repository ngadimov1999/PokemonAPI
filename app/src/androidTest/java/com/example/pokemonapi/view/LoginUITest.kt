package com.example.pokemonapi.view

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.pokemonapi.R

import org.junit.Rule
import org.junit.Test

class LoginUITest {
    private val login = "stas"
    private val password = "123456"

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun clickLoginButton_opensSearchFragment() {
        onView(withId(R.id.editLogin)).perform(ViewActions.typeText(login))
        onView(withId(R.id.editPassword)).perform(ViewActions.typeText(password))

        onView(withId(R.id.button_login)).perform(ViewActions.click())

        Thread.sleep(1_000)
        Espresso.onView(withId(R.id.search_field))
            .check(matches(withText("")))
    }

    @Test
    fun clickRegistrationButton_opensRegistrationDialog() {
        onView(withId(R.id.button_registration)).perform(ViewActions.click())

        Espresso.onView(withId(R.id.button_reg_confirm))
            .check(matches(withText("Зарегистрироваться")))
    }
}