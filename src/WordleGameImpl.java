import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Getter @Setter
public class WordleGameImpl implements WordleGame {
    private String userGuess;
    private BufferedReader reader;
    private int hardLimitMaxAttempts = 50;
    private int maxAttempts = 5;
    private int validAttempts = 0;
    private int attempts = 0;
    private boolean gameEnded = false;
    private boolean gameWon = false;
    private String wordOfTheDay;
    private WordsDictionary wordsDictionary;
    private WordAttempt validationResults;
    private ArrayList<WordAttempt> previousValidationsResults;
    private PrettyPrinter prettyPrinter;


    // contexte par défaut, input par la console
    public WordleGameImpl() {
        this(new BufferedReader(new InputStreamReader(System.in)), new WordsDictionaryImpl(), new PrettyPrinterImpl());
    }

    public WordleGameImpl(BufferedReader reader, WordsDictionary wordsDictionary, PrettyPrinter prettyPrinter) {
        this.reader = reader;
        this.wordsDictionary = wordsDictionary;
        this.wordOfTheDay = wordsDictionary.getWordOfTheDay();
        this.prettyPrinter = prettyPrinter;
        this.previousValidationsResults = new ArrayList<>();
    }

    public void start() {
        prettyPrinter.startGame();
        checkGameEnded();


        while (!gameEnded) {
            playTurn();
            attempts++;
            checkGameEnded();
        }


        if (gameWon) {
            prettyPrinter.endGameWon();
        } else {
            prettyPrinter.endGameLost();
        }
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
        prettyPrinter.askForUserInput();
        setUserGuess(getUserInputFromConsole());
        if(checkUserGuessIsValid()) {
            // only increment attempts if the guess is valid
            validAttempts++;
            validationResults = WordValidator.getValidationResults(wordOfTheDay, userGuess);
            System.out.println("previous attempts:");
            prettyPrinter.allWordsResult(previousValidationsResults);
            // TODO: remove wod display
            System.out.println(wordOfTheDay);
            prettyPrinter.wordResult(validationResults);
            previousValidationsResults.add(validationResults);

            checkGameWon();
        }
    }

    private void checkGameWon() {
        if (wordOfTheDay.equals(userGuess)) {
            gameWon = true;
            gameEnded = true;
        }
    }

    private void checkGameEnded() {
        if (validAttempts >= maxAttempts || attempts >= hardLimitMaxAttempts) {
            gameEnded = true;
        }
    }

    private boolean checkUserGuessIsValid() {
        return WordValidator.isValid(userGuess);
    }

}
