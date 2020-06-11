package fr.gostyle.gostyleApp;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ExpressoTest {
    @Rule
    public ActivityScenarioRule<SignUp> signUpActivity =
            new ActivityScenarioRule(SignUp.class);


    @Test
    public void openSignupActivity() {
        onView(withId(R.id.editText_nom)).perform(typeText("DUPONT"));
        onView(withId(R.id.editText_prenom)).perform(typeText("Fabien"));
        onView(withId(R.id.editText_email)).perform(typeText("fabien.dupont@gmail.com"));
        onView(withId(R.id.editText_password)).perform(typeText("12345678"));
        onView(withId(R.id.editText_password2)).perform(typeText("12345678"));
        onView(withId(R.id.button_signup)).perform(click());
        onView(withId(R.id.button_signup)).check(doesNotExist());



        /*onView(withId(R.id.btn_link_to_login)).perform(click());
        onView(withId(R.id.button_login)).check(matches(isDisplayed()));*/
       /* onView(withId(R.id.editText_nom));
        onView(withId(R.id.editText_prenom));
        onView(withId(R.id.editText_email));
        onView(withId(R.id.editText_password));
        onView(withId(R.id.editText_password2));*/

    }


}
