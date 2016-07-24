package com.szy.stardust.fm.home;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.szy.stardust.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withText("graphics"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rv_main), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(4, click()));

        pressBack();

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.rv_main), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(5, click()));

        pressBack();

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.rv_main), isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(6, click()));

        pressBack();

    }

}
