package com.sa.icarasia.ui.fragments;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import com.sa.icarasia.R;
import com.sa.icarasia.ui.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Pattern;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by salman on 27/01/2018.
 */
public class LoginFragmentTest{

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String email = "salman@gmail.com";
    private String password = "salman@12";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreateView() throws Exception {
    }

    @Test
    public void onActivityCreated() throws Exception {
    }

    @Test
    public void login(){

        // Enter Values
        Espresso.onView(withId(R.id.input_email_login)).perform(typeText(email));
        Espresso.onView(withId(R.id.input_password_login)).perform(typeText(password));

        //close keyboard
        Espresso.closeSoftKeyboard();

        //click button
        Espresso.onView(withId(R.id.btn_login)).perform(click());

    }

    @Test
    public void onLoginSuccess() throws Exception {
    }

    @Test
    public void onLoginFailed() throws Exception {
    }

    @Test
    public void validate() throws Exception {
    }

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );
    public static boolean isValidEmail(CharSequence email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}