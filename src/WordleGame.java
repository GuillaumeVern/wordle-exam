import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter @Setter
public class WordleGame {
    private String userGuess;
    private BufferedReader reader;
    private int maxAttempts = 5;
    private int attempts = 0;
    private Boolean gameEnded = false;


    // contexte par défaut, input par la console
    public WordleGame() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    // contexte de test, injection d'un BufferedReader mocké
    public WordleGame(BufferedReader bufferedReader) {
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

    public String userGuessInput() {
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
        setUserGuess(userGuessInput());
        System.out.println("Your guess is: " + userGuess);
    }

    private void updateGameEnded() {
        if (attempts >= maxAttempts) {
            gameEnded = true;
        }
    }
}
