
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrettyPrinterTest {

    @Test
    public void startGameTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();
        prettyPrinter.startGame();

        String expected = """
                -------------------------
                 Welcome to Wordle Game!
                     Type q to quit
                  help to get the answer
                -------------------------
                
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void endGameWonTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();
        prettyPrinter.endGameWon();

        String expected = """
                -------------------------
                Congratulations! You won!
                -------------------------
                
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void endGameLostTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();
        prettyPrinter.endGameLost();

        String expected = """
                -------------------------
                  Game Over! You lost!
                -------------------------
                
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void askForUserInputTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();
        prettyPrinter.askForUserInput();

        String expected = """
                Enter your guess:
                
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void wordResultTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();
        WordAttempt wordAttemptMock = mock(WordAttemptImpl.class);

        when(wordAttemptMock.toString()).thenReturn("valid");
        prettyPrinter.wordResult(wordAttemptMock);

        String expected = """
                        valid
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void wordResultNullTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();

        prettyPrinter.wordResult(null);

        String expected = "";

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void allWordsResultTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();
        WordAttempt wordAttemptMock = mock(WordAttemptImpl.class);
        when(wordAttemptMock.toString()).thenReturn("valid");
        ArrayList<WordAttempt> previousWordsResults = new ArrayList<>(List.of(wordAttemptMock, wordAttemptMock));

        prettyPrinter.allWordsResult(previousWordsResults);

        String expected = """
                        valid
                        valid
                
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void allWordsResultNullTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();

        prettyPrinter.allWordsResult(null);

        String expected = "";

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void printErrorMessageTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        PrettyPrinter prettyPrinter = new PrettyPrinterImpl();

        prettyPrinter.printErrorMessage("expected");

        String expected = """
                \u001B[31mexpected\u001B[0m
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }


}
