
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrettyPrinterTest {
    private ByteArrayOutputStream outContent;
    PrettyPrinter prettyPrinter;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        prettyPrinter = new PrettyPrinterImpl();
    }

    @Test
    public void startGameTest() {
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
        prettyPrinter.askForUserInput();

        String expected = """
                Enter your guess:
                
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void wordResultTest() {
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

        prettyPrinter.wordResult(null);

        String expected = "";

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void allWordsResultTest() {
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

        prettyPrinter.allWordsResult(null);

        String expected = "";

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void printErrorMessageTest() {

        prettyPrinter.printErrorMessage("expected");

        String expected = """
                \u001B[31mexpected\u001B[0m
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void remainingAttemptsTest() {

        prettyPrinter.remainingAttempts(3);

        String expected = """
                -------------------------
                  Remaining attempts: 3
                -------------------------
                
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void previousAttemptsTest() {
        WordAttempt wordAttemptMock = mock(WordAttemptImpl.class);
        when(wordAttemptMock.toString()).thenReturn("valid");
        ArrayList<WordAttempt> previousWordsResults = new ArrayList<>(List.of(wordAttemptMock, wordAttemptMock));

        prettyPrinter.previousAttempts(previousWordsResults);

        String expected = """
                -------------------------
                    Previous attempts
                
                        valid
                        valid
                
                -------------------------
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }

    @Test
    public void printStatisticsTest() {
        GameStatisticsTracker gameStatisticsTrackerMock = mock(GameStatisticsTracker.class);
        when(gameStatisticsTrackerMock.getTotalAttempts()).thenReturn(0);
        when(gameStatisticsTrackerMock.getAverageAttempts()).thenReturn(0);
        when(gameStatisticsTrackerMock.getWinsCount()).thenReturn(0);
        when(gameStatisticsTrackerMock.getWinsStreak()).thenReturn(0);
        when(gameStatisticsTrackerMock.getGamesPlayed()).thenReturn(0);

        prettyPrinter.printStatistics(gameStatisticsTrackerMock);

        String expected = """
                -------------------------
                     Session stats:
                    Total attempts: 0
                  Average attempts: 0
                              Wins: 0
                        Win streak: 0
                      Games played: 0
                -------------------------
                """;

        assertEquals(expected, outContent.toString().replace("\r",""));
    }


}
