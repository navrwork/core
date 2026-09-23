package com.navr.sysdesign.urlshortener;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for URL shortening.
 * <br>
 * This class provides methods to generate a unique hash for a given URL,
 * convert that hash to a base62 string, and shorten the resulting string
 * to a specified length. It is designed to facilitate the creation of
 * shortened URLs for use in web applications.
 */
public class UrlShortenerUtil {

    public static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Base62 character set

    /**
     * Generates a unique hash for the given URL.
     *
     * @param url The original URL to be shortened.
     * @return A unique hash representing the shortened URL.
     */
    private static BigInteger generateHash(String url) {
        BigInteger hash = BigInteger.ZERO;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(url.getBytes(StandardCharsets.UTF_8)); // Generate the hash
            hash = new BigInteger(1, hashBytes); // Convert the byte array to a positive BigInteger
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return hash;
    }

    /**
     * Converts a BigInteger to a base62 string representation.
     * <br>
     * Logic: <br>
     * The method repeatedly divides the number by 62 and uses the remainder
     * to index into a character set that includes lowercase letters, uppercase letters, and digits.
     * The process continues until the number is reduced to zero, and the resulting characters are
     * reversed to form the final base62 string.
     * <br>
     *
     * @param number The BigInteger to be converted.
     * @return A base62 string representation of the number.
     */
    public static String toBase62(BigInteger number) {
        BigInteger base = BigInteger.valueOf(BASE62_CHARS.length());
        StringBuilder sb = new StringBuilder();

        // Convert the number to base62
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = number.divideAndRemainder(base);
            int remainder = divRem[1].intValue();
            sb.append(BASE62_CHARS.charAt(remainder));
            number = divRem[0];
        }

        // Reverse it because we extracted digits from right to left
        return sb.reverse().toString();
    }

    /**
     * Shortens the given long URL to a specified length.
     *
     * @param longUrl        The original long URL to be shortened.
     * @param shortUrlLength The desired length of the shortened URL.
     * @return A shortened version of the original URL.
     */
    private static String shortenUrl(String longUrl, int shortUrlLength) {
        String shortCode = longUrl.length() > shortUrlLength ? longUrl.substring(0, shortUrlLength) : longUrl;
        return shortCode;
    }

    /**
     * Generates a short URL for the given long URL.
     *
     * @param url The original long URL to be shortened.
     * @return A shortened version of the original URL.
     */
    public static String generateShortUrl(String url) {
        BigInteger hash = generateHash(url);
        String base62 = toBase62(hash);
        return shortenUrl(base62, 8); // Shorten to 8 characters
    }

    public static void main(String[] args) {

        System.out.printf("###############################################%n");
        System.out.printf("Short URL Utility Test%n");
        System.out.printf("###############################################%n");

        // URLs with same characters but different order should generate different short URLs
        String url1 = "https://www.abc.com";
        BigInteger hash1 = generateHash(url1);
        System.out.printf("Hash for %s: %s%n", url1, hash1.toString(16)); // Print hash in hexadecimal
        String shortUrl1 = generateShortUrl(url1);
        String url2 = "https://www.cab.com"; // Same characters as url1 but different order
        BigInteger hash2 = generateHash(url2);
        System.out.printf("Hash for %s: %s%n", url2, hash2.toString(16)); // Print hash in hexadecimal
        String shortUrl2 = generateShortUrl(url2);

        System.out.printf("Short URL for %s: %s%n", url1, shortUrl1);
        System.out.printf("Short URL for %s: %s%n", url2, shortUrl2);
    }
}
