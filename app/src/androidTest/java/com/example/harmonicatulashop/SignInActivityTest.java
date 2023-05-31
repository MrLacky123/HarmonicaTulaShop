package com.example.harmonicatulashop;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.harmonicatulashop.ui.account.activities.SignInActivity;

import org.junit.Rule;
import org.junit.Test;

public class SignInActivityTest {

    @Rule
    public ActivityScenarioRule<SignInActivity> scenarioRule =
            new ActivityScenarioRule<>(SignInActivity.class);

    @Test
    public void testActivity() {

        onView(withId(R.id.sign_in)).perform(click());

        onView(withId(R.id.sign_in_button)).perform(click());
        onView(withId(R.id.login_red)).check(matches(withText("Поле логин должно быть заполнено!")));

        String login = "ilgnev";

        onView(withId(R.id.editTextLogin)).perform(typeText(login));
        onView(withId(R.id.sign_in_button)).perform(click());
        onView(withId(R.id.password_red)).check(matches(withText("Поле пароль должно быть заполнено!")));

        login = "werfa";
        String password = "12345";

        onView(withId(R.id.editTextLogin)).perform(typeText(login));
        onView(withId(R.id.editTextPassword)).perform(typeText(password));
        onView(withId(R.id.login_red)).check(matches(withText("Введен неверный логин")));

        login = "user";
        password = "14";

        onView(withId(R.id.editTextLogin)).perform(typeText(login));
        onView(withId(R.id.editTextPassword)).perform(typeText(password));
        onView(withId(R.id.password_red)).check(matches(withText("Введен неверный пароль!")));
    }
}
