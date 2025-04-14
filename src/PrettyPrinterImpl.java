import java.util.ArrayList;

public class PrettyPrinterImpl implements PrettyPrinter {

    public void startGame() {
        System.out.println("""
                -------------------------
                 Welcome to Wordle Game!
                -------------------------
                """);
    }

    public void endGameWon() {
        clear();
        System.out.println("""
                -------------------------
                Congratulations! You won!
                -------------------------
                """);
    }

    public void endGameLost() {
        clear();
        System.out.println("""
                -------------------------
                  Game Over! You lost!
                -------------------------
                """);
    }

    public void askForUserInput() {
        System.out.println("""
                Enter your guess:
                """);
    }

    public void wordResult(WordAttempt lastWordResult) {
        if (lastWordResult == null) {
            return;
        }

        System.out.print(lastWordResult.toString());
        System.out.println();
    }

    public void allWordsResult(ArrayList<WordAttempt> previousWordsResults) {
        if (previousWordsResults == null || previousWordsResults.isEmpty()) {
            return;
        }

        for (WordAttempt wordResult : previousWordsResults) {
            wordResult(wordResult);
        }
        System.out.println();
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

    public void clear() {
        System.out.println("""
                
                """.repeat(50));
    }
}
