package cz.hartrik.wordsearch.solve;

/**
 * Vytvoří výsledek z řešené osmisměrky. Přijímá výstup z 
 * {@link NumericComparator}.
 *
 * @version 2013-08-30
 * @author Patrik Harag
 */
public class Result {
    
    private final char[][] charArray;
    private final int[][] matches;

    public Result(char[][] charArray, int[][] matches) {
        if (charArray.length != matches.length ||
                charArray[0].length != matches[0].length)
            throw new IllegalArgumentException("arrays of different sizes");
        
        this.charArray = charArray;
        this.matches = matches;
    }
    
    public String createResult() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            for (int j = 0; j < charArray[0].length; j++) {
                int nextValue = matches[i][j];
                if (nextValue == 0) {
                    char nextChar = charArray[i][j];
                    if (nextChar != '.') builder.append(nextChar);
                }
            }
        }
        return builder.toString();
    }
    
}