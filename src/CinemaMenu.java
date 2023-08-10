public class CinemaMenu {

  enum EnumMainMenu {
    //пункты первого меню mainMenu
    EXIT("Выход"),
    TIMETABLES("Расписание"),
    FREE_SPACE("Свободные места"),
    BUYING_TICKETS("Покупка билетов"),
    TIKETS_EXCHANGE_OR_RETURN("Обмен/Возврат билетов"),
    ADMINISTRATOR("Администратор");
    private final String message;

    EnumMainMenu(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

  enum FreeSpaceMenu {
    //пункты меню Свободные места
    RETURN_TO_THE_MAIN_MENU("Введите 0 чтоб вернуться в главное меню"),
    CHANGE_DATE("Введите 1 чтоб изменить дату");
    private final String messageFreeSpaceMenu;

    FreeSpaceMenu(String messageFreeSpaceMenu) {
      this.messageFreeSpaceMenu = messageFreeSpaceMenu;
    }

    public String getMessageFreeSpaceMenu() {
      return messageFreeSpaceMenu;
    }
  }


  //меню и проверки меню тут
  // Метод для вывода разделительной строки между меню
  public static void separator() {
    System.out.println("----------------------------------------------------");
  }

  public static void mainMenu() {
    System.out.println(
        EnumMainMenu.TIMETABLES.ordinal() + "." + EnumMainMenu.FREE_SPACE.getMessage());
    System.out.println(
        EnumMainMenu.BUYING_TICKETS.ordinal() + "." + EnumMainMenu.BUYING_TICKETS.getMessage());
    System.out.println(EnumMainMenu.TIKETS_EXCHANGE_OR_RETURN.ordinal() + "."
        + EnumMainMenu.TIKETS_EXCHANGE_OR_RETURN.getMessage());
    System.out.println(
        EnumMainMenu.ADMINISTRATOR.ordinal() + "." + EnumMainMenu.ADMINISTRATOR.getMessage());
    System.out.println(EnumMainMenu.EXIT.ordinal() + "." + EnumMainMenu.EXIT.getMessage());
    separator();
  }

  public static void FreeSpaceMenu() {
    System.out.println(FreeSpaceMenu.CHANGE_DATE.ordinal() + "."
        + FreeSpaceMenu.CHANGE_DATE.getMessageFreeSpaceMenu());
    System.out.println(FreeSpaceMenu.RETURN_TO_THE_MAIN_MENU.ordinal() + "."
        + FreeSpaceMenu.RETURN_TO_THE_MAIN_MENU.getMessageFreeSpaceMenu());
    separator();
  }
}
