import java.io.IOException;
import java.sql.Struct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
  //метод вывода 9 КАРТ ЗА 3 ДНЯ
  public static void printHallMapsForAllDays() {

    System.out.println("             КАРТА СЕАНСА 1");
    System.out.println("             КАРТА СЕАНСА 2");
    System.out.println("             КАРТА СЕАНСА 3");

  }

  //________________________________________________________________________________________
  //метод вывода 3 КАРТ НА ВЫБРАННЫЙ ДЕНЬ
  public static void printHallMapsPerDay(LocalDate date, Session session, HallMap hallMap)
      throws DataFormatException, IOException {
    Map<Integer, Map<Integer, Character[]>> hallMapsForDate = session.getHallMapsForDate(date,
        hallMap);

    if (hallMapsForDate.isEmpty()) {
      System.out.println("На выбранную дату нет сеансов.");
      return;
    }

    for (Map.Entry<Integer, Map<Integer, Character[]>> entry : hallMapsForDate.entrySet()) {
      int sessionKey = entry.getKey();
      System.out.println("КАРТА СЕАНСА " + sessionKey);
      hallMap.showSessionMap(sessionKey);
    }
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
    //inputDateTime(); //метод ввода ДАТЫ и СЕАНСА
    System.out.println("1.  1 2 3 4 5 6 7 8 9");
    System.out.println("2.  1 2 3 4 5 6 7 8 9");
    System.out.println("3.  1 2 3 4 5 6 7 8 9");

  }

  //________________________________________________________________________________________

  /**
   * Метод пункта меню 2.СВОБОДНЫЕ МЕСТА содержит подменю 2.1 ИЗМЕНИТЬ ДАТУ содержит подменю
   * 2.2ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
   *
   * @param scanner
   * @param session
   * @throws DataFormatException
   */
  public static void freeSpace(Scanner scanner, Session session, HallMap hallMap)
      throws DataFormatException, IOException {
    CinemaMenu.printSeparator(); // вывод разделительной линии
    System.out.println("\u001B[32m" + "\t\t\t\t2. СВОБОДНЫЕ МЕСТА" + "\u001B[0m");
    //ввели дату и проверяем на совпадение с датами в тексте//
    LocalDate date = checkDate(scanner, session);

    //TODO удалить этот проверочный вывод
    System.out.println(date);
    //ВЫВОД ТРЕХ КАРТ ЗА ОДИН ДЕНЬ
    printHallMapsPerDay(date, session, hallMap);

    boolean runFreeSpaseMenu = true;
    while (runFreeSpaseMenu) {
      int commandFreeSpaseMenu = CinemaMenu.readCommandFreeSpaceMenu(scanner);
      EnumFreeSpaceMenu selectedFreeSpaseMenu = EnumFreeSpaceMenu.values()[commandFreeSpaseMenu];
      switch (selectedFreeSpaseMenu) {
        case CHANGE_DATE: // 2.1 ИЗМЕНИТЬ ДАТУ
          System.out.println(
              "\u001B[32m" + "\t\t\t\t2. СВОБОДНЫЕ МЕСТА -> ИЗМЕНИТЬ ДАТУ: " + "\u001B[0m");
          date = checkDate(scanner, session);
          //ВЫВОД ТРЕХ КАРТ ЗА ОДИН ДЕНЬ
          printHallMapsPerDay(date, session, hallMap);
          break;
        case RETURN_TO_THE_MAIN_MENU: // 2.2 ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
          runFreeSpaseMenu = false;
          break;
      }
    }
  }
  //________________________________________________________________________________________

  /**
   * Меню пункт 3 3. ПОКУПКА БИЛЕТОВ содержит подменю 3.1 ПОДТВЕРЖДЕНИЕ ПОКУПКИ 3.2 ИЗМЕНИТЬ ВЫБОР
   * 3.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
   *
   * @param scanner
   * @param session
   * @throws DataFormatException
   */
  public static void buyingTickets(Scanner scanner, Session session) throws DataFormatException {
    CinemaMenu.printSeparator(); // вывод разделительной линии
    System.out.println("\u001B[32m" + "\t\t\t\t3. ПОКУПКА БИЛЕТОВ" + "\u001B[0m");

    //метод ПОКУПКИ БИЛЕТА С ВЫВОДОМ КАРТЫ С ПОДСВЧЕННЫМИ МЕСТАМИ
    //buyTickets(scanner,sessoin);

    //Вывод нового меню
    boolean runBuyingTicketsMenu = true;
    while (runBuyingTicketsMenu) {
      int commandBuyingTicketsMenu = CinemaMenu.readCommandBuyingTicketsMenu(scanner);
      EnumBuyingTicketsMenu selectedBuyingTicketsMenu = EnumBuyingTicketsMenu.values()[commandBuyingTicketsMenu];
      switch (selectedBuyingTicketsMenu) {
        case TO_CONFIRM: // 3.1 ПОДТВЕРЖДЕНИЕ ПОКУПКИ
          System.out.println(
              "\u001B[32m" + "\t\t\t\t3. ПОКУПКА БИЛЕТОВ -> ПОДТВЕРЖДЕНИЕ ПОКУПКИ :"
                  + "\u001B[0m");
          CinemaMenu.confirmPurchase(); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
          //ввод фамилии
          runBuyingTicketsMenu = false;
          break;
        case CHANGE_SELECTION: // 3.2 ИЗМЕНИТЬ ВЫБОР
          System.out.println(
              "\u001B[32m" + "\t\t\t\t3. ПОКУПКА БИЛЕТОВ -> ИЗМЕНИТЬ ВЫБОР :" + "\u001B[0m");
          // метод ввода ДАТЫ и СЕАНСА
          LocalDate date = checkDate(scanner, session);
          LocalDate time = checkDate(scanner, session);

          // заново запрашиваем ряд/количество мест/места
          CinemaMenu.inputRowQuantityPlace();// метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
          CinemaMenu.confirmPurchase(); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
          break;
        case CANCELLATION: // 3.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
          runBuyingTicketsMenu = false;
          break;
      }
    }
  }
  //________________________________________________________________________________________

  /**
   * Метод меню 4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ содержит подменю 4.1 ОБМЕН БИЛЕТОВ 4.2 СДАТЬ БИЛЕТЫ 4.3
   * ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
   *
   * @param scanner
   * @param session
   * @throws DataFormatException
   */
  public static void ticketsExchangeOrReturn(Scanner scanner, Session session)
      throws DataFormatException {
    CinemaMenu.printSeparator(); // вывод разделительной линии
    System.out.println("\u001B[32m" + "\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ:" + "\u001B[0m");
    CinemaMenu.inputLastName();  //
    // ввод фио
    // вывод из файла  ПЕТРОВ 2 билета  Завтра 12.00 Русалочка
    boolean runTicketsExchangeOrReturnMenu = true;
    while (runTicketsExchangeOrReturnMenu) {
      int commandTicketsExchangeOrReturnMenu = CinemaMenu.readCommandTicketsExchangeOrReturnMenu(
          scanner);
      EnumTicketsExchangeOrReturnMenu selectedTicketsExchangeOrReturnMenu = EnumTicketsExchangeOrReturnMenu.values()[commandTicketsExchangeOrReturnMenu];
      switch (selectedTicketsExchangeOrReturnMenu) {
        case TICKETS_EXCHANGE: // 4.1 ОБМЕН БИЛЕТОВ
          System.out.println(
              "\u001B[32m" + "\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ -> ОБМЕН БИЛЕТОВ :"
                  + "\u001B[0m");
          CinemaMenu.printSeparator(); // вывод разделительной линии
          // вывод РАСПИСАНИЯ
          session.showSchedule();
          //метод ввода ДАТЫ и СЕАНСА
          LocalDate date = checkDate(scanner, session);
          LocalDate time = checkDate(scanner, session);

          // вывод карты мест для конкретного сеанса

          CinemaMenu.inputRowQuantityPlace(); // метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
          //////////////////////////////////////////////////////////////////////////////////
          System.out.println("Введите ряд - >");
          //ввод ряда
          // Вывод карты ряда на экран
          System.out.println("Введите количество мест - >");
          ////////////////////////////////////////////////////////////////////////////////
          System.out.println("Вы обменяли______билета на ______   ______");
          runTicketsExchangeOrReturnMenu = false;
          break;
        case TO_RETURN_TICKETS: //4.2 СДАТЬ БИЛЕТЫ
          System.out.println(
              "\u001B[32m" + "\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ -> СДАТЬ БИЛЕТЫ :"
                  + "\u001B[0m");
          CinemaMenu.toReturnTickets(); // метод СДАТЬ БИЛЕТЫ С ВЫВОДОМ ИНФОРМАЦИИ НА КАКОЙ СЕАНС И ФИО
          runTicketsExchangeOrReturnMenu = false;
          break;
        case CANCELLATION_RETURN_TICKETS: // 4.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
          runTicketsExchangeOrReturnMenu = false;
          break;
      }
    }
  }

  //________________________________________________________________________________________

  /**
   * Метод меню 5. АДМИНИСТРАТОР 5.1 СТАТИСТИКА ЗА СЕАНС 5.2 СТАТИСТИКА ЗА ДЕНЬ 5.3 ИЗМЕНИТЬ БОНУС
   * Выход в предыдущее меню
   *
   * @param scanner
   * @param session
   * @throws DataFormatException
   */
  public static void administartor(Scanner scanner, Session session) throws DataFormatException {
    boolean runAdministratorMenu = true;
    while (runAdministratorMenu) {
      int commandAdministratorMenu = CinemaMenu.readCommandAdministratorMenu(scanner);
      EnumAdministratorMenu selectedAdministratorMenu = EnumAdministratorMenu.values()[commandAdministratorMenu];
      switch (selectedAdministratorMenu) {
        case STATISTICS_PER_SESSION: // 5.1 СТАТИСТИКА ЗА СЕАНС
          System.out.println(
              "\u001B[32m" + "\t\t\t\t4. АДМИНИСТРАТОР -> СТАТИСТИКА ЗА СЕАНС :"
                  + "\u001B[0m");
          CinemaMenu.printStatisticsForSession(); //метод вывода СТАТИСТИКИ ЗА СЕАНС
          break;
        case STATISTICS_FOR_DAY: // 5.2 СТАТИСТИКА ЗА ДЕНЬ
          System.out.println("\u001B[32m" + "\t\t\t\t4. АДМИНИСТРАТОР -> СТАТИСТИКА ЗА ДЕНЬ :"
              + "\u001B[0m");
          CinemaMenu.printStatisticsForDay(); //метод вывода СТАТИСТИКИ ЗА ДЕНЬ
          break;
        case CHOICE_BONUS: // 5.3 ИЗМЕНИТЬ БОНУС
          System.out.println(
              "\u001B[32m" + "\t\t\t\t4. АДМИНИСТРАТОР -> ИЗМЕНИТЬ БОНУС :" + "\u001B[0m");
          CinemaMenu.changeBonus(); //метод ИЗМЕНЕНИЯ БОНУСА
          break;
        case EXIT_MAIN_MENU: // Выход в предыдущее меню
          runAdministratorMenu = false;
          break;
      }
    }
  }
  //________________________________________________________________________________________
  //________________________________________________________________________________________

  //________________________________________________________________________________________

  /**
   * метод ввода даты
   *
   * @param scanner
   * @return
   */
  public static LocalDate inputDate(Scanner scanner) {
    DateTimeFormatter inputDateFormate = DateTimeFormatter.ofPattern(
        "dd-MM-yy"); // ввод даты в формате "dd-MM-yy"
    System.out.println("Введите дату ->");
    String dateString;
    LocalDate date;
    while (true) {
      try {
        dateString = scanner.nextLine();  // ввод пользователем Даты в формате "dd-MM-yy"
        // Введеную строку переводим в формат времени
        date = LocalDate.parse(dateString, inputDateFormate);
        break;
      } catch (DateTimeParseException e) {
        System.out.println("Неверный формат ввода даты: " + e.getMessage());
      }
    }
    return date;
  }

  /**
   * метод проверки введеной пользователем ДАТЫ с датой записаной в файле
   *
   * @param scanner
   * @param session
   * @return
   */
  public static LocalDate checkDate(Scanner scanner, Session session) {
    LocalDate date = null;
    boolean run = true;
    while (run) {
      date = inputDate(scanner);

      if (!session.isDateCorrect(date)) {
        System.out.println("Несовпадение дат! ");
      } else {
        run = false;
        break;
      }
    }
    return date;
  }
  //________________________________________________________________________________________

  /**
   * метод ввода даты и сеанса
   *
   * @param scanner
   * @throws DataFormatException
   */

  public static LocalTime inputTime(Scanner scanner) throws DataFormatException {
    DateTimeFormatter inputTimeFormate = DateTimeFormatter.ofPattern(
        "HH:mm"); // ввод времени в формате "HH:mm"
    System.out.println("Введите времени сеанса HH:mm ->");
    String timeString;
    LocalTime time;
    while (true) {
      try {
        timeString = scanner.nextLine(); // ввод пользователем Времени в формате "HH:mm"
        time = LocalTime.parse(timeString, inputTimeFormate);
        break;
      } catch (DateTimeParseException e) {
        System.out.println("Неверный формат ввода времени: " + e.getMessage());
      }
    }
    return time;
  }

  /**
   * метод проверки введеного пользователем ВРЕМЕНИ со ВРЕМЕНЕМ записанвм в файле
   *
   * @param scanner
   * @param session
   * @return
   * @throws DataFormatException
   */
  public static LocalTime checkTime(Scanner scanner, Session session) throws DataFormatException {
    LocalTime time = null;
    boolean run = true;
    while (run) {
      time = inputTime(scanner);

      if (!session.isTimeCorrect(time)) {
        System.out.println("Несовпадение времени меансов! ");
      } else {
        run = false;
        break;
      }
    }
    return time;
  }
  //________________________________________________________________________________________

  /**
   * метод ввода Ряд/Колличество/Места
   */
  public static void inputRowQuantityPlace() {
    System.out.println("Осуществите ввод выбраного ряда ->");
    // Вывод карты ряда на экран
    System.out.println("Введите колличество необходимых мест ->");
    System.out.println("Мы радуем приятным бонусом за покупку 4х и более мест");
    System.out.println("Введите номера выбранных мест ->");

  }

  //________________________________________________________________________________________

  /**
   * метод ПОКУПКИ БИЛЕТА С ВЫВОДОМ КАРТЫ С ПОДСВЧЕННЫМИ МЕСТАМИ
   */
  public static void buyTickets(Scanner scanner, Session session) {
    //метод ввода ДАТЫ и СЕАНСА
    LocalDate date = checkDate(scanner, session);
    LocalDate time = checkDate(scanner, session);

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
    // фамилия колва билетов   ряд/место дата/время фильм
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
   // inputDate();
  }

  //________________________________________________________________________________________
  //метод вывода СТАТИСТИКИ ЗА СЕАНС
  public static void printStatisticsForSession() {
    // inputDateTime();
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
