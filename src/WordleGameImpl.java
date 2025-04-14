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
    private int remainingAttempts = maxAttempts;
    private int validAttempts = 0;
    private int attempts = 0;
    private boolean turnEnded = false;
    private boolean gameWon = false;
    private String wordOfTheDay;
    private WordsDictionary wordsDictionary;
    private WordAttempt validationResults;
    private ArrayList<WordAttempt> previousValidationsResults;
    private PrettyPrinter prettyPrinter;
    private WordValidator wordValidator;
    private GameStatisticsTracker gameStatisticsTracker;
    private boolean quit = false;


    // contexte par défaut, input par la console
    public WordleGameImpl() {
        this(new BufferedReader(new InputStreamReader(System.in)), new WordsDictionaryImpl(), new PrettyPrinterImpl());
    }

    public WordleGameImpl(GameStatisticsTracker gameStatisticsTracker) {
        this(new BufferedReader(new InputStreamReader(System.in)), new WordsDictionaryImpl(), new PrettyPrinterImpl(), gameStatisticsTracker);
    }

    public WordleGameImpl(BufferedReader reader, WordsDictionary wordsDictionary, PrettyPrinter prettyPrinter) {
        this(reader, wordsDictionary, prettyPrinter, new GameStatisticsTrackerImpl());
    }

    public WordleGameImpl(BufferedReader reader, WordsDictionary wordsDictionary, PrettyPrinter prettyPrinter, GameStatisticsTracker gameStatisticsTracker) {
        this.reader = reader;
        this.wordsDictionary = wordsDictionary;
        this.wordOfTheDay = wordsDictionary.getWordOfTheDay();
        this.prettyPrinter = prettyPrinter;
        this.previousValidationsResults = new ArrayList<>();
        this.wordValidator = new WordValidatorImpl();
        this.gameStatisticsTracker = gameStatisticsTracker;
    }

    public void start() {

        prettyPrinter.startGame();
        checkTurnEnded();
        while (!turnEnded && !quit) {
            playTurn();
            attempts++;
            checkTurnEnded();
        }


        if (quit) {
            prettyPrinter.printStatistics(gameStatisticsTracker);
            return;
        }
        if (gameWon) {
            prettyPrinter.endGameWon();
            gameStatisticsTracker.win();
        } else {
            prettyPrinter.endGameLost();
            gameStatisticsTracker.loss();
        }
        prettyPrinter.printStatistics(gameStatisticsTracker);
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
        switch (userGuess) {
            case "q":
                quit = true;
                return;
            case "help":
                System.out.println(wordOfTheDay);
                break;
            default:
                if(checkUserGuessIsValid()) {
                    // only increment attempts if the guess is valid
                    validAttempts++;
                    gameStatisticsTracker.attempt();
                    validationResults = wordValidator.getValidationResults(wordOfTheDay, userGuess);
                    previousValidationsResults.add(validationResults);
                    prettyPrinter.previousAttempts(previousValidationsResults);
                    prettyPrinter.remainingAttempts(maxAttempts - validAttempts);
                    checkGameWon();
                }
                break;
        }

    }

    private void checkGameWon() {
        if (wordOfTheDay.equals(userGuess)) {
            gameWon = true;
            turnEnded = true;
        }
    }

    private void checkTurnEnded() {
        if (validAttempts >= maxAttempts || attempts >= hardLimitMaxAttempts) {
            turnEnded = true;
        }
    }

    private boolean checkUserGuessIsValid() {
        return wordValidator.isValid(userGuess);
    }

}
