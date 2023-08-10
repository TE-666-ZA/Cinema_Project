public enum Separators {
  //разделители для файла  и константы меню
  //пункты первого меню mainMenu
  EXIT("Выход"),
  TIMETABLES("Расписание"),
  FREE_SPACE("Свободные места"),
  BUYING_TICKETS("Покупка билетов"),
  TIKETS_EXCHANGE_OR_RETURN("Обмен/Возврат билетов"),
  ADMINISTRATOR("Администратор");
  private final String message;

  Separators(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
