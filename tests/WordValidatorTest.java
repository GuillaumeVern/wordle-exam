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

    @Test
    public void testExactMatchWord() {
        WordAttempt results = WordValidator.getValidationResults("hello", "hello");
        EnumCharValidationState[] expected = {EnumCharValidationState.EXACT_MATCH,
                EnumCharValidationState.EXACT_MATCH,
                EnumCharValidationState.EXACT_MATCH,
                EnumCharValidationState.EXACT_MATCH,
                EnumCharValidationState.EXACT_MATCH};
        WordAttempt wordAttempt = new WordAttemptImpl();
        wordAttempt.setValidationResults(expected);
        assertArrayEquals(wordAttempt.getValidationResults(), results.getValidationResults());
    }

    @Test
    public void testExactMatchLetter() {
        WordAttempt results = WordValidator.getValidationResults("hello", "hffff");
        EnumCharValidationState[] expected = {EnumCharValidationState.EXACT_MATCH,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD};
        WordAttempt wordAttempt = new WordAttemptImpl();
        wordAttempt.setValidationResults(expected);
        assertArrayEquals(wordAttempt.getValidationResults(), results.getValidationResults());
    }

    @Test
    public void testMatchLetterInWord() {
        WordAttempt results = WordValidator.getValidationResults("hello", "ffffh");
        EnumCharValidationState[] expected = {EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.IN_WORD};
        WordAttempt wordAttempt = new WordAttemptImpl();
        wordAttempt.setValidationResults(expected);
        assertArrayEquals(wordAttempt.getValidationResults(), results.getValidationResults());
    }


}
