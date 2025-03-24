import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordValidatorTest {

    @Test
    public void testWordIsValid() {
        boolean result = WordValidator.isValid("hello");
        assertTrue(result);
    }

    @Test
    public void testWordIsExaclyFiveLetters() {
        boolean result = WordValidator.isValid("helloo");
        assertFalse(result);
    }

    @Test
    public void testWordIsNotNull() {
        boolean result = WordValidator.isValid(null);
        assertFalse(result);
    }

    @Test
    public void testWordNoNumbers() {
        boolean result = WordValidator.isValid("hell2");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpecialCharacters() {
        boolean result = WordValidator.isValid("hell!");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpaces() {
        boolean result = WordValidator.isValid("hel o");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpacesStart() {
        boolean result = WordValidator.isValid(" ello");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpacesEnd() {
        boolean result = WordValidator.isValid("hell ");
        assertFalse(result);
    }


}
