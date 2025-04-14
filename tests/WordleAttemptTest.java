import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordleAttemptTest {


    @Test
    public void validGuessToStringTest() {
        WordAttempt wordAttempt = new WordAttemptImpl("valid",
                new EnumCharValidationState[]{
                        EnumCharValidationState.EXACT_MATCH,
                        EnumCharValidationState.EXACT_MATCH,
                        EnumCharValidationState.EXACT_MATCH,
                        EnumCharValidationState.EXACT_MATCH,
                        EnumCharValidationState.EXACT_MATCH});
        String expected = "\u001B[0m\u001B[1m\u001B[30m\u001B[102mV\u001B[0m\u001B[1m\u001B[30m\u001B[102mA\u001B[0m\u001B[1m\u001B[30m\u001B[102mL\u001B[0m\u001B[1m\u001B[30m\u001B[102mI\u001B[0m\u001B[1m\u001B[30m\u001B[102mD\u001B[0m";
        String actual = wordAttempt.toString();

        assertEquals(expected, actual);
    }
}
