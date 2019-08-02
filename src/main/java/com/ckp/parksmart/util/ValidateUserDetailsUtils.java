package com.ckp.parksmart.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUserDetailsUtils {

    private ValidateUserDetailsUtils() {

    }

    /**
     * Checks whether the given user name is valid or not
     *
     * @param userName
     * @return true valid user name, false invalid user name
     */
    public static boolean isInvalidUsername(String userName)
    {
        boolean isContainSpace = false;
        if (userName.contains(" ")) {
            isContainSpace = true;
        }
        return isContainSpace;
    }

    /**
     * Validate password with regular expression
     *
     * @param password
     * @return true valid password, false invalid password
     */
    public static boolean isInvalidPassword(String password)
    {
        final String PASS_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^`~&*()_+\\-=\\[\\]{};’:”\\\\|,.<>\\/?]).{8,20}$";
        Pattern pattern = Pattern.compile(PASS_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Validate emailId with regular expression
     *
     * @param emailId for validation
     * @return true valid emailId, false invalid emailId
     */
    public static boolean isInvalidEmail(final String emailId)
    {
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }

    /**
     * Validate phone number with regular expression
     *
     * @param phoneNumber
     * @return true valid phone number, false invalid phone number
     */
    public static boolean isInvalidPhoneNumber(String phoneNumber) {
        boolean isInvalidPhoneNumber = false;
        if (!(phoneNumber.matches("^((?=[+0-9\\-])(?![_]).)*$"))) {
            isInvalidPhoneNumber = true;
        }
        return isInvalidPhoneNumber;
    }


    /**
     * Checks whether the given string contains special characters
     *
     * @param input
     * @return true valid input, false invalid input
     */
    //Punct: Special Characters, Space: Whitespace characters
    public static boolean containsPunctOrSpace(String input)
    {
        return !input.matches("^(?=[A-Za-z])[[^\\p{Punct}]&&[^\\p{Space}]]+$");//^[a-zA-Z0-9 ]*$   "^[a-zA-Z0-9_]*$"
    }
}
