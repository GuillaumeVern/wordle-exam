import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class WordleGameTest {
    @Test
    public void testUserGuess() {
        WordleGame wordle = new WordleGame();
        wordle.setUserGuess("hello");
        assertEquals("hello", wordle.getUserGuess());
    }

    // the user should be able to set a guess through the console
    @Test
    public void testUserGuessInput() {
        BufferedReader mockedBufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        try {
            when(mockedBufferedReader.readLine()).thenReturn("testinput");
        } catch (IOException e) {
            System.out.println("Error reading input in Unit Test");
        }
        WordleGame wordle = new WordleGame(mockedBufferedReader);

        String guess = wordle.userGuessInput();

        assertEquals("testinput", guess);
    }

}
