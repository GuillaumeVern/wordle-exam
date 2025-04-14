import lombok.Getter;

@Getter
public enum EnumCharValidationState {
    EXACT_MATCH ("\u001B[32m"),
    IN_WORD ("\u001B[33m"),
    NOT_IN_WORD ("\u001B[31m");

    private final String symbol;

    EnumCharValidationState(String symbol) {
        this.symbol = symbol;
    }
}

