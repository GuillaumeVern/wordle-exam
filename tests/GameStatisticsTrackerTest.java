import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameStatisticsTrackerTest {
    private GameStatisticsTracker gameStatisticsTracker;

    @BeforeEach
    public void setUp() {
        gameStatisticsTracker = new GameStatisticsTrackerImpl();
    }

    @Test
    public void attemptTest() {
        gameStatisticsTracker.attempt();
        assertEquals(1, gameStatisticsTracker.getTotalAttempts());
        assertEquals(1, gameStatisticsTracker.getAverageAttempts());
    }

    @Test
    public void winTest() {
        gameStatisticsTracker.attempt();
        gameStatisticsTracker.win();
        assertEquals(1, gameStatisticsTracker.getWinsCount());
        assertEquals(1, gameStatisticsTracker.getWinsStreak());
    }

    @Test
    public void lossTest() {
        gameStatisticsTracker.attempt();
        gameStatisticsTracker.win();
        gameStatisticsTracker.attempt();
        gameStatisticsTracker.loss();
        assertEquals(2, gameStatisticsTracker.getGamesPlayed());
        assertEquals(1, gameStatisticsTracker.getWinsCount());
        assertEquals(0, gameStatisticsTracker.getWinsStreak());
    }
}
