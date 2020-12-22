package hangman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Jogo
 *
 * @author POO 2019/2020
 * @version june/2020
 */
public class Game extends HBox {

    private final WordGenerator GENERATOR = new WordGenerator();
    private int numberOfTries = 0;
    private String hiddenWord;
    private String guessedWord = "";
    private Text[] displayGuessedWord;

    public Game() {
        createContent();
    }
    
    private void createContent() {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(60));

        hiddenWord = GENERATOR.generateWord();
        displayGuessedWord = new Text[hiddenWord.length()];
        
        for (int i = 0; i < hiddenWord.length(); i++) {
            guessedWord += "_";
            Text guessedLetter = new Text("_");
            displayGuessedWord[i] = guessedLetter;
            guessedLetter.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
            
            getChildren().add(guessedLetter);
        }
    }

    public boolean testLetter(String stringLetter) {
        numberOfTries += 1;
        
        char letter = stringLetter.charAt(0);
        char[] wordArray = guessedWord.toCharArray();
        
        if (!guessedWord.contains(stringLetter) && hiddenWord.contains(stringLetter)) {
            for (int i = 0; i < guessedWord.length(); i++) {
                if ((letter == hiddenWord.charAt(i)) && (letter != guessedWord.charAt(i))) {
                    wordArray[i] = letter;
                }
            }
            
            updateGuessedWord(wordArray);
            return true;
        }

        return false;
    }
    
    private void updateGuessedWord(char[] wordArray) {
        guessedWord = String.valueOf(wordArray);
        for (int i = 0; i < displayGuessedWord.length; i++) {
            displayGuessedWord[i].setText("" + guessedWord.charAt(i));
        }
    }
    
    public boolean isComplete() {
        return guessedWord.equals(hiddenWord);
    }

    public int getNumberOfTries() {
        return numberOfTries;
    }

    public String getHiddenWord() {
        return hiddenWord;
    }

    public String getGuessedWord() {
        return guessedWord;
    }

}
