import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatisticsTrackerImpl implements GameStatisticsTracker{
    private int totalAttempts = 0;
    private int averageAttempts = 0;
    private int winsCount = 0;
    private int winsStreak = 0;
    private int gamesPlayed = 0;

    public void attempt() {
        totalAttempts++;
        computeAverageAttempts();
    }

    public void win() {
        gamesPlayed++;
        winsCount++;
        winsStreak++;
        computeAverageAttempts();
    }

    public void loss() {
        gamesPlayed++;
        winsStreak = 0;
        computeAverageAttempts();
    }

    private void computeAverageAttempts() {
        if (gamesPlayed > 0) {
            averageAttempts = totalAttempts / gamesPlayed;
        } else {
            averageAttempts = 1;
        }
    }

}
