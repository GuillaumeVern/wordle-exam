
public class Wordle {



    public static void main(String[] args) {
        GameStatisticsTrackerImpl gameStatisticsTracker = new GameStatisticsTrackerImpl();
        WordleGameImpl game = new WordleGameImpl(gameStatisticsTracker);
        while(!game.isQuit()) {
            game = new WordleGameImpl(gameStatisticsTracker);
            game.start();
        }
    }


}
