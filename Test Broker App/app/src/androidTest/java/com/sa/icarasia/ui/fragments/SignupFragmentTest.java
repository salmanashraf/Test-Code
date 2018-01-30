package com.sa.icarasia.ui.fragments;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.sa.icarasia.R;
import com.sa.icarasia.ui.activities.MainActivity;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by salman on 31/01/2018.
 */
public class SignupFragmentTest {

    private String email = "salman333@gmail.com";
    private String password = "salman@12";
    private String fName = "Salman";
    private String lName = "Ashraf";
    private String number = "01495129122";
    private String  selectionText = "Broker";

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void signup(){


        Matcher<View> matcher = allOf(withText("Signup"),
                isDescendantOfA(withId(R.id.tabs)));
        onView(matcher).perform(click());

        Espresso.onView(withId(R.id.input_email)).perform(typeText(email));
        Espresso.onView(withId(R.id.input_password)).perform(typeText(password));
        Espresso.onView(withId(R.id.input_fname)).perform(typeText(fName));
        Espresso.onView(withId(R.id.input_lname)).perform(typeText(lName));
        Espresso.onView(withId(R.id.input_mobile)).perform(typeText(number));

        //close keyboard
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerUserType)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(selectionText))).perform(click());
        onView(withId(R.id.spinnerUserType)).check(matches(withSpinnerText(containsString(selectionText))));



        //click button
        Espresso.onView(withId(R.id.btn_signup)).perform(click());

    }

}