package com.hoaitester.helpers;

import java.io.File;
import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;


public class SystemHelper {
    private static final Pattern NONLATIN = Pattern.compile( "[^||w-]");
    private static final Pattern WHITESPACE = Pattern.compile(  "[\\s]");
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + File.separator;
        return current;
    }
//Hàm khử dấu
    public static String removeSpecialCharacters(String str){
// Normalize the string to decompose diacritical marks
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD) ;
// Remove diacritical marks by replacing non-ASCII characters
        String result = normalized.replaceAll ("\\p{M}",  "");
        System.out.println("Result: " + result);
        return result;
    }


    //Hàm thay thế khoảng trắng thành dấu _
    public static String makeSlug(String input) {
        if (input == null)
            throw new IllegalArgumentException();
        String nowhiteSpace = WHITESPACE.matcher(input).replaceAll("_");
        String normalized = Normalizer.normalize(nowhiteSpace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

}
