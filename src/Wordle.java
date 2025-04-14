import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Wordle {
    public static GameStatisticsTracker gameStatisticsTracker = new GameStatisticsTrackerImpl();
    public static WordleGame game = new WordleGameImpl(gameStatisticsTracker);


    public static void main(String[] args) {
        while(!game.isQuit()) {
            game = new WordleGameImpl(gameStatisticsTracker);
            game.start();
        }
    }

    public static void setGame(WordleGame game) {
        Wordle.game = game;
    }

}
