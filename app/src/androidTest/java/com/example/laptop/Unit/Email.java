    package com.example.laptop.Unit;

    import org.hamcrest.core.Is;
    import org.junit.Assert;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.junit.runners.JUnit4;

    import static org.hamcrest.CoreMatchers.is;
    import static org.junit.Assert.assertEquals;

    import java.util.Date;

    @RunWith(JUnit4.class)
    public class Email {
        @Test
        public void testIsEmailValid() {
            String testEmail = "anupamchugh@gmail.com";
            Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail),
                    Data.checkEmailForValidity(testEmail), is(true));
        }
        @Test
        public void testEmailValidityPartTwo() {
            String testEmail = "   anupamchugh@gmail.com  ";
            Assert.assertThat(String.format("Email Validity Test failed for %s ", testEmail),
                    Data.checkEmailForValidity(testEmail), is(true));
        }
        @Test
        public void emailStringNullCheck() {
            Assert.assertThat(Data.emailStringChecker(null),Is.is(""));
        }
        @Test
        public void emailStringEmptyCheck() {
            Assert.assertThat(Data.emailStringChecker(""), Is.is(""));
        }

    }
