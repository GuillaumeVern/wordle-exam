import lombok.Getter;

@Getter
public enum EnumCharValidationState {
    EXACT_MATCH ("*"),
    IN_WORD ("+"),
    NOT_IN_WORD ("-");

    private final String symbol;

    EnumCharValidationState(String symbol) {
        this.symbol = symbol;
    }
}

