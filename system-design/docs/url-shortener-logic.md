# URL Shortener Logic

This project uses a hash-based URL shortener implemented in `UrlShortenerUtil`.

## Overview

The logic follows this flow:

1. Take the original long URL.
2. Generate a SHA-256 hash of the URL.
3. Convert the hash bytes to a positive `BigInteger`.
4. Convert that number into a Base62 string.
5. Shorten the result to a fixed length (8 characters in this code).
6. Return the shortened code.

## Code Logic

### 1) Generate SHA-256 hash

```java
private static BigInteger generateHash(String url) {
    BigInteger hash = BigInteger.ZERO;
    try {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(url.getBytes(StandardCharsets.UTF_8));
        hash = new BigInteger(1, hashBytes);
    } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
    }
    return hash;
}
```

What it does:
- Takes the URL string.
- Converts it to UTF-8 bytes.
- Creates a SHA-256 digest.
- Turns the digest into a positive `BigInteger`.

This makes the input deterministic and unique for most URLs.

### 2) Convert BigInteger to Base62

```java
public static String toBase62(BigInteger number) {
    BigInteger base = BigInteger.valueOf(BASE62_CHARS.length());
    StringBuilder sb = new StringBuilder();

    while (number.compareTo(BigInteger.ZERO) > 0) {
        BigInteger[] divRem = number.divideAndRemainder(base);
        int remainder = divRem[1].intValue();
        sb.append(BASE62_CHARS.charAt(remainder));
        number = divRem[0];
    }

    return sb.reverse().toString();
}
```

Character set used:

```java
BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
```

How it works:
- Repeatedly divides the number by 62.
- Stores the remainder.
- Maps the remainder to a character from the Base62 alphabet.
- Reverses the result because the digits are collected from right to left.

This creates a compact, URL-safe representation of the hash.

### 3) Shorten to a fixed length

```java
private static String shortenUrl(String longUrl, int shortUrlLength) {
    String shortCode = longUrl.length() > shortUrlLength ? longUrl.substring(0, shortUrlLength) : longUrl;
    return shortCode;
}
```

The final generator method is:

```java
public static String generateShortUrl(String url) {
    BigInteger hash = generateHash(url);
    String base62 = toBase62(hash);
    return shortenUrl(base62, 8);
}
```

This means:
- Hash the URL
- Encode to Base62
- Keep only the first 8 characters

## Example flow

For a URL like:

```text
https://www.abc.com
```

The code:
- Generates a SHA-256 hash
- Converts the hash to a `BigInteger`
- Converts that number to Base62
- Takes the first 8 characters

That final 8-character value becomes the short URL code.

## Important note

This implementation is a simplified demonstration. In production, a real URL shortener normally also needs:

- a database to map short codes to original URLs
- collision handling
- uniqueness checks
- expiration or redirect management
- optional custom aliases

This current logic is mainly a hashing and encoding technique, not a full short-link storage system.
