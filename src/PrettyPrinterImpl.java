import java.util.ArrayList;

public class PrettyPrinterImpl implements PrettyPrinter {

    public void startGame() {
        System.out.println("""
                -------------------------
                 Welcome to Wordle Game!
                     Type q to quit
                  help to get the answer
                -------------------------
                """);
    }

    public void endGameWon() {
        System.out.println("""
                -------------------------
                Congratulations! You won!
                -------------------------
                """);
    }

    public void endGameLost() {
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

        System.out.print("        ");
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
        System.out.println("\u001B[31m" + message + "\u001B[0m");
    }

    public void remainingAttempts(int remainingAttempts) {
        System.out.printf("""
                -------------------------
                  Remaining attempts: %s
                -------------------------
                %n""", remainingAttempts);
    }

    public void previousAttempts(ArrayList<WordAttempt> previousWordsResults) {
        if (previousWordsResults.isEmpty()) {
            return;
        }

        System.out.println("""
                -------------------------
                    Previous attempts
                """);
         allWordsResult(previousWordsResults);
        System.out.println("-------------------------");
    }

    public void printStatistics(GameStatisticsTracker statisticsTracker) {
        System.out.printf("""
                -------------------------
                     Session stats:
                    Total attempts: %s
                  Average attempts: %s
                              Wins: %s
                        Win streak: %s
                      Games played: %s
                -------------------------
                """,
                statisticsTracker.getTotalAttempts(),
                statisticsTracker.getAverageAttempts(),
                statisticsTracker.getWinsCount(),
                statisticsTracker.getWinsStreak(),
                statisticsTracker.getGamesPlayed() - 1
        );
    }

}
