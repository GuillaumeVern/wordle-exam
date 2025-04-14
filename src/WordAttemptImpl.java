import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WordAttemptImpl implements WordAttempt {
    private String word;
    private EnumCharValidationState[] validationResults;

    public WordAttemptImpl(String word) {
        this.word = word;
        this.validationResults = new EnumCharValidationState[word.length()];
    }

    public void setValidationResultAt(int index, EnumCharValidationState state) {
        validationResults[index] = state;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            result.append(validationResults[i].getSymbol());
            result.append(word.charAt(i));
            result.append("\u001B[0m");
        }
        return result.toString();
    }


}
