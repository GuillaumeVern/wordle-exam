import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public interface WordValidator {
    ArrayList<Function<String, Boolean>> validators = new ArrayList<>(Arrays.asList(WordValidator::isNotNull, WordValidator::isExactlyFiveCharacters, WordValidator::isOnlyLetters));

    static boolean isValid(String word) {
        boolean valid = true;
        for (Function<String, Boolean> validator : validators) {
            if (!validator.apply(word)) {
                valid = false;
                break;
            }
        }
        return valid;
    }

    private static boolean isNotNull(String word) {
        boolean valid = true;
        if (word == null) {
            valid = false;
            System.out.println("Word must not be null, please enter a valid word.");
        }
        return valid;
    }

    private static boolean isExactlyFiveCharacters(String word) {
        boolean valid = true;
        if (word.length() != 5) {
            valid = false;
            System.out.println("Word must be exactly 5 letters long, please enter a valid word.");
        }
        return valid;
    }

    private static boolean isOnlyLetters(String word) {
        boolean valid = true;
        if (!word.matches("[a-zA-Z]+")) {
            valid = false;
            System.out.println("Word must contain only letters, please enter a valid word.");
        }
        return valid;
    }
}