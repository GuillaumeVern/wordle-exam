import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatisticsTrackerImpl implements GameStatisticsTracker{
    private int totalAttempts = 0;
    private int averageAttempts = 0;
    private int winsCount = 0;
    private int winsStreak = 0;
    private int gamesPlayed = 1;

    public void attempt() {
        totalAttempts++;
        averageAttempts = totalAttempts / gamesPlayed;
    }

    public void win() {
        gamesPlayed++;
        winsCount++;
        winsStreak++;
    }

    public void loss() {
        gamesPlayed++;
        winsStreak = 0;
    }

}
