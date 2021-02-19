
import java.util.Scanner; // for reading files
import java.io.File;  // for input files
import java.io.FileNotFoundException;  // To handle errors


public class DictionaryFactory {
    WordType wordTypeReference;
    UserDictionary createFromFile(String fileName)   {
        UserDictionary newDictionary = new UserDictionary();
        try{
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            String[] tokens;
            String[] parts;
            WordType wordType;
            String definition;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tokens = data.split(",", -1);
                String entryWord = tokens[0];
                parts = tokens[1].split("%");
                wordType= wordTypeReference.valueOf(parts[0].toUpperCase());
                definition= parts[1];
                newDictionary.addWord(entryWord, wordType, definition);

                for (int i=2; i< tokens.length; i++) {
                    parts = tokens[i].split("%");
                    wordType= wordTypeReference.valueOf(parts[0].toUpperCase());
                    definition= parts[1];
                    newDictionary.addWord(entryWord, wordType, definition);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return newDictionary;
    }
}
