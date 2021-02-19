import java.util.Scanner;

public class DictionaryLauncher {
    public static void processInput(String userInput, UserDictionary myDictionary){

        System.out.println(userInput);  // to show output in testing . Need to comment for release.
        userInput = userInput.toLowerCase();
        String[] parts = userInput.split("\\s+");
        String[] results = new String[0];
        boolean isReverse, isDistinct;
        isDistinct = isReverse = false;

        String searchWord = parts[0];
        String helpMsg = "|\n" +
                "PARAMETER HOW-TO, please enter:\n" +
                "1. A search key -then 2. An optional part of speech -then\n" +
                "3. An optional 'distinct' -then 4. An optional 'reverse'\n" +
                "|";
        String notFoundMsg = "|\n<NOT FOUND> To be considered for the next release. Thank you.\n|";

        if (parts.length==1){
            switch(searchWord){
                case "!help","" ->  System.out.println(helpMsg);
                case "!q","!Q" -> System.out.println("-----THANK YOU-----");
                default -> {
                    if (myDictionary.hasWord(searchWord)) {  // Check if dictionary contains word
                        results = myDictionary.queryWord(searchWord);
                        System.out.println("|");
                        for (String item : results){
                            System.out.println(item);
                        }
                        System.out.println("|");
                    }
                    else {  // if not found, print error
                        System.out.println(notFoundMsg);
                        System.out.println(helpMsg);
                        }
                }
            }
            return;
        }
        else if (parts.length > 4){
            System.out.println(helpMsg);
            return;
        }
        else {   // User input has 2 to 4 parts
            // First part is assumed to be searchWord (= parts[0] )
            if (!myDictionary.hasWord(searchWord)) {  // if word doesn't exist, error and return.
                System.out.println(notFoundMsg);
                System.out.println(helpMsg);
                return;
            }
            
            // Test the second part - parts[1] - for WordType
            String secondPart = parts[1].toUpperCase();
            if (WordType.check(secondPart)){
                if (!myDictionary.getWord(searchWord).hasType(secondPart)){ // if word (type) doesn't exist, error and return.
                    System.out.println(notFoundMsg);
                    System.out.println(helpMsg);
                    return;
                }
                results = myDictionary.queryWordTagged(searchWord, secondPart);  // otherwise results are saved
            }
            else {
                results = myDictionary.queryWord(searchWord);  // word exist but no type was specified. query all types.
                switch (secondPart) {  // then secondPart is either valid or erroneous command.
                    case "REVERSE" -> isReverse = true;
                    case "DISTINCT" -> isDistinct = true;
                    default -> {
                        results = myDictionary.queryWord(searchWord);
                        System.out.print(String.format(
                                "|\n<The entered 2nd parameter '%s' is NOT a part of speech.>\n"
                                        + "<The entered 2nd parameter '%s' is NOT 'distinct'.>\n"
                                        + "<The entered 2nd parameter '%s' is NOT 'reverse'.>\n"
                                        + "<The entered 2nd parameter '%s' was disregarded.>\n"
                                        + "<The 2nd parameter should be a part of speech or 'distinct' or 'reverse'.>\n|\n"
                                , parts[1], parts[1], parts[1], parts[1]));
                    }

                }
            }

            // Test the third part - parts[2] - only if parts[] has 3 or more
            // includes parts == 4 because (if parts == 4 ) regardless of parts== 3 or 4, third part is processed.
            // the next case parts == 4 will not process the third part.
            if (parts.length >= 3) {
                switch (parts[2].toUpperCase()) {
                    case "REVERSE" -> isReverse = true;
                    case "DISTINCT" -> isDistinct = true;
                    default -> {
                        System.out.print(String.format(
                                "|\n<The entered 3rd parameter '%s' is NOT 'distinct'.>\n"
                                        + "<The entered 3rd parameter '%s' is NOT 'reverse'.>\n"
                                        + "<The entered 3rd parameter '%s' was disregarded.>\n"
                                        + "<The 3rd parameter should be 'distinct' or 'reverse'.>\n|\n"
                                , parts[2], parts[2], parts[2]));
                    }
                }
            }

            // test the 4th part. Only process the fourth part. third part is processed in previous case.
            // "distinct" keyword is illegal in 4th
            if (parts.length == 4) {
                if (parts[3].toUpperCase().equals("REVERSE")) {
                    isReverse = true;
                }
                else {
                    System.out.print(String.format(
                            "|\n<The entered 4th parameter '%s' is NOT 'reverse'.>\n" +
                                    "<The entered 4th parameter '%s' was disregarded.>\n" +
                                    "<The 4th parameter should be 'reverse'.>\n|\n", parts[3],parts[3], parts[3]));
                }
            }

        }
        // Further processings on the output depending on the choices of "REVERSE" or "DISTINCT"
        if (isDistinct){
            results = myDictionary.distinctList(results);
        }
        if (isReverse) {
            results = myDictionary.reverseList(results);
        }
        // Finally print out the results
        System.out.println("|");
        for (String item : results){
            System.out.println(item);
        }
        System.out.println("|");
    }
    public static void main(String[] args) {

        // Create and initialize dictionary using factory class/method Dictionary.
        System.out.println("! Loading data...");
        DictionaryFactory dictionaryFactory=new DictionaryFactory();
        UserDictionary myDictionary =  dictionaryFactory.createFromFile("dictionary.txt");
        System.out.println("! Loading completed...\n");
        // loading ends

        // Welcome Message
        String welcomeMsg =String.format(
                "===== DICTIONARY 340 JAVA =====\n" +
                "----- Keywords: %d\n" +
                "----- Definitions: %d\n", myDictionary.countEntries(),myDictionary.countDefinitions());
        System.out.println(welcomeMsg);

        // user-entry begins
        Scanner userInput = new Scanner(System.in);
        String quitWord = "!q", inputString = "";
        int counter = 0;

        // while loop to accept user input. call processInput static method. quit if '!q' is entered.
        while (!inputString.toLowerCase().equals(quitWord)  ){
            counter += 1;
            System.out.print("Search [" + counter +"]: ");
            inputString = userInput.nextLine().trim();
            processInput(inputString,myDictionary);
        }
    }
}
