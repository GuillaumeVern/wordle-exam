import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Getter @Setter
public class WordleGame {
    private String userGuess;
    private BufferedReader reader;


    // contexte par défaut, input par la console
    public WordleGame() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    // contexte de test, input par un BufferedReader mocké
    public WordleGame(BufferedReader bufferedReader) {
        this.reader = bufferedReader;
    }

    public void start() {
        System.out.println("Welcome to Wordle!");
        System.out.println("Enter your guess:");
        setUserGuess(userGuessInput());
        System.out.println("Your guess is: " + userGuess);
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
}
