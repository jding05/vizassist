package com.example.vizassist;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    // the apk that you are tested
    // enter app and do this test --> open main activity
    // if you didn't assign, there are so many view, this test doesn't know which to test
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    // defalut generate, to make sure the package is the right package name
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.vizassist", appContext.getPackageName());
    }

    @Test
    public void viewsAreVisible() {
        // make sure your capturedimage is display
        onView(withId(R.id.capturedImage)).check(matches((isDisplayed())));
        // make sure your resultview  is display
        onView(withId(R.id.resultView)).check(matches((isDisplayed())));

        // resultview need a test -- placeholder (no result yet)
        onView(withId(R.id.resultView)).check(matches(withText(R.string.result_placeholder)));
        // check image > 0
        onView(withId(R.id.capturedImage)).check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                assertNotEquals(view.getHeight(), 0);
                assertNotEquals(view.getWidth(), 0);
            }
        });
    }
}
