public enum EnumFileTools {
    DATE_INDEX("/"),
    TIME_INDEX("["),
    TITLE_INDEX("&"),
    BONUS_INDEX("*"),
    CHEQUE_INDEX("~"),
    CHEQUE_SEPARATOR(","),
    MAP_PREFIX("|"),
    MAP_CHEQUE_SPLITTER(""),
    MAP_KEY_VALUE_SPLITTER(">"),
    Money_index("€"),
    SPLITTER(",");

    private final String messageEnumFileTools;

    // Корректный конструктор enum
    EnumFileTools(String messageEnumFileTools) {
        this.messageEnumFileTools = messageEnumFileTools;
    }

    public String getTool() {
        return this.messageEnumFileTools;
    }
}
