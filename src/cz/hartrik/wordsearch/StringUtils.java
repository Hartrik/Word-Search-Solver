package cz.hartrik.wordsearch;

/**
 * Nástroje pro parsování vstupů.
 * 
 * @version 2013-09-25
 * @author Patrik Harag
 */
public class StringUtils {

    private StringUtils() {}
    
    public static String[] parseDictionary(String string) {
        string = string.replaceAll("[\\s]", ",").replaceAll(",{2,}", ",");
        string = string.toLowerCase();
        if (string.startsWith(",")) string = string.substring(1);
        return string.split(",");
    }
    
    public static char[][] parseWordSearch(String string) {
        String[] lines = clearWordSearch(string);
        
        char[][] array = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) array[i] = lines[i].toCharArray();
        return array;
    }
    
    public static String[] clearWordSearch(String string) {
        string = string.trim().toLowerCase();
        string = string.replaceAll("\n", "<line>").replaceAll("[\\s]", "");
        String[] lines = string.split("<line>");

        int maxLength = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].replaceAll("\\.*$", "");
            lines[i] = line;
            
            int length = line.length();
            if (length > maxLength) maxLength = length;
        }
        
        for (int i = 0; i < lines.length; i++) {
            int length = lines[i].length();
            if (length < maxLength) {
                lines[i] = appendNewStringToExisting
                        (lines[i], ".", maxLength - length);
            }
        }
        return lines;
    }
    
    public static String fold(String[] strings, String separator) {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            if (first) {
                first = false; sb.append(string);
            } else sb.append(separator).append(string);
        }
        return sb.toString();
    }
    
    public static int maxLength(String[] lines) {
        int maxLength = 0;
        for (String line : lines) {
            final int length = line.length();
            if (length > maxLength) maxLength = length;
        }
        return maxLength;
    }
    
    public static String appendNewStringToExisting(String exisitingString,
            String newString, int number) {
        
        StringBuilder builder = new StringBuilder(exisitingString);
        for (int i = 0; i < number; i++) builder.append(newString);
        return builder.toString();
    }
    
}