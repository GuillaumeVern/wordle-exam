import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WordleGameTest {
    WordleGameImpl wordleGame;

    @BeforeEach
    public void setUp() {
        BufferedReader mockedBufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        try {
            when(mockedBufferedReader.readLine()).thenReturn("valid");
        } catch (IOException e) {
            System.out.println("Error reading input in Unit Test");
        }
        wordleGame = new WordleGameImpl(mockedBufferedReader);
        wordleGame.setUserGuess("valid");
    }

    @Test
    public void testUserGuess() {
        assertEquals("valid", wordleGame.getUserGuess());
    }

    // the user should be able to set a guess through the console
    @Test
    public void testUserGuessInput() {


        String guess = wordleGame.getUserInputFromConsole();

        assertEquals("valid", guess);
    }

    @Test
    public void testGameEndedIfMaxAttemptsReached(){
        wordleGame.setMaxAttempts(1);
        wordleGame.setValidAttempts(1);

        wordleGame.start();

        assertEquals(true, wordleGame.getGameEnded());
    }

    @Test
    public void testAttemptCounterDoesntIncrementOnInvalidWord() {
        BufferedReader mockedBufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        try {
            when(mockedBufferedReader.readLine()).thenReturn("invalid input");
        } catch (IOException e) {
            System.out.println("Error reading input in Unit Test");
        }
        wordleGame = new WordleGameImpl(mockedBufferedReader);
        wordleGame.setMaxAttempts(2);
        wordleGame.setValidAttempts(0);
        // pas besoin de répéter le test 50 fois
        wordleGame.setAttempts(49);

        wordleGame.start();

        assertEquals(0, wordleGame.getValidAttempts());
    }

}
