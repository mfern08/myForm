package com.example.form;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void openSecondActivity(){
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(withText("OK")).perform(click());

        onView(withId(R.id.button)).perform(ViewActions.scrollTo(), click());

        onView(withId(R.id.welcome)).check(matches(withText("Thanks for Signing Up")));
        onView(withId(R.id.username)).check(matches(withText("mfern08")));

    }


    @Test
    public void nameisEmpty(){
        onView(withId(R.id.name)).perform(typeText(""));
        onView(allOf(withId(R.id.name), hasErrorText("Please Enter Name")));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button)).perform(ViewActions.scrollTo(), click());
    }

    @Test
    public void usernameEmpty(){
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText(""));
        onView(allOf(withId(R.id.username), hasErrorText("Please Enter Username")));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button)).perform(ViewActions.scrollTo(), click());
    }

    @Test
    public void emailValid(){
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("adfadf"));
        onView(allOf(withId(R.id.email), hasErrorText("Enter valid Email")));
        onView(withId(R.id.date)).perform(click());

        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button)).perform(ViewActions.scrollTo(), click());
    }


    @Test
    public void emailNotEmpty(){
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText(""));
        onView(allOf(withId(R.id.email), hasErrorText("Enter valid Email")));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(1990, 9, 30));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button)).perform(ViewActions.scrollTo(), click());
    }

    @Test
    public void underAge() {
        onView(withId(R.id.name)).perform(typeText("Maria Fernandez"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(Matchers.equalTo(android.widget.DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 9, 30));
        onView(allOf(withId(R.id.date), hasErrorText("Must Be 18+ years")));
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button)).perform(ViewActions.scrollTo(), click());
    }

    @Test
    public void RotateAndSignIn(){
        onView(withId(R.id.name)).perform(typeText("Maria"));
        onView(withId(R.id.username)).perform(typeText("mfern08"));
        onView(withId(R.id.email)).perform(typeText("maria@gmail.com"));

        TestUtils.rotateScreen(TestUtils.getActivity(activityScenarioRule));

        onView(withId(R.id.name)).check(matches(withText("Maria")));
        onView(withId(R.id.username)).check(matches(withText("mfern08")));
        onView(withId(R.id.email)).check(matches(withText("maria@gmail.com")));
    }

}
