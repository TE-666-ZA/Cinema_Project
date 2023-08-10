public class CinemaMenu {
  //меню и проверки меню тут

  enum EnumMainMenu {
    //пункты первого меню mainMenu
    EXIT("Выход"),
    TIMETABLES("Расписание"),
    FREE_SPACE("Свободные места"),
    BUYING_TICKETS("Покупка билетов"),
    TIСKETS_EXCHANGE_OR_RETURN("Обмен/Возврат билетов"),
    ADMINISTRATOR("Администратор");
    private final String message;

    EnumMainMenu(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  enum EnumFreeSpaceMenu {
    //пункты меню Свободные места
    RETURN_TO_THE_MAIN_MENU("Введите 0 чтоб вернуться в главное меню"),
    CHANGE_DATE("Введите 1 чтоб изменить дату");
    private final String messageEnumFreeSpaceMenu;

    EnumFreeSpaceMenu(String messageEnumFreeSpaceMenu) {
      this.messageEnumFreeSpaceMenu = messageEnumFreeSpaceMenu;
    }

    public String getMessageFreeSpaceMenu() {
      return messageEnumFreeSpaceMenu;
    }
  }

  enum EnumBuyingTicketsMenu {
    CANCELLATION("Введите 0 если хотите ОТМЕНИТЬ ПОКУПКУ"),
    CHANGE_SELECTION("Введите 1 чтоб ИЗМЕНИТЬ ВЫБОР"),
    TO_CONFIRM("Введите 2 чтоб ПОДТВЕРДИТЬ ПОКУПКУ");
    private final String messageEnumBuyingTicketsMenu;

    EnumBuyingTicketsMenu(String messageEnumBuyingTicketsMenu) {
      this.messageEnumBuyingTicketsMenu = messageEnumBuyingTicketsMenu;
    }

    public String getBuyingTicketsMenu() {
      return messageEnumBuyingTicketsMenu;
    }
  }

  // Метод для вывода разделительной строки между меню
  public static void separator() {
    System.out.println("----------------------------------------------------");
  }

  public static void mainMenu() {
    System.out.println(
        EnumMainMenu.TIMETABLES.ordinal() + "." + EnumMainMenu.TIMETABLES.getMessage());
    System.out.println(
        EnumMainMenu.FREE_SPACE.ordinal() + "." + EnumMainMenu.FREE_SPACE.getMessage());
    System.out.println(
        EnumMainMenu.BUYING_TICKETS.ordinal() + "." + EnumMainMenu.BUYING_TICKETS.getMessage());
    System.out.println(EnumMainMenu.TIСKETS_EXCHANGE_OR_RETURN.ordinal() + "."
        + EnumMainMenu.TIСKETS_EXCHANGE_OR_RETURN.getMessage());
    System.out.println(
        EnumMainMenu.ADMINISTRATOR.ordinal() + "." + EnumMainMenu.ADMINISTRATOR.getMessage());
    System.out.println(EnumMainMenu.EXIT.ordinal() + "." + EnumMainMenu.EXIT.getMessage());
    separator();
  }

  public static void FreeSpaceMenu() {
    System.out.println(EnumFreeSpaceMenu.CHANGE_DATE.getMessageFreeSpaceMenu());
    System.out.println(EnumFreeSpaceMenu.RETURN_TO_THE_MAIN_MENU.getMessageFreeSpaceMenu());
    separator();
  }

  public static void BuyingTicketsMenu() {
    System.out.println(EnumBuyingTicketsMenu.CHANGE_SELECTION.getBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.TO_CONFIRM.getBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.CANCELLATION.getBuyingTicketsMenu());
    separator();
  }
}
