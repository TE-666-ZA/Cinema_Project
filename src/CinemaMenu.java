public class CinemaMenu {
  //меню и проверки меню тут

  enum EnumMainMenu {
    //пункты первого меню mainMenu
    EXIT("Выход"),
    TIMETABLES("Расписание"),
    FREE_SPACE("Свободные места"),
    BUYING_TICKETS("Покупка билетов"),
    TICKETS_EXCHANGE_OR_RETURN("Обмен/Возврат билетов"),
    ADMINISTRATOR("Администратор");
    private final String messageEnumMainMenu;

    EnumMainMenu(String messageEnumMainMenu) {
      this.messageEnumMainMenu = messageEnumMainMenu;
    }

    public String getMessageEnumMainMenu() {
      return messageEnumMainMenu;
    }
  }

  enum EnumFreeSpaceMenu {
    //пункты меню Свободные места
    RETURN_TO_THE_MAIN_MENU("Введите 0 чтоб ВЕРНУТЬСЯ В ГЛАВНОЕ МЕНЮ"),
    CHANGE_DATE("Введите 1 чтоб ИЗМЕНИТЬ ДАТУ");
    private final String messageEnumFreeSpaceMenu;

    EnumFreeSpaceMenu(String messageEnumFreeSpaceMenu) {
      this.messageEnumFreeSpaceMenu = messageEnumFreeSpaceMenu;
    }

    public String getMessageEnumFreeSpaceMenu() {
      return messageEnumFreeSpaceMenu;
    }
  }

  enum EnumBuyingTicketsMenu {
    CANCELLATION("Введите 0 чтобы ОТМЕНИТЬ ПОКУПКУ"),
    CHANGE_SELECTION("Введите 1 чтоб ИЗМЕНИТЬ ВЫБОР"),
    TO_CONFIRM("Введите 2 чтоб ПОДТВЕРДИТЬ ПОКУПКУ");
    private final String messageEnumBuyingTicketsMenu;

    EnumBuyingTicketsMenu(String messageEnumBuyingTicketsMenu) {
      this.messageEnumBuyingTicketsMenu = messageEnumBuyingTicketsMenu;
    }

    public String getMessageEnumBuyingTicketsMenu() {
      return messageEnumBuyingTicketsMenu;
    }
  }

  enum EnumTicketsExchangeOrReturnMenu {
    CANCELLATION_RETURN_TICKETS("Введите 0 если хотите ОТМЕНИТЬ ОБМЕН/ВОЗВРАТ БИЛЕТОВ"),
    TICKETS_EXCHANGE("Введите 1 если хотите СОВЕРШИТЬ ОБМЕН БИЛЕТОВ"),
    TO_RETURN_TICKETS("Введите 2 если хотите СДАТЬ БИЛЕТЫ");

    public final String messageEnumTicketsExchangeOrReturnMenu;

    EnumTicketsExchangeOrReturnMenu(String messageEnumTicketsExchangeOrReturnMenu) {
      this.messageEnumTicketsExchangeOrReturnMenu = messageEnumTicketsExchangeOrReturnMenu;
    }

    public String getMessageEnumTicketsExchangeOrReturnMenu() {
      return messageEnumTicketsExchangeOrReturnMenu;
    }

  }

  // Метод для вывода разделительной строки между меню
  public static void separator() {
    System.out.println("----------------------------------------------------");
  }

  public static void mainMenu() {
    System.out.println(
        EnumMainMenu.TIMETABLES.ordinal() + "." + EnumMainMenu.TIMETABLES.getMessageEnumMainMenu());
    System.out.println(
        EnumMainMenu.FREE_SPACE.ordinal() + "." + EnumMainMenu.FREE_SPACE.getMessageEnumMainMenu());
    System.out.println(
        EnumMainMenu.BUYING_TICKETS.ordinal() + "."
            + EnumMainMenu.BUYING_TICKETS.getMessageEnumMainMenu());
    System.out.println(EnumMainMenu.TICKETS_EXCHANGE_OR_RETURN.ordinal() + "."
        + EnumMainMenu.TICKETS_EXCHANGE_OR_RETURN.getMessageEnumMainMenu());
    System.out.println(
        EnumMainMenu.ADMINISTRATOR.ordinal() + "."
            + EnumMainMenu.ADMINISTRATOR.getMessageEnumMainMenu());
    System.out.println(
        EnumMainMenu.EXIT.ordinal() + "." + EnumMainMenu.EXIT.getMessageEnumMainMenu());
    separator();
  }

  public static void FreeSpaceMenu() {
    System.out.println(EnumFreeSpaceMenu.RETURN_TO_THE_MAIN_MENU.getMessageEnumFreeSpaceMenu());
    System.out.println(EnumFreeSpaceMenu.CHANGE_DATE.getMessageEnumFreeSpaceMenu());
    separator();
  }

  public static void BuyingTicketsMenu() {
    System.out.println(EnumBuyingTicketsMenu.CHANGE_SELECTION.getMessageEnumBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.TO_CONFIRM.getMessageEnumBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.CANCELLATION.getMessageEnumBuyingTicketsMenu());
    separator();
  }

  public static void TicketsExchangeOrReturnMenu() {
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.TICKETS_EXCHANGE.getMessageEnumTicketsExchangeOrReturnMenu());
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.TO_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.CANCELLATION_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
    separator();
    
  }
}
