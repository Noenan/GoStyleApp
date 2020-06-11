package fr.gostyle.gostyleApp;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.gostyle.gostyleApp.models.User;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpTest {

    @Rule
    public ActivityTestRule<SignUp> signUpActivity =
            new ActivityTestRule(SignUp.class);

    //A investiguer le test ne marche pas pour le moment
    @Test
    public void registerUserTest() {
        User userTest = new User();
        userTest.setNom("Henry");
        userTest.setPrenom("Philippe");
        userTest.setEmail("henry@gmail.com");
        userTest.setPassword("12345678");
        signUpActivity.getActivity().registerUser(userTest,"123456789");

    }
}
