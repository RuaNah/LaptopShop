package com.example.laptop.UI;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;


public class UltilsTest {
    public void setText(int idText,String value){
        onView(ViewMatchers.withId(idText)).perform(ViewActions.typeText(value),closeSoftKeyboard());
    }
    public void btnClick(int idBtn)
    {
        onView(withId(idBtn)).perform(ViewActions.click());
    }
    public void equal(int idText,String s)
    {
        onView(withId(idText)).check(matches(withText(s)));
    }

}
