import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor
public class WordValidatorImpl implements WordValidator {
    private PrettyPrinter prettyPrinter;
    private WordsDictionary wordsDictionary;


    public WordValidatorImpl() {
        this(new PrettyPrinterImpl());
    }

    public WordValidatorImpl(PrettyPrinter prettyPrinter) {
        this.prettyPrinter = prettyPrinter;
        this.wordsDictionary = new WordsDictionaryImpl();
    }

    public boolean isValid(String word) {
        boolean valid = true;
        valid = isNotNull(word) && isExactlyFiveCharacters(word) && isOnlyLetters(word) && isInDictionary(word);
        return valid;
    }

    public WordAttempt getValidationResults(String word, String guess) {
        WordAttempt wordAttempt = new WordAttemptImpl(guess);
        char[] wordCharArray = word.toCharArray();
        char[] guessCharArray = guess.toCharArray();
        int occurences = 0;
        int occurencesFound = 0;

        for (int i = 0; i < wordCharArray.length; i++) {
            wordAttempt.setValidationResultAt(i, EnumCharValidationState.NOT_IN_WORD);
            if (wordCharArray[i] == guessCharArray[i]) {
                wordAttempt.setValidationResultAt(i, EnumCharValidationState.EXACT_MATCH);
            }
        }

        for (int i = 0; i < wordCharArray.length; i++) {
            if (word.contains(String.valueOf(guessCharArray[i]))) {
                occurences = 0;
                for (char c : wordCharArray) {
                    if (c == guessCharArray[i]) {
                        occurences++;
                    }
                }
                occurencesFound = 0;
                for (int j = 0; j < wordAttempt.getValidationResults().length; j++) {
                    if (wordAttempt.getValidationResults()[j] == EnumCharValidationState.EXACT_MATCH ||
                            wordAttempt.getValidationResults()[j] == EnumCharValidationState.IN_WORD) {
                        occurencesFound++;
                    }
                }
                if (occurencesFound < occurences) {
                    wordAttempt.setValidationResultAt(i, EnumCharValidationState.IN_WORD);
                }
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

    private boolean isInDictionary (String word) {
        boolean valid = true;
        if (!wordsDictionary.checkUserInputWordIsValid(word)) {
            valid = false;
            prettyPrinter.printErrorMessage("Word is not in the dictionary, please enter a valid word.");
        }
        return valid;
    }
}
