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
    private Boolean gameEnded = false;


    // contexte par défaut, input par la console
    public WordleGameImpl() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    // contexte de test, injection d'un BufferedReader mocké
    public WordleGameImpl(BufferedReader bufferedReader) {
        this.reader = bufferedReader;
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
        String guess;
        try {
            guess = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error reading input");
            guess = "";
        }
        return guess;
    }

    private void playTurn() {
        System.out.println("Enter your guess:");
        setUserGuess(getUserInputFromConsole());
        if(checkUserGuessIsValid()) {
            // only increment attempts if the guess is valid
            validAttempts++;
            System.out.println("Your guess is: " + userGuess);
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
