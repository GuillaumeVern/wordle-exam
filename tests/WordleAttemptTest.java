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
        String expected = "\u001B[32mV\u001B[32mA\u001B[32mL\u001B[32mI\u001B[32mD\u001B[0m";
        String actual = wordAttempt.toString();

        assertEquals(expected, actual);
    }
}
