package com.navr.core.java17;

/**
 * <pre>
 * "Text Blocks" was introduced to simplify the writing of multiline strings
 * and make the code more readable when working with long text content.
 * Text Blocks allow you to write multi-line strings in a more natural and concise way.
 * They are enclosed within triple double quotes, and indentation is preserved.
 *
 * Note that you don't need to manually escape newline characters with '\n'.
 *
 * Text Blocks are particularly useful when working with SQL queries, JSON, HTML,
 * XML, or any other text-based content that spans multiple lines.
 * They help improve code readability and maintainability.
 * </pre>
 */
public class TextBlocks {
    public static void main(String[] args) {
        String textBlock = """
                This is a test message to 
                demonstrate the TextBlock feature introduced 
                        in Java 17. 
                Let's enjoy the new feature. 
                    Bye!
                """;
        System.out.printf("textBlock=%s%n", textBlock);
    }
}
