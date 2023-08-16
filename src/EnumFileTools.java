public enum EnumFileTools {
    DATE_INDEX("/"),
    TIME_INDEX("["),
    TITLE_INDEX("&"),
    BONUS_INDEX("*"),
    CHEQUE_INDEX("~"),
    SUM_INDEX("`"),
    MAP_PREFIX("|"),
    MAP_SPLITTER(""),
    MAP_KEY_VALUE_SPLITTER(">"),
    Money_index("€"),
    SPLITTER("\n");

    private final String messageEnumFileTools;

    // Корректный конструктор enum
    EnumFileTools(String messageEnumFileTools) {
        this.messageEnumFileTools = messageEnumFileTools;
    }

    public String getTool() {
        return this.messageEnumFileTools;
    }
}
