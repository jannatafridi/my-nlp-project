import java.util.ArrayList;
import java.util.Scanner;

public class MyNLP {

  /** 
  * Create 1-2 meaningful ArrayList 
  */
private ArrayList<String> thesaurus;
  private String userInput;
  private ArrayList<String> improvedSentence;

  // TODO: assigned initial values for your instance variables
  /**
  * onstructor to initialize instance vars
  */ 
  public MyNLP() {
    thesaurus = FileReader.toStringList("synonyms.txt"); //load synonyms from file
    improvedSentence = new ArrayList<String>();
  }
/**
* converts the user input to a list of words and replaces with stronger synonyms
*/ 
  public void userInputToList() {
    String[] userWords = userInput.split(" ");
    for (String word : userWords) {
      String cleanedWord = removePunctuation(word);
      //remove punctuation & numbers
      cleanedWord = removeNumbers(cleanedWord);
      improvedSentence.add(cleanedWord);
    }
  }
  public String removePunctuation(String word) {
    String[] symbols = {".", ",", "!", "?", "/", "-"};
    for (String s : symbols) {
      word = word.replace(s, "");
    }
    return word;
  }
     // TODO: remove all numbers from the word
   public String removeNumbers(String word) {
    String[] numbers = {"0", "1", "2", "3", "4", "5", "6","7", "8", "9"};
    for (String n : numbers) {
      word = word.replace(n,"");
    }
  return word;
  }
  /**
   * Javadoc example
   * @param parameter1 the first parameter
   * @return what is returned from this method
   */

/**
* replace original word with stronger synonym
*/
  public String replaceWord(String word) {
    /**
    *iterate over the synonyms, if word, replace with random synonm
    */
  for (String synonymList : thesaurus) {
      String[] synonyms = synonymList.split(","); 
    /**
    * finds the weak word and searches for new word
    */
      if (synonyms[0].equals(word) && synonyms.length > 1) { 
        int randomIndex = (int) (Math.random() * synonyms.length);
        /**
        * we dont want a zero bc the original word is at index 0
        */ 
        while (randomIndex == 0) {
          randomIndex = (int) (Math.random() * synonyms.length);
        }
        /**
        * replace weak word with a random synonym found
        */
        return synonyms[randomIndex]; 
      }
    }
    /**
    * return original word if no synonym is found
    */
    return word; 
  }
  
  
  public void improve() {
    /**
   * iterate overs the improvedSentence and use replaceWord
   */
  for (int i = 0; i < improvedSentence.size(); i++) {
    String word = improvedSentence.get(i);
    String replacedWord = replaceWord(word);
    improvedSentence.set(i, replacedWord); //replace word with synonym
  }
  }
  
  
  /**
   * Starts the app and gets user input
   */
  public void prompt() {
    Scanner input = new Scanner(System.in);

    System.out.println("Type in your sentence to strengthen: ");
    userInput = input.nextLine();
    userInputToList(); // process user input
    improve();
    printSummary();
    
    input.close();
  }

  /**
   * Prints the summary of my NLP project
   */
  public void printSummary() {
    System.out.println("Your sentence: ");
      System.out.println(userInput);

      System.out.println("Improved Sentence: ");
    String betterSentence = "";
    for (String word : improvedSentence) {
      betterSentence += word + " ";
    }
    System.out.println(betterSentence);

  }


}