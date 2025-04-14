import java.util.ArrayList;

public interface PrettyPrinter {
    void startGame();
    void endGameWon();
    void endGameLost();
    void askForUserInput();
    void wordResult(WordAttempt lastWordResult);
    void allWordsResult(ArrayList<WordAttempt> previousWordsResults);
    void printErrorMessage(String message);
    void remainingAttempts(int remainingAttempts);
    void previousAttempts(ArrayList<WordAttempt> previousWordsResults);
    void printStatistics(GameStatisticsTracker statisticsTracker);
}
