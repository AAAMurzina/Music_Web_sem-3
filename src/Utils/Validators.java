package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public static final Pattern VALID_LOGIN_REGEX =
            Pattern.compile("^([a-zA-Z_$][a-zA-Z\\d_$]*)$");

    public static boolean validateLogin(String loginStr) {
        Matcher matcher = VALID_LOGIN_REGEX .matcher(loginStr);
        return matcher.find();
    }
}
