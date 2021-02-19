import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum WordType {
    NOUN,
    VERB,
    ADJECTIVE,
    ADVERB,
    CONJUNCTION,
    INTERJECTION,
    PREPOSITION,
    PRONOUN;
    public static String[] REFERENCE = new String[] {"NOUN", "VERB", "ADJECTIVE", "ADVERB", "CONJUNCTION", "INTERJECTION", "PREPOSITION", "PRONOUN"};
    static boolean check(String s)
    {
        Set<String> wordTypes = new HashSet<>(Arrays.asList(REFERENCE));
        return wordTypes.contains(s);
    }
}
