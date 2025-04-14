import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class WordleTest {

    @Test
    public void wordleTest() {
        WordleGame wordleGameMock = mock(WordleGame.class);
        when(wordleGameMock.isQuit()).thenReturn(true);
        Wordle.setGame(wordleGameMock);
        Wordle.main(null);
    }

}
