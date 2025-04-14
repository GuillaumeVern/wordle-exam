public interface WordAttempt {
    void setValidationResultAt(int index, EnumCharValidationState state);
    String toString();
    void setValidationResults(EnumCharValidationState[] validationResults);
    EnumCharValidationState[] getValidationResults();
}
