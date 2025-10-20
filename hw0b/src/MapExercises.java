import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character,Integer> letterMap = new TreeMap<>();
        for(char c = 'a';c<='z';c++)
        {
            int number = c - 'a' + 1;
            letterMap.put(c,number);
        }
        return letterMap;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer,Integer> squares = new TreeMap<>();
        for(int i:nums){
            squares.put(i,i*i);
        }
        return squares;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> countWords = new TreeMap<>();
        for (String x:words){
            int value = 0;
            for(int i=0;i<words.size();i++)
            {
                if (x.equals(words.get(i))){value +=1;}
            }
            if (countWords.containsKey(x)){
                continue;
            }
            countWords.put(x,value);
        }
        return countWords;
    }
}
