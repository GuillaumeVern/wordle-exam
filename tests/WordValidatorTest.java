import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class WordValidatorTest {
    private WordValidator validator;

    @BeforeEach
    void setUp() {
        PrettyPrinter prettyprinterMock = mock(PrettyPrinterImpl.class);
        validator = new WordValidatorImpl(prettyprinterMock);
    }

    @Test
    public void testWordIsValid() {
        boolean result = validator.isValid("hello");
        assertTrue(result);
    }

    @Test
    public void testWordIsExaclyFiveLetters() {
        boolean result = validator.isValid("helloo");
        assertFalse(result);
    }

    @Test
    public void testWordIsNotNull() {
        boolean result = validator.isValid(null);
        assertFalse(result);
    }

    @Test
    public void testWordNoNumbers() {
        boolean result = validator.isValid("hell2");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpecialCharacters() {
        boolean result = validator.isValid("hell!");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpaces() {
        boolean result = validator.isValid("hel o");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpacesStart() {
        boolean result = validator.isValid(" ello");
        assertFalse(result);
    }

    @Test
    public void testWordNoSpacesEnd() {
        boolean result = validator.isValid("hell ");
        assertFalse(result);
    }

    @Test
    public void testExactMatchWord() {
        WordAttempt results = validator.getValidationResults("hello", "hello");
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
        WordAttempt results = validator.getValidationResults("hello", "hffff");
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
        WordAttempt results = validator.getValidationResults("hello", "ffffh");
        EnumCharValidationState[] expected = {EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.IN_WORD};
        WordAttempt wordAttempt = new WordAttemptImpl();
        wordAttempt.setValidationResults(expected);
        assertArrayEquals(wordAttempt.getValidationResults(), results.getValidationResults());
    }

    @Test
    public void testMatchOneExact() {
        WordAttempt results = validator.getValidationResults("hello", "hfffh");
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
    public void testMatchTwoExact() {
        WordAttempt results = validator.getValidationResults("hello", "hfofo");
        EnumCharValidationState[] expected = {EnumCharValidationState.EXACT_MATCH,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.NOT_IN_WORD,
                EnumCharValidationState.EXACT_MATCH};
        WordAttempt wordAttempt = new WordAttemptImpl();
        wordAttempt.setValidationResults(expected);
        assertArrayEquals(wordAttempt.getValidationResults(), results.getValidationResults());
    }


}
