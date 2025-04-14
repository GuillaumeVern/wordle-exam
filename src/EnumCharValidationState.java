import lombok.Getter;

@Getter
public enum EnumCharValidationState {
    EXACT_MATCH ("\u001B[0m\u001B[1m\u001B[30m\u001B[102m"),
    IN_WORD ("\u001B[0m\u001B[1m\u001B[30m\u001B[103m"),
    NOT_IN_WORD ("\u001B[0m\u001B[1m\u001B[37m");

    private final String symbol;

    EnumCharValidationState(String symbol) {
        this.symbol = symbol;
    }
}

