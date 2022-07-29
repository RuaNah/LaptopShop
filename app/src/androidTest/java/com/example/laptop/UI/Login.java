package com.example.laptop.UI;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;

import com.example.laptop.R;
import com.example.laptop.activity.LoginActivity;
import com.example.laptop.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class Login {
    UltilsTest utilsTest = new UltilsTest();
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);
    @Rule
    public ActivityTestRule<MainActivity> intentRule
            = new ActivityTestRule<>(
            MainActivity.class,
            true,     // initialTouchMode
            false);

    @Test
    public void check(){
        utilsTest.setText(R.id.edt_username,"quochuy88");
        utilsTest.setText(R.id.edt_password,"2");
        utilsTest.btnClick(R.id.btnSignin);

        utilsTest.equal(R.id.edt_username, "quochuy88");
        utilsTest.equal(R.id.edt_password,"123456");
        //Intent intent = new Intent();
        //intentRule.launchActivity(intent);

    }
}
