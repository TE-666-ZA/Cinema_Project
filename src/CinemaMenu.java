import java.util.InputMismatchException;
import java.util.Scanner;

public class CinemaMenu {

  //меню и проверки меню тут
  protected static final int INCORRECT = -1;

  // Метод для вывода разделительной строки между меню
  public static void separator() {
    System.out.println("--------------------------------------------------------------");
  }

  //________________________________________________________________________________________
  //методы вывода и проверки ГЛАВНОГО МЕНЮ

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

  protected static int readCommandMainMenu(Scanner scanner) {
    int command = INCORRECT;
    while (!isCommandEnumMainMenu(command)) {
      EnumMainMenu.printMainMenu();
      System.out.print("Введите пункт меню: ");
      try {
        command = scanner.nextInt();
        if (!isCommandEnumMainMenu(command)) {
          System.out.println("Некорректный пункт меню: " + command);
        }
      } catch (InputMismatchException e) {
        System.out.println("Некорректный ввод, введите номер меню: ");
      } finally {
        scanner.nextLine();
      }
    }
    return command;
  }

  //________________________________________________________________________________________
  //методы вывода и проверки меню СВОБОДНЫЕ МЕСТА
  protected static boolean isCommandEnumFreeSpaceMenu(int command) {
    switch (command) {
      case 0:
      case 1:
        return true;
      default:
        return false;
    }
  }

  protected static int readCommandFreeSpaceMenu(Scanner scanner) {
    int command = INCORRECT;
    while (!isCommandEnumFreeSpaceMenu(command)) {
      freeSpaceMenu();
      System.out.print("Введите пункт меню: ");
      try {
        command = scanner.nextInt();
        if (!isCommandEnumFreeSpaceMenu(command)) {
          System.out.println("Некорректный пункт меню: " + command);
        }
      } catch (InputMismatchException e) {
        System.out.println("Некорректный ввод, введите номер меню: ");
      } finally {
        scanner.nextLine();
      }
    }
    return command;
  }

  //________________________________________________________________________________________
  //методы вывода и проверки меню пунктов
  // 3.ПОКУПКА БИЛЕТОВ
  // 4.ОБМЕН/ВОЗВРАТ БИЛЕТОВ
  protected static boolean isCommandEnumBuyingTicketsMenu(int command) {
    switch (command) {
      case 0:
      case 1:
      case 2:
        return true;
      default:
        return false;
    }
  }

  protected static int readCommandBuyingTicketsMenu(Scanner scanner) {
    int command = INCORRECT;
    while (!isCommandEnumBuyingTicketsMenu(command)) {
      buyingTicketsMenu();
      System.out.print("Введите пункт меню: ");
      try {
        command = scanner.nextInt();
        if (!isCommandEnumBuyingTicketsMenu(command)) {
          System.out.println("Некорректный пункт меню: " + command);
        }
      } catch (InputMismatchException e) {
        System.out.println("Некорректный ввод, введите номер меню: ");
      } finally {
        scanner.nextLine();
      }
    }
    return command;
  }

  // 4.ОБМЕН/ВОЗВРАТ БИЛЕТОВ
  protected static int readCommandTicketsExchangeOrReturnMenu(Scanner scanner) {
    int command = INCORRECT;
    while (!isCommandEnumBuyingTicketsMenu(command)) {
      ticketsExchangeOrReturnMenu();
      System.out.print("Введите пункт меню: ");
      try {
        command = scanner.nextInt();
        if (!isCommandEnumBuyingTicketsMenu(command)) {
          System.out.println("Некорректный пункт меню: " + command);
        }
      } catch (InputMismatchException e) {
        System.out.println("Некорректный ввод, введите номер меню: ");
      } finally {
        scanner.nextLine();
      }
    }
    return command;
  }

  //________________________________________________________________________________________
  //методы вывода и проверки меню АДМИНИСТРАТОР
  protected static boolean isCommandEnumAdministratorMenu(int command) {
    switch (command) {
      case 0:
      case 1:
      case 2:
      case 3:
        return true;
      default:
        return false;
    }
  }

  protected static int readCommandAdministratorMenu(Scanner scanner) {
    int command = INCORRECT;
    while (!isCommandEnumAdministratorMenu(command)) {
      administratorMenu();
      System.out.print("Введите пункт меню: ");
      try {
        command = scanner.nextInt();
        if (!isCommandEnumAdministratorMenu(command)) {
          System.out.println("Некорректный пункт меню: " + command);
        }
      } catch (InputMismatchException e) {
        System.out.println("Некорректный ввод, введите номер меню: ");
      } finally {
        scanner.nextLine();
      }
    }
    return command;
  }

  //________________________________________________________________________________________
  //методы вывода МЕНЮ и ПОДМЕНЮ
  public static void freeSpaceMenu() {
    System.out.println(EnumFreeSpaceMenu.CHANGE_DATE.getMessageEnumFreeSpaceMenu());
    System.out.println(EnumFreeSpaceMenu.RETURN_TO_THE_MAIN_MENU.getMessageEnumFreeSpaceMenu());
    separator();
  }

  public static void buyingTicketsMenu() {
    System.out.println(EnumBuyingTicketsMenu.TO_CONFIRM.getMessageEnumBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.CHANGE_SELECTION.getMessageEnumBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.CANCELLATION.getMessageEnumBuyingTicketsMenu());
    separator();
  }

  public static void ticketsExchangeOrReturnMenu() {
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.TICKETS_EXCHANGE.getMessageEnumTicketsExchangeOrReturnMenu());
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.TO_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.CANCELLATION_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
    separator();

  }

  public static void administratorMenu() {
    System.out.println(
        EnumAdministratorMenu.STATISTICS_PER_SESSION.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.STATISTICS_FOR_DAY.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.CHOICE_BONUS.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.EXIT_MAIN_MENU.getMessageEnumAdministratorMenu());
    separator();
  }

  //________________________________________________________________________________________
  //метод вывода РАСПИСАНИЯ
  public static void printTimetables() {
    CinemaMenu.separator(); // вывод разделительной линии
    System.out.println("\u001B[32m" + "\t\t\t\t1. РАСПИСАНИЕ" + "\u001B[0m");
    System.out.println("Сегодня  трататататтатататта");
    System.out.println("Завтра таратататататтататата");
    System.out.println("День вслед за затра трататата");

  }

  //________________________________________________________________________________________
  //метод вывода 9 КАРТ ЗА 3 ДНЯ
  public static void printHallMapsForAllDays() {
    inputDateTime(); //метод ввода ДАТЫ
    System.out.println("             КАРТА СЕАНСА 1");
    System.out.println("             КАРТА СЕАНСА 2");
    System.out.println("             КАРТА СЕАНСА 3");

  }

  //________________________________________________________________________________________
  //метод вывода 3 КАРТ НА ВЫБРАННЫЙ ДЕНЬ
  public static void printHallMapsPerDay() {
    inputDate();  //метод ввода ДАТЫ
    System.out.println("             КАРТА СЕАНСА 1");
    System.out.println("             КАРТА СЕАНСА 2");
    System.out.println("             КАРТА СЕАНСА 3");

  }

  //________________________________________________________________________________________
  //метод вывода КАРТЫ С МЕСТАМИ КОТОРЫЕ ВЫБРАЛ КЛИЕНТ С ПОДСВЕТКОЙ МЕСТ
  public static void printMapWithYourLocation() {

    System.out.println("     КАРТА СЕАНСА ");
    System.out.println("     1.  1 2 3 4 Х Х 7 8 9");
    System.out.println("     1.  Х Х 3 4 5 6 7 8 9");

  }

  //________________________________________________________________________________________
  //метод вывода 1й КАРТЫ НА ВЫБРАННЫЙ СЕАНС
  public static void printHallMapPerSession() {
    inputDateTime(); //метод ввода ДАТЫ и СЕАНСА
    System.out.println("1.  1 2 3 4 5 6 7 8 9");
    System.out.println("2.  1 2 3 4 5 6 7 8 9");
    System.out.println("3.  1 2 3 4 5 6 7 8 9");

  }

  //________________________________________________________________________________________
  //метод ввода даты
  public static void inputDate() {
    System.out.println("Введите дату ->");
  }

  //________________________________________________________________________________________
  //метод ввода даты и сеанса
  public static void inputDateTime() {
    System.out.println("Осуществите ввод даты ->");
    System.out.println("Осуществите ввод времени сеанса ->");
  }

  //________________________________________________________________________________________
  //метод ввода Ряд/Колличество/Места
  public static void inputRowQuantityPlace() {
    System.out.println("Осуществите ввод выбраного ряда ->");
    // Вывод карты ряда на экран
    System.out.println("Введите колличество необходимых мест ->");
    System.out.println("Мы радуем приятным бонусом за покупку 4х и более мест");
    System.out.println("Введите номера выбранных мест ->");
  }

  //________________________________________________________________________________________
  //метод ПОКУПКИ БИЛЕТА С ВЫВОДОМ КАРТЫ С ПОДСВЧЕННЫМИ МЕСТАМИ
  public static void buyTickets() {
    printHallMapPerSession();// метод вывода 1й КАРТЫ НА ВЫБРАННЫЙ СЕАНС
    inputRowQuantityPlace(); // метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
    printMapWithYourLocation(); //метод вывода 1й КАРТЫ НА ВЫБРАННЫЙ СЕАНС С ПОДСВЕТКОЙ МЕСТ
    System.out.println("Сумма покупки ...");
  }

  //________________________________________________________________________________________
  //метод подтверждения покупки в меню ПОКУПКА БИЛЕТОВ
  public static void confirmPurchase() {
    System.out.println("Подтвердите покупку вводом ФАМИЛИИ ->");
  }

  //________________________________________________________________________________________
  //метод для меню ОБМЕН/ВАЗВРАТ БИЛЕТОВ, когда по фамиии выводим кол-во билетов/день/сеанс
  public static void inputLastName() {
    System.out.println("Введите ФАМИЛИЮ ->");
  }

  //________________________________________________________________________________________
  //метод СДАТЬ БИЛЕТЫ
  public static void toReturnTickets() {
    System.out.println(" ФИО ___билетов на ____ _____");
    System.out.println(" Возврат _____ евро");
    System.out.println(" Спасибо, что воспользовались нашим сервисом!");
  }

  //________________________________________________________________________________________
  //метод ввода пароля Администратора
  public static void inputPass() {
    System.out.println("Введите пароль ->");
  }

  //________________________________________________________________________________________
  //метод вывода СТАТИСТИКИ ЗА ДЕНЬ
  public static void printStatisticsForDay() {
    inputDate();
    System.out.println("Введите пароль ->");
  }

  //________________________________________________________________________________________
  //метод вывода СТАТИСТИКИ ЗА СЕАНС
  public static void printStatisticsForSession() {
    inputDateTime();
    System.out.println("Введите пароль ->");
  }

  //________________________________________________________________________________________
  //метод ИЗМЕНЕНИЯ БОНУСА
  public static void changeBonus() {
    System.out.println("Введите НОВЫЙ БОНУС ->");
  }

  //классы Enum - пункты меню
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
    TO_CONFIRM("Введите 1 чтоб ПОДТВЕРДИТЬ ПОКУПКУ"),
    CHANGE_SELECTION("Введите 2 чтоб ИЗМЕНИТЬ ВЫБОР");
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
    STATISTICS_PER_SESSION("Введите 1 чтоб отобразить СТАТИСТИКУ ЗА СЕАНС"),
    STATISTICS_FOR_DAY("Введите 2 чтоб выбрать СТАТИСТИКУ ЗА ДЕНЬ"),
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
