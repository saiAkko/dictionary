import java.util.*;
import java.util.stream.Collectors;


public class UserDictionary {
    Map <String, WordMap> words;  // interface for our dictionary

    // constructor -> interface will be assigned with HashMap
    public UserDictionary() {
        this.words = new HashMap<>();
    }

    public int countEntries(){
        return this.words.size();
    }
    public int countDefinitions(){
        int counter = 0;
        for (WordMap word : this.words.values()){
            counter += word.typeMap.size();
        }
        return counter;
    }

    // add word to the dictionary - taking word(string), word type and, meaning
    public void addWord(String word, WordType wordType, String meaning){
        WordMap foundWordMap = this.words.get(word);   // first, try to get word-to-add from dictionary
        if (foundWordMap != null){   // if exist, we will add to it's wordmap
            foundWordMap.add(wordType, meaning);
        }
        else {   // word does not exist in dictionary. create new word.
            WordMap newWordMap = new WordMap(word, wordType, meaning);
            this.words.put(word, newWordMap);
        }
    }

    // getter - get entire obj with meanings (wordmap) from dictionary given searchWord - string.
    public WordMap getWord(String word){
        return this.words.get(word);
    }

    // checker - check if word exist given searchWord
    public boolean hasWord(String word){
        if ( this.words.get(word) != null )
            return true;
        return false;
    }

    // checker - check if word with specified wordType exist (specify word and wordType)
    public boolean hasWordTagged(String word, String wordType){
        if ( this.hasWord(word) && this.getWord(word).hasType(wordType.toUpperCase()) )
            return true;
        return false;
    }


    public String[]  queryWord(String word){
        if (this.hasWord(word)){
            String[] results= this.getWord(word).getMeanings();
            Arrays.sort(results);
            return results;
        }
        return queryNotFound();
    }

    public String[]  queryWordTagged(String word, String wordType){
        if (this.hasWordTagged(word, wordType)){
            WordType queryType = WordType.valueOf(wordType.toUpperCase());
            String[] results= this.getWord(word).getMeanings(queryType);
            Arrays.sort(results);
            return results;
        }
        return queryNotFound();
    }
    public String[] queryNotFound(){
        String[] notFoundMsg = new String[] {"<NOT FOUND> To be considered for the next release. Thank you."};
        return notFoundMsg;
    }
    public String[] distinctList(String[] Duplicates){
        List<String> newList = Arrays.asList(Duplicates).stream()
                .distinct()
                .collect(Collectors.toList());
        String[] distinctArray = new String[newList.size()];
        distinctArray = newList.toArray(distinctArray);
        return distinctArray;
    }
    public String[] reverseList(String[] originalArray){
        int count = originalArray.length;
        String[] reversedArray = new String[count];

        for (int i = 0; i < count ; i++ ){
            reversedArray[i] = originalArray[ count - i - 1 ];
        }

        return reversedArray;
    }
}
