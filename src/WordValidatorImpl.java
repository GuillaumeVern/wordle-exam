import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

@Getter @Setter
public class WordValidatorImpl implements WordValidator {
    private PrettyPrinter prettyPrinter;


    public WordValidatorImpl() {
        this(new PrettyPrinterImpl());
    }

    public WordValidatorImpl(PrettyPrinter prettyPrinter) {
        this.prettyPrinter = prettyPrinter;
    }

    public boolean isValid(String word) {
        boolean valid = true;
        valid = isNotNull(word) && isExactlyFiveCharacters(word) && isOnlyLetters(word);
        return valid;
    }

    public WordAttempt getValidationResults(String word, String guess) {
        WordAttempt wordAttempt = new WordAttemptImpl(guess);
        char[] wordCharArray = word.toCharArray();
        char[] guessCharArray = guess.toCharArray();

        for (int i = 0; i < wordCharArray.length; i++) {
            if (wordCharArray[i] == guessCharArray[i]) {
                wordAttempt.setValidationResultAt(i, EnumCharValidationState.EXACT_MATCH);
            } else if (word.contains(String.valueOf(guessCharArray[i]))) {
                wordAttempt.setValidationResultAt(i, EnumCharValidationState.IN_WORD);
            } else {
                wordAttempt.setValidationResultAt(i, EnumCharValidationState.NOT_IN_WORD);
            }
        }

        return wordAttempt;
    }

    private boolean isNotNull(String word) {
        boolean valid = true;
        if (word == null) {
            valid = false;
            prettyPrinter.printErrorMessage("Word must not be null, please enter a valid word.");
        }
        return valid;
    }

    private boolean isExactlyFiveCharacters(String word) {
        boolean valid = true;
        if (word.length() != 5) {
            valid = false;
            prettyPrinter.printErrorMessage("Word must be exactly 5 letters long, please enter a valid word.");
        }
        return valid;
    }

    private boolean isOnlyLetters(String word) {
        boolean valid = true;
        if (!word.matches("[a-zA-Z]+")) {
            valid = false;
            prettyPrinter.printErrorMessage("Word must contain only letters, please enter a valid word.");
        }
        return valid;
    }
}
