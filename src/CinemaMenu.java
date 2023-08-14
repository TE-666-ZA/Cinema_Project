import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class CinemaMenu {

  //меню и проверки меню тут
  protected static final int INCORRECT = -1;

  // Метод для вывода разделительной строки между меню
  public static void printSeparator() {
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
      printFreeSpaceMenu();
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
      printBuyingTicketsMenu();
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
      printTicketsExchangeOrReturnMenu();
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
      printAdministratorMenu();
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
  public static void printFreeSpaceMenu() {
    System.out.println(EnumFreeSpaceMenu.CHANGE_DATE.getMessageEnumFreeSpaceMenu());
    System.out.println(EnumFreeSpaceMenu.RETURN_TO_THE_MAIN_MENU.getMessageEnumFreeSpaceMenu());
    printSeparator();
  }

  public static void printBuyingTicketsMenu() {
    System.out.println(EnumBuyingTicketsMenu.TO_CONFIRM.getMessageEnumBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.CHANGE_SELECTION.getMessageEnumBuyingTicketsMenu());
    System.out.println(EnumBuyingTicketsMenu.CANCELLATION.getMessageEnumBuyingTicketsMenu());
    printSeparator();
  }

  public static void printTicketsExchangeOrReturnMenu() {
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.TICKETS_EXCHANGE.getMessageEnumTicketsExchangeOrReturnMenu());
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.TO_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
    System.out.println(
        EnumTicketsExchangeOrReturnMenu.CANCELLATION_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
    printSeparator();

  }

  public static void printAdministratorMenu() {
    System.out.println(
        EnumAdministratorMenu.STATISTICS_PER_SESSION.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.STATISTICS_FOR_DAY.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.CHOICE_BONUS.getMessageEnumAdministratorMenu());
    System.out.println(EnumAdministratorMenu.EXIT_MAIN_MENU.getMessageEnumAdministratorMenu());
    printSeparator();
  }

  //________________________________________________________________________________________
  //метод вывода РАСПИСАНИЯ
  public static void printTimetables() {
    CinemaMenu.printSeparator(); // вывод разделительной линии
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
  public static void printHallMapsPerDay(Scanner scanner) throws DataFormatException {
    String date = inputDate1(scanner);  //метод ввода ДАТЫ
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
  }


  public static String inputDate1(Scanner scanner) throws DataFormatException {
    DateTimeFormatter inputDateFormate = DateTimeFormatter.ofPattern(
        "dd-MM-yy"); // ввод даты в формате "dd-MM-yy"
    DateTimeFormatter outputDateFormate = DateTimeFormatter.ofPattern(
        "dd-MM-yyyy"); // будет осуществлен в формате "dd-MM-yyyy"
    System.out.println("Введите дату ->");
    String dateString = scanner.nextLine();  // ввод пользователем Даты в формате "dd-MM-yy"
    LocalDate date = LocalDate.parse(dateString,
        inputDateFormate); // Введеную строку переводим в формат времени
    String outputDate = date.format(
        outputDateFormate); // форматируем дату под необходимый вывод и возвращаем в стринг
    System.out.println(outputDate);
    return outputDate;
    // проверку на неверный ввод
    // и проверку на считывание с файла
  }

  //________________________________________________________________________________________
  //метод ввода даты и сеанса
  public static void inputDateTime() {
    System.out.println("Осуществите ввод времени сеанса ->");
  }

  public static Map<String, String> inputDateTime1(Scanner scanner) throws DataFormatException {
    String date = inputDate1(scanner); // вызов метода ввода ДАТЫ
    DateTimeFormatter inputTimeFormate = DateTimeFormatter.ofPattern(
        "HH:mm"); // ввод времени в формате "HH:mm"
    DateTimeFormatter outputTimeFormate = DateTimeFormatter.ofPattern(
        "HH.mm"); // вывод времени будет осуществлен в формате "HH.mm"
    System.out.println("Введите времени сеанса ->");
    String timeString = scanner.nextLine(); // ввод пользователем Времени в формате "HH:mm"
    LocalTime time = LocalTime.parse(timeString, inputTimeFormate);
    String outputTime = time.format(outputTimeFormate);
    Map<String, String> mapDAteTime = new HashMap<>();
    mapDAteTime.put(date, outputTime);
    System.out.println(date + "     " + outputTime);
    return mapDAteTime;
  }
  // проверку на корректный ввод и проверку на считывание с файла

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
  public static boolean inputPass(Scanner scanner) {
    String pass = "Admin945";  // сохраненный пароль для Администратора
    System.out.println("Введите пароль ->");
    String passUser = scanner.nextLine(); // ввод пароля с консоли
    if (pass.equals(passUser)) {          // сравниваем введенный и созраненные пароли
      System.out.println("Пароль верный!");
      return true;
    } else {
      System.out.println("Пароль неверный.");
      return false;
    }
  }

  //________________________________________________________________________________________
  //метод вывода СТАТИСТИКИ ЗА ДЕНЬ
  public static void printStatisticsForDay() {
    inputDate();
  }

  //________________________________________________________________________________________
  //метод вывода СТАТИСТИКИ ЗА СЕАНС
  public static void printStatisticsForSession() {
    inputDateTime();
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
