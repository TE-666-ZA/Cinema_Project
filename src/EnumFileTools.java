/**
 * Enum для хранения различных индексов и разделителей, используемых при работе с файлами
 */
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
    SPLITTER(",");

    // Константа, которая хранит текстовое представление элемента
    private final String messageEnumFileTools;

    /**
     * Конструктор с заданием текстового представления элемента
     *
     * @param messageEnumFileTools текстовое представление элемента
     */
    EnumFileTools(String messageEnumFileTools) {
        this.messageEnumFileTools = messageEnumFileTools;
    }

    /**
     * Геттер для получения текстового представления элемента
     *
     * @return текстовое представление элемента
     */
    public String getTool() {
        return this.messageEnumFileTools;
    }
}
