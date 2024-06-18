package org.example.hrms.core.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isNullOrWhitespace(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isValidWebsite(String website) {
        // Updated regex to support http, https, and www.
        String regex = "^(https?://)?(www\\.)?[a-zA-Z0-9]+([\\-\\.]?a-zA-Z0-9]+)*\\.[a-zA-Z]{2,6}(/.*)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(website);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email, String website) {
        // Simple regex to check if the email is in a valid format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches()) {
            return false;
        }

        // Extract the domain from the email and the website
        String emailDomain = email.substring(email.indexOf("@") + 1);
        String websiteDomain = website.replaceAll("^(https?://)?(www\\.)?", "");

        // Ensure the email domain matches the website domain
        return emailDomain.equals(websiteDomain);
    }
}
