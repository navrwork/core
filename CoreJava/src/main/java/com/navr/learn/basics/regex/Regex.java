package com.navr.learn.regex;


/**
 *
 * Ref:
 *  https://www.vogella.com/tutorials/JavaRegularExpressions/article.html#common-matching-symbols
 *  https://docs.oracle.com/javase/tutorial/essential/regex/
 *
 * <pre>
 * RegEx	Description
 * =========================
 * .		Matches any character
 * ^regex	Finds regex that must match at the beginning of the line.
 * regex$	Finds regex that must match at the end of the line.
 * [abc]        Set definition, can match the letter a or b or c.
 * [abc][vz]    Set definition, can match a or b or c followed by either v or z.
 * [^abc]	When a caret appears as the first character inside square brackets, it negates the pattern. This pattern matches any character except a or b or c.
 * [a-d1-7]	Ranges: matches a letter between a and d and figures from 1 to 7, but not d1.
 * X|Z		Finds X or Z.
 * XZ		Finds X directly followed by Z.
 * $		Checks if a line end follows.
 *
 *
 * RegEx	Description
 * =========================
 * \d		Any digit, short for [0-9]
 * \D		A non-digit, short for [^0-9]
 * \s		A whitespace character, short for [ \t\n\x0b\r\f]
 * \S		A non-whitespace character, short for
 * \w		A word character, short for [a-zA-Z_0-9]
 * \W		A non-word character [^\w]
 * \S+		Several non-whitespace characters
 * \b		Matches a word boundary where a word character is [a-zA-Z0-9_]
 * </pre>
 */
public class Regex {

    public static void main(String[] args) {
        isValidPhoneNumber("1234567890"); // true
        isValidPhoneNumber("123-456-7890"); // true

        isValidEmail("abc@xyz.org"); // true
        isValidEmail("abcd123@hello.edu"); // true
        isValidEmail("this_is_a_long_email_id_1999@something0.com"); // true

        isValidEmail("abc@xyz_com"); // false
        isValidEmail("abc@butnotthisone.org"); // false
        isValidEmail("abc@xyz.boo"); // false
    }

    private static boolean isValidPhoneNumber(String phNumber) {
        boolean isValidPhoneNumber = false;
        // Below are valid 10 digit phone numbers
        // 1234567890
        // 123-456-7890
        if (phNumber.matches("^[0-9]{10}$")) {
            isValidPhoneNumber = true;
        } else if (phNumber.matches("^[0-9]{3}-[0-9]{3}-[0-9]{4}")) {
            isValidPhoneNumber = true;
        }
        System.out.printf("isValidPhoneNumber: phNumber=%s, result=%b%n", phNumber, isValidPhoneNumber);
        return isValidPhoneNumber;
    }

    private static boolean isValidEmail(String emailId) {
        boolean isValidEmail = false;
        //
        // Valid ids: abc123@something.com, abc@something0.org, abc123@some123.edu
        //
        // Constraints
        // First part of the mail id: First char should be a-z OR A-Z. Min 3 chars. Can contain alphabets or numbers.
        // Second part of the mail should be a word char and has min 3 chars and max 10 chars.
        // Last part (3rd part) contains one of these: .com, .org, .edu

        String regex = "^[a-z|A-Z]\\w{2,}@\\w{3,10}\\.[com|org|edu].*";
        isValidEmail = emailId.matches(regex);
        System.out.printf("isValidEmail: emailId=%s, %b%n", emailId, isValidEmail);

        return isValidEmail;
    }
}
