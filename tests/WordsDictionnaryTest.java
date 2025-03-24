import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordsDictionnaryTest {
    WordsDictionary wordsDictionary;

    @BeforeEach
    public void setUp() {
        // on mock la classe FileLoader pour ne pas charcher les fichiers de 10000 lignes à chaque test
        FileLoader mockedFileLoader = mock(FileLoader.class);
        when(mockedFileLoader.loadWordsFromFile(new File("dictionaries/wordle-La.txt"))).thenReturn(new ArrayList<>());
        when(mockedFileLoader.loadWordsFromFile(new File("dictionaries/wordle-Ta.txt"))).thenReturn(new ArrayList<>());
        wordsDictionary = new WordsDictionaryImpl();

    }

    @Test
    public void testGetWordOfTheDayNotNull() {
        WordsDictionary wordsDictionnary = new WordsDictionaryImpl();
        String word = wordsDictionnary.getWordOfTheDay();
        assertNotNull(word);
    }

    @Test
    public void testGetWordOfTheDayIsString() {
        WordsDictionary wordsDictionnary = new WordsDictionaryImpl();
        String word = wordsDictionnary.getWordOfTheDay();
        assertEquals(String.class, word.getClass());
    }

    @Test
    public void testCheckUserInputLaIsValid() {
        WordsDictionary wordsDictionary = new WordsDictionaryImpl();
        boolean result = wordsDictionary.checkUserInputWordIsValid("hello");
        assertTrue(result);
    }

    @Test
    public void testCheckUserInputTaIsValid() {
        WordsDictionary wordsDictionary = new WordsDictionaryImpl();
        boolean result = wordsDictionary.checkUserInputWordIsValid("abask");
        assertTrue(result);
    }

}
