import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter @Setter
public class WordleGameImpl implements WordleGame {
    private String userGuess;
    private BufferedReader reader;
    private int hardLimitMaxAttempts = 50;
    private int maxAttempts = 5;
    private int validAttempts = 0;
    private int attempts = 0;
    private boolean gameEnded = false;
    private String wordOfTheDay;
    private WordsDictionary wordsDictionary;
    private EnumCharValidationState[] validationResults;


    // contexte par défaut, input par la console
    public WordleGameImpl() {
        this(new BufferedReader(new InputStreamReader(System.in)), new WordsDictionaryImpl());
    }

    // contexte de test, injection d'un BufferedReader mocké
    public WordleGameImpl(BufferedReader reader) {
        this(reader, new WordsDictionaryImpl());
    }

    public WordleGameImpl(WordsDictionary wordsDictionary) {
        this(new BufferedReader(new InputStreamReader(System.in)), wordsDictionary);
    }

    public WordleGameImpl(BufferedReader reader, WordsDictionary wordsDictionary) {
        this.reader = reader;
        this.wordsDictionary = wordsDictionary;
        this.wordOfTheDay = wordsDictionary.getWordOfTheDay();
    }

    public void start() {
        System.out.println("Welcome to Wordle!");
        updateGameEnded();
        while (!gameEnded) {
            playTurn();
            attempts++;
            updateGameEnded();
        }
        System.out.println("Game ended.");
    }

    public String getUserInputFromConsole() {
        String guess = null;
        try {
            guess = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input");
        }
        return guess;
    }

    private void playTurn() {
        System.out.println("Enter your guess:");
        setUserGuess(getUserInputFromConsole());
        if(checkUserGuessIsValid()) {
            // only increment attempts if the guess is valid
            validAttempts++;

            // TODO: check word against dictionary here
            System.out.println("Your guess is: " + userGuess);

            validationResults = WordValidator.getValidationResults(wordOfTheDay, userGuess);
            for (EnumCharValidationState result : validationResults) {
                System.out.print(result.getSymbol());
            }
        }
    }

    private void updateGameEnded() {
        if (validAttempts >= maxAttempts || attempts >= hardLimitMaxAttempts) {
            gameEnded = true;
        }
    }

    private boolean checkUserGuessIsValid() {
        return WordValidator.isValid(userGuess);
    }

}
