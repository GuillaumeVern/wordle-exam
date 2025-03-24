import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WordleGameTest {
    private WordleGame wordleGame;

    @Before
    public void setUp() {
        BufferedReader mockedBufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        try {
            when(mockedBufferedReader.readLine()).thenReturn("testinput");
        } catch (IOException e) {
            System.out.println("Error reading input in Unit Test");
        }
        wordleGame = new WordleGame(mockedBufferedReader);
    }

    @Test
    public void testUserGuess() {
        WordleGame wordle = new WordleGame();
        wordle.setUserGuess("hello");
        assertEquals("hello", wordle.getUserGuess());
    }

    // the user should be able to set a guess through the console
    @Test
    public void testUserGuessInput() {


        String guess = wordleGame.userGuessInput();

        assertEquals("testinput", guess);
    }

    @Test
    public void testGameEndedIfMaxAttemptsReached(){
        wordleGame.setMaxAttempts(1);
        wordleGame.setAttempts(1);

        wordleGame.start();

        assertEquals(true, wordleGame.getGameEnded());
    }

}
