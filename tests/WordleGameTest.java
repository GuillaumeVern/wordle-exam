import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class WordleGameTest {
    WordleGame wordleGame;

    @BeforeEach
    public void setUp() {
        BufferedReader mockedBufferedReader = org.mockito.Mockito.mock(BufferedReader.class);
        try {
            when(mockedBufferedReader.readLine()).thenReturn("valid");
        } catch (IOException e) {
            System.out.println("Error reading input in Unit Test");
        }
        wordleGame = new WordleGameImpl(mockedBufferedReader);
    }

    // the user should be able to set a guess through the console
    @Test
    public void testUserGuessInput() {
        String guess = wordleGame.getUserInputFromConsole();

        assertEquals("valid", guess);
    }

    @Test
    public void testGameEndedIfMaxAttemptsReached(){
        wordleGame.start();

        assertTrue(wordleGame.isGameEnded());
    }


}
