import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public interface WordValidator {

    boolean isValid(String word);

    WordAttempt getValidationResults(String word, String guess);

}