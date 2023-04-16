
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Make a chain of words
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        reader.close();

        FileInputStream fileInputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String oneStringFromFile = "";
        while (bufferedReader.ready()) {
            oneStringFromFile = oneStringFromFile.concat(bufferedReader.readLine().concat(" "));
        }
        oneStringFromFile = oneStringFromFile.trim();

        String[] strings = oneStringFromFile.split(" ");
        
        StringBuilder result = getLine(strings);
        System.out.println(result);
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) return result;
        
        String[] wordsClone;
        StringBuilder result = new StringBuilder();
        StringBuilder buffer;
        
        for (int c = 0; c < wordsClone.length; c++) {
            wordsClone = words.clone();
            buffer = new StringBuilder();
            String string = wordsClone[c];
            wordsClone[c] = "";
            buffer.append(string).append(" ");

            for (int i = 0; i < wordsClone.length; i++) {
                if (wordsClone[i].length() != 0 && string.length() != 0) {

                    char last = string.charAt(string.length() - 1);
                    char first = wordsClone[i].charAt(0);
                    
                    //start learning how the program works from here:
                    if (Objects.equals(Character.toLowerCase(last), Character.toLowerCase(first))) {
                        buffer.append(wordsClone[i]).append(" ");
                        string = wordsClone[i];
                        wordsClone[i] = "";
                        i = -1;
                    }
                }
            }
            
            //try to find the best result that covers the maximum number 
            //of matching words:
            
            if (buffer.length() > result.length()) {
                result = buffer;
            }
        }

        //according to the assignment condition, all words from the file 
        //must be added to the result, including those 
        //that are not suitable for the compiled list
        
        ArrayList<String> wordsArrayList = new ArrayList<>();
        Collections.addAll(wordsArrayList, words);
        ArrayList<String> resultArrayList = new ArrayList<>();
        Collections.addAll(resultArrayList, result.toString().split(" "));

        for (String s : resultArrayList) {
            wordsArrayList.remove(s);
        }

        for (String s : wordsArrayList) {
            result.append(s.concat(" "));
        }

        result.deleteCharAt(result.length() - 1);
        return result;
    }
}
