import java.util.ArrayList;

public interface PrettyPrinter {
    void startGame();
    void endGameWon();
    void endGameLost();
    void askForUserInput();
    void wordResult(WordAttempt lastWordResult);
    void allWordsResult(ArrayList<WordAttempt> previousWordsResults);
    void printErrorMessage(String message);
}
