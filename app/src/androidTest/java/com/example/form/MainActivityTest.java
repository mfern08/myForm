package com.example.form;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void openSecondActivity(){
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
    }

    @Test
    public void nameisEmpty(){
        onView(withId(R.id.name)).perform(typeText(""));
        onView(allOf(withId(R.id.name), hasErrorText("Please Enter Name")));
    }

    @Test
    public void usernameEmpty(){
        onView(withId(R.id.username)).perform(typeText(""));
        onView(allOf(withId(R.id.username), hasErrorText("Please Enter Username")));
    }

    @Test
    public void emailValid(){
        onView(withId(R.id.email)).perform(typeText("adfadf"));
        onView(allOf(withId(R.id.email), hasErrorText("Enter valid Email")));
    }


    @Test
    public void emailNotEmpty(){
        onView(withId(R.id.email)).perform(typeText(""));
        onView(allOf(withId(R.id.email), hasErrorText("Enter valid Email")));
    }

}
