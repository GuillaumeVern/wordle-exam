public interface GameStatisticsTracker {
    void attempt();
    void win();
    void loss();
    int getTotalAttempts();
    int getAverageAttempts();
    int getWinsCount();
    int getWinsStreak();
    int getGamesPlayed();
}
