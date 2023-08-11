import java.util.InputMismatchException;
import java.util.Scanner;

public class CinemaMenu {

  //меню и проверки меню тут
  protected static final int INCORRECT = -1;

  // Метод для вывода разделительной строки между меню
  public static void separator() {
    System.out.println("----------------------------------------------------");
  }

  //
  protected static boolean isCommandEnumMainMenu(int command) {
    switch (command) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
        return true;
      default:
        return false;
    }
  }

  protected static int readCommandMainMenuCommand(Scanner scanner) {

    int command = INCORRECT;
    while (!isCommandEnumMainMenu(command)) {
      EnumMainMenu.printMainMenu();
      System.out.print("Введите номер действия: ");
      try {
        command = scanner.nextInt();
        if (!isCommandEnumMainMenu(command)) {
          System.out.println("Некорректный номер команды: " + command);
        }
      } catch (InputMismatchException e) {
        System.out.println("Некорректный ввод, введите номер команды: ");
      } finally {
        scanner.nextLine();
      }
    }
    return command;
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

  public static void AdministratorMenu() {
    System.out.println(EnumAdministratorMenu.EXIT_MAIN_MENU.getMessageEnumAdministratorMenu());
    System.out.println(
        EnumAdministratorMenu.STATISTICS_PER_SESSOIN.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.STATISTCS_FOR_DAY.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.CHOICE_BONUS.getMessageEnumAdministratorMenu());
    separator();
  }

  protected enum EnumMainMenu {
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

    public static void printMainMenu() {
      for (EnumMainMenu command : values()) {
        if (!command.messageEnumMainMenu.isEmpty()) { // message пустое для всех служебных значений
          System.out.println(command.ordinal() + ". " + command.getMessageEnumMainMenu());
        }
      }

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

  enum EnumAdministratorMenu {
    EXIT_MAIN_MENU("Введите 0 для ВОЗВРАТА В ПРЕДЫДУЩЕЕ МЕНЮ"),
    STATISTICS_PER_SESSOIN("Введите 1 чтоб отобразить СТАТИСТИКУ ЗА СЕАНС"),
    STATISTCS_FOR_DAY("Введите 2 чтоб выбрать СТАТИСТИКУ ЗА ДЕНЬ"),
    CHOICE_BONUS("Введите 3 чтоб изменить БОНУСЫ");
    private final String messageEnumAdministratorMenu;

    EnumAdministratorMenu(String messageEnumAdministratorMenu) {
      this.messageEnumAdministratorMenu = messageEnumAdministratorMenu;
    }

    public String getMessageEnumAdministratorMenu() {
      return messageEnumAdministratorMenu;
    }

  }
}
