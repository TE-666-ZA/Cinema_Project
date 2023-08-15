import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class CinemaMenu {

  protected static final int INCORRECT = -1;

  // Метод для вывода разделительной строки между меню
  public static void printSeparator() {
    System.out.println("--------------------------------------------------------------");
  }

  /**
   * Методы провеки введеного пункта меню
   *
   * @param command
   * @return
   */

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
      EnumFreeSpaceMenu.printFreeSpaceMenu();
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

  /**
   * методы вывода и проверки меню пунктов 3.ПОКУПКА БИЛЕТОВ 4.ОБМЕН/ВОЗВРАТ БИЛЕТОВ
   *
   * @param command
   * @return
   */
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
      EnumBuyingTicketsMenu.printBuyingTicketsMenu();
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

  /**
   * Методы реализуюзие пункт
   *
   * @param scanner
   * @return
   */
  // 4.ОБМЕН/ВОЗВРАТ БИЛЕТОВ
  protected static int readCommandTicketsExchangeOrReturnMenu(Scanner scanner) {
    int command = INCORRECT;
    while (!isCommandEnumBuyingTicketsMenu(command)) {
      EnumTicketsExchangeOrReturnMenu.printTicketsExchangeOrReturnMenu();
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
      EnumAdministratorMenu.printAdministratorMenu();
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
  //метод вывода 3 КАРТ НА ВЫБРАННЫЙ ДЕНЬ
  public static void printHallMapsPerDay(Session session) throws IOException {
    LocalTime[] times = session.getTimes();
    String[] titles = session.getTitle();

    for (int sessionKey = 1; sessionKey <= 3; sessionKey++) {
      HallMap hallMap = new HallMap();
      Map<Integer, Character[]> sessionMap = hallMap.getSessionMap(sessionKey);

      if (sessionMap != null) {
        System.out.println(times[sessionKey - 1] + " " + titles[sessionKey - 1]);
        HallMap.showSessionMap(sessionMap);
      } else {
        System.out.println("Сеанс " + sessionKey + " не найден");
      }
    }
  }

  //________________________________________________________________________________________
  //метод вывода КАРТЫ С МЕСТАМИ КОТОРЫЕ ВЫБРАЛ КЛИЕНТ С ПОДСВЕТКОЙ МЕСТ
  public static void printMapWithYourLocation(Map<Integer, Character[]> sessionMap, int selectedRow,
      int[] selectedSeats, boolean isMoreThanFourTickets) {
    for (var entry : sessionMap.entrySet()) {
      int rowNumber = entry.getKey();
      Character[] rowArray = entry.getValue();
      System.out.print("Ряд " + rowNumber + ": ");
      for (int seat = 0; seat < rowArray.length; ++seat) {
        if (selectedRow == rowNumber && Arrays.asList(selectedSeats).contains(seat)) {
          System.out.print("\u001B[31m" + "X" + "\u001B[0m" + " ");
        } else if (isMoreThanFourTickets && rowArray[seat] == 'X') {
          System.out.print("\u001B[33m" + "*" + "\u001B[0m" + " ");
        } else {
          System.out.print(rowArray[seat] + " ");
        }
      }
      System.out.println();
    }
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
    printSeparator(); // вывод разделительной линии
    System.out.println("\u001B[32m\t\t\t\t2. СВОБОДНЫЕ МЕСТА\u001B[0m");
    //ввели дату и проверяем на совпадение с датами в тексте//
    LocalDate date = checkDate(scanner, session);
    //ВЫВОД ТРЕХ КАРТ ЗА ОДИН ДЕНЬ
    printHallMapsPerDay(session);
    //вызов подменю
    //2.1 ИЗМЕНИТЬ ДАТУ
    // 2.2 ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
    boolean runFreeSpaseMenu = true;
    while (runFreeSpaseMenu) {
      int commandFreeSpaseMenu = CinemaMenu.readCommandFreeSpaceMenu(scanner);
      EnumFreeSpaceMenu selectedFreeSpaseMenu = EnumFreeSpaceMenu.values()[commandFreeSpaseMenu];
      switch (selectedFreeSpaseMenu) {
        case CHANGE_DATE: // 2.1 ИЗМЕНИТЬ ДАТУ
          System.out.println(
              "\u001B[32m\t\t\t\t2. СВОБОДНЫЕ МЕСТА -> ИЗМЕНИТЬ ДАТУ: \u001B[0m");
          date = checkDate(scanner, session);
          //ВЫВОД ТРЕХ КАРТ ЗА ОДИН ДЕНЬ
          printHallMapsPerDay(session);
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
   * @param hallMap
   * @throws DataFormatException
   */
  public static void buyingTickets(Scanner scanner, Session session, HallMap hallMap)
      throws DataFormatException, IOException {
    CinemaMenu.printSeparator(); // вывод разделительной линии
    System.out.println("\u001B[32m\t\t\t\t3. ПОКУПКА БИЛЕТОВ\u001B[0m");

    //метод ПОКУПКИ БИЛЕТА С ВЫВОДОМ КАРТЫ С ПОДСВЧЕННЫМИ МЕСТАМИ
    //buyTickets(scanner,sessoin);

    //вызов подменю
    // 3.1 ПОДТВЕРЖДЕНИЕ ПОКУПКИ
    // 3.2 ИЗМЕНИТЬ ВЫБОР
    //3.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
    boolean runBuyingTicketsMenu = true;
    while (runBuyingTicketsMenu) {
      int commandBuyingTicketsMenu = CinemaMenu.readCommandBuyingTicketsMenu(scanner);
      EnumBuyingTicketsMenu selectedBuyingTicketsMenu = EnumBuyingTicketsMenu.values()[commandBuyingTicketsMenu];
      switch (selectedBuyingTicketsMenu) {
        case TO_CONFIRM: // 3.1 ПОДТВЕРЖДЕНИЕ ПОКУПКИ
          System.out.println(
              "\u001B[32m\t\t\t\t3. ПОКУПКА БИЛЕТОВ -> ПОДТВЕРЖДЕНИЕ ПОКУПКИ :\u001B[0m");
          //CinemaMenu.confirmPurchase(scanner, ); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
          //ввод фамилии
          runBuyingTicketsMenu = false;
          break;
        case CHANGE_SELECTION: // 3.2 ИЗМЕНИТЬ ВЫБОР
          System.out.println(
              "\u001B[32m\t\t\t\t3. ПОКУПКА БИЛЕТОВ -> ИЗМЕНИТЬ ВЫБОР :\u001B[0m");
          // метод ввода ДАТЫ и СЕАНСА
          LocalDate selectedDate = checkDate(scanner, session);
          LocalDate selectedTime = checkDate(scanner, session);

          // заново запрашиваем ряд/количество мест/места
          CinemaMenu.inputRowQuantityPlace(scanner,
              session);// метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
          //CinemaMenu.confirmPurchase(scanner); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
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
      throws DataFormatException, IOException {
    // вывод разделительной линии
    CinemaMenu.printSeparator();
    System.out.println("\u001B[32m\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ:\u001B[0m");
    // CinemaMenu.inputLastName();  //
    // ввод фио
    // вывод из файла  ПЕТРОВ 2 билета  Завтра 12.00 Русалочка
    boolean runTicketsExchangeOrReturnMenu = true;
    while (runTicketsExchangeOrReturnMenu) {
      int commandTicketsExchangeOrReturnMenu = CinemaMenu.readCommandTicketsExchangeOrReturnMenu(
          scanner);
      EnumTicketsExchangeOrReturnMenu selectedTicketsExchangeOrReturnMenu = EnumTicketsExchangeOrReturnMenu.values()[commandTicketsExchangeOrReturnMenu];
      switch (selectedTicketsExchangeOrReturnMenu) {
        // 4.1 ОБМЕН БИЛЕТОВ
        case TICKETS_EXCHANGE:
          System.out.println(
              "\u001B[32m\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ -> ОБМЕН БИЛЕТОВ :\u001B[0m");
          // вывод разделительной линии
          CinemaMenu.printSeparator();
          // вывод РАСПИСАНИЯ
          session.showSchedule();
          //метод ввода ДАТЫ и СЕАНСА
          LocalDate date = checkDate(scanner, session);
          LocalTime time = checkTime(scanner, session);

          // вывод карты мест для конкретного сеанса

          CinemaMenu.inputRowQuantityPlace(scanner,
              session); // метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
          //////////////////////////////////////////////////////////////////////////////////
          System.out.println("Введите ряд - >");
          //ввод ряда
          // Вывод карты ряда на экран
          System.out.println("Введите количество мест - >");
          ////////////////////////////////////////////////////////////////////////////////
          System.out.println("Вы обменяли______билета на ______   ______");
          runTicketsExchangeOrReturnMenu = false;
          break;
        //4.2 СДАТЬ БИЛЕТЫ
        case TO_RETURN_TICKETS:
          System.out.println(
              "\u001B[32m\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ -> СДАТЬ БИЛЕТЫ :\u001B[0m");
          // метод СДАТЬ БИЛЕТЫ С ВЫВОДОМ ИНФОРМАЦИИ НА КАКОЙ СЕАНС И ФИО
          CinemaMenu.toReturnTickets();
          runTicketsExchangeOrReturnMenu = false;
          break;
        // 4.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
        case CANCELLATION_RETURN_TICKETS:
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
  public static void administrator(Scanner scanner, Session session) throws DataFormatException {
    boolean runAdministratorMenu = true;
    while (runAdministratorMenu) {
      int commandAdministratorMenu = CinemaMenu.readCommandAdministratorMenu(scanner);
      EnumAdministratorMenu selectedAdministratorMenu = EnumAdministratorMenu.values()[commandAdministratorMenu];
      switch (selectedAdministratorMenu) {
        // 5.1 СТАТИСТИКА ЗА СЕАНС
        case STATISTICS_PER_SESSION:
          System.out.println(
              "\u001B[32m\t\t\t\t4. АДМИНИСТРАТОР -> СТАТИСТИКА ЗА СЕАНС :\u001B[0m");
          //метод вывода СТАТИСТИКИ ЗА СЕАНС
          CinemaMenu.printStatisticsForSession();
          break;
        // 5.2 СТАТИСТИКА ЗА ДЕНЬ
        case STATISTICS_FOR_DAY:
          System.out.println("\u001B[32m\t\t\t\t4. АДМИНИСТРАТОР -> СТАТИСТИКА ЗА ДЕНЬ :\u001B[0m");
          //метод вывода СТАТИСТИКИ ЗА ДЕНЬ
          CinemaMenu.printStatisticsForDay();
          break;
        // 5.3 ИЗМЕНИТЬ БОНУС
        case CHOICE_BONUS:
          System.out.println(
              "\u001B[32m\t\t\t\t4. АДМИНИСТРАТОР -> ИЗМЕНИТЬ БОНУС :\u001B[0m");
          //метод ИЗМЕНЕНИЯ БОНУСА
          CinemaMenu.changeBonus();
          break;
        // Выход в предыдущее меню
        case EXIT_MAIN_MENU:
          runAdministratorMenu = false;
          break;
      }
    }
  }
  //________________________________________________________________________________________

  /**
   * метод ввода даты
   *
   * @param scanner
   * @return
   */
  public static LocalDate inputDate(Scanner scanner) {
    // ввод даты в формате "dd-MM-yy"
    DateTimeFormatter inputDateFormate = DateTimeFormatter.ofPattern(
        "dd-MM-yy");
    System.out.println("Введите дату ->");
    String dateString;
    LocalDate date;
    while (true) {
      try {
        // ввод пользователем Даты в формате "dd-MM-yy"
        dateString = scanner.nextLine();
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
   * метод ввода сеанса и проверка на его правильный ввод
   *
   * @param scanner
   * @throws DataFormatException
   */

  public static LocalTime inputTime(Scanner scanner) throws DataFormatException {
    // ввод времени в формате "HH:mm"
    DateTimeFormatter inputTimeFormate = DateTimeFormatter.ofPattern(
        "HH:mm");
    System.out.println("Введите времени сеанса HH:mm ->");
    String timeString;
    LocalTime time;
    while (true) {
      try {
        // ввод пользователем Времени в формате "HH:mm"
        timeString = scanner.nextLine();
        time = LocalTime.parse(timeString, inputTimeFormate);
        break;
      } catch (DateTimeParseException e) {
        System.out.println("Неверный формат ввода времени: " + e.getMessage());
      }
    }
    return time;
  }

  /**
   * метод проверки введеного пользователем ВРЕМЕНИ со ВРЕМЕНЕМ записаным в файле
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
        System.out.println("Несовпадение времени сеансов! ");
      } else {
        run = false;
        break;
      }
    }
    return time;
  }
  //________________________________________________________________________________________

  /**
   * метод ввода Ряд/Количество/Места
   */
  public static int[] inputRowQuantityPlace(Scanner scanner, Session session) throws IOException {
    System.out.print("Введите номер ряда -> ");
    int rowNumber = Integer.parseInt(scanner.nextLine());

    LocalDate date = checkDate(scanner, session);
    Map<Integer, String> availableMovies = session.getMoviesForDate(date);
    if (availableMovies.isEmpty()) {
      System.out.println("В выбранный день доступных для просмотра фильмов нет");
      return new int[0];
    }

    System.out.println("Выберите фильм из списка: ");
    for (Map.Entry<Integer, String> entry : availableMovies.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue());
    }

    int selectedMovieKey = -1;
    while (!availableMovies.containsKey(selectedMovieKey)) {
      System.out.print("Введите номер выбранного фильма: ");
      selectedMovieKey = Integer.parseInt(scanner.nextLine());
    }
    // Просто используем номер фильма как номер сеанса
    int sessionKey = selectedMovieKey;
    HallMap hallMap = new HallMap();
    hallMap.showRowMap(rowNumber, sessionKey, hallMap);
    // Метод для ввода количества
    int quantity = readQuantity(scanner);
    if (quantity >= 4) {
      System.out.println("Мы радуем приятным бонусом за покупку 4х и более мест");
    }

    int[] selectedSeats = readSelectedSeats(scanner, quantity);
    return selectedSeats;
  }

  /**
   * метод ввода и проверки Количества мест
   *
   * @param scanner
   * @return
   */
  private static int readQuantity(Scanner scanner) {
    boolean validQuantity = false;
    int quantity = 0;
    while (!validQuantity) {
      try {
        System.out.print("Введите количество необходимых мест: ");
        quantity = Integer.parseInt(scanner.nextLine());
        validQuantity = true;
      } catch (NumberFormatException e) {
        System.out.println("Неверный формат. Введите число.");
      }
    }
    return quantity;
  }

  /**
   * метод выбора места при покупке
   *
   * @param scanner
   * @param quantity
   * @return
   */
  private static int[] readSelectedSeats(Scanner scanner, int quantity) {
    int[] selectedSeats = new int[quantity];
    for (int i = 0; i < quantity; i++) {
      boolean validSeat = false;
      while (!validSeat) {
        try {
          System.out.print("Введите номер выбранного места: ");
          int seatNumber = Integer.parseInt(scanner.nextLine());
          selectedSeats[i] = seatNumber;
          validSeat = true;
        } catch (NumberFormatException e) {
          System.out.println("Неверный формат. Введите число.");
        }
      }
    }
    return selectedSeats;
  }

  //________________________________________________________________________________________

  /**
   * метод ПОКУПКИ БИЛЕТА С ВЫВОДОМ КАРТЫ С ПОДСВЧЕННЫМИ МЕСТАМИ
   */
  public static void buyTickets(Scanner scanner, Session session) throws IOException {
    HallMap hallMap = new HallMap();
    LocalDate date = checkDate(scanner, session);
    Map<Integer, String> availableMovies = session.getMoviesForDate(date);

    if (availableMovies.isEmpty()) {
      System.out.println("В выбранный день доступных для просмотра фильмов нет");
      return;
    }

    System.out.println("Выберите фильм из списка:");
    for (Map.Entry<Integer, String> entry : availableMovies.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue());
    }

    int selectedMovieKey = -1;
    while (!availableMovies.containsKey(selectedMovieKey)) {
      System.out.print("Введите номер выбранного фильма: ");
      selectedMovieKey = Integer.parseInt(scanner.nextLine());
    }

    String selectedMovieTitle = availableMovies.get(selectedMovieKey);
    session.selectMovie(date, selectedMovieTitle);
    // Просто используем номер фильма как номер сеанса
    int sessionKey = selectedMovieKey;
    Map<Integer, Character[]> sessionMap = hallMap.getSessionMap(sessionKey);
    // Получение времени сеанса
    LocalTime time = session.getSchedule().get(date).get(selectedMovieTitle);

    System.out.print("Введите номер ряда: ");
    int selectedRow = Integer.parseInt(scanner.nextLine());

    System.out.println("Карта ряда:");
    // Метод вывода карты ряда
    hallMap.showRowMap(selectedRow, sessionKey, hallMap);
    // Метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
    int[] selectedSeats = inputRowQuantityPlace(scanner, session);
    boolean isMoreThanFourTickets = selectedSeats.length >= 4;
    // Метод вывода 1й КАРТЫ НА ВЫБРАННЫЙ СЕАНС С ПОДСВЕТКОЙ МЕСТ
    printMapWithYourLocation(sessionMap, selectedRow, selectedSeats,
        isMoreThanFourTickets);

    // Подтверждение покупки
    System.out.println("Сумма покупки ...");
    confirmPurchase(scanner, selectedSeats, selectedRow, date.toString(), time.toString());
  }

  //________________________________________________________________________________________

  /**
   * метод подтверждения покупки в меню ПОКУПКА БИЛЕТОВ
   *
   * @param scanner
   * @param selectedSeats
   * @param rowNumber
   * @param selectedDate
   * @param selectedTime
   * @throws IOException
   */
  public static void confirmPurchase(Scanner scanner, int[] selectedSeats, int rowNumber,
      String selectedDate, String selectedTime) throws IOException {
    System.out.println("Подтвердите покупку вводом ФАМИЛИИ -> ");
    String lastName = scanner.nextLine();
    String purchaseInfo =
        selectedDate + " " + selectedTime + " Ряд " + rowNumber + " Места " + arrayToString(
            selectedSeats) + " Фамилия: " + lastName;
    FileEditor fileEditor = new FileEditor("res/VisitorsData.txt");
    fileEditor.write(new String[]{purchaseInfo}, "");
  }
//TODO удалить этот метод

  /**
   * @param scanner
   * @throws IOException
   */
  public static void inputLastName(Scanner scanner) throws IOException {
    System.out.println("Введите ФАМИЛИЮ -> ");
    String lastName = scanner.nextLine();

  }

  /**
   * метод сохранения символов строки в массив
   *
   * @param arr
   * @return
   */
  public static String arrayToString(int[] arr) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < arr.length; i++) {
      sb.append(arr[i]);
      if (i < arr.length - 1) {
        sb.append(", ");
      }
    }
    return sb.toString();
  }

  //________________________________________________________________________________________

  /**
   * метод СДАТЬ БИЛЕТЫ
   */
  public static void toReturnTickets() {
    System.out.println(" ФИО ___билетов на ____ _____");
    System.out.println(" Возврат _____ евро");
    System.out.println(" Спасибо, что воспользовались нашим сервисом!");
  }

  //________________________________________________________________________________________

  /**
   * метод ввода пароля Администратора
   *
   * @param scanner
   * @return
   */
  public static boolean inputPass(Scanner scanner) {
    // сохраненный пароль для Администратора
    String pass = "Admin945";
    System.out.println("Введите пароль ->");
    // ввод пароля с консоли
    String passUser = scanner.nextLine();
    // сравниваем введенный и созраненные пароли
    if (pass.equals(passUser)) {
      System.out.println("Пароль верный!");
      return true;
    } else {
      System.out.println("Пароль неверный.");
      return false;
    }
  }

  //________________________________________________________________________________________

  /**
   * метод вывода СТАТИСТИКИ ЗА ДЕНЬ
   */
  public static void printStatisticsForDay() {
    // inputDate();
  }

  //________________________________________________________________________________________

  /**
   * метод вывода СТАТИСТИКИ ЗА СЕАНС
   */
  public static void printStatisticsForSession() {
    // inputDateTime();
  }

  //________________________________________________________________________________________

  /**
   * метод ИЗМЕНЕНИЯ БОНУСА
   */
  public static void changeBonus() {
    System.out.println("Введите НОВЫЙ БОНУС ->");
  }

  public static void printExit() {
    System.out.println(
        "\u001B[32m\t\t\t\tБЛАГОДАРИМ, ЧТО ВОСПОЛЬЗОВАЛИСЬ НАШИМ СЕРВИСОМ! \u001B[0m");
    System.out.println(
        "\u001B[32m\t\t\t\t\t\t\t\t\t\t\t\tДО НОВЫХ ВСТРЕЧ! \u001B[0m");
    System.out.println("Проект реализован 'RAT FAMILY' ");
    System.out.println("В ролях - Кенан Июсубови ");
    System.out.println("В ролях - Устинья Петрова");
    System.out.println("Озвучка - Виктория Щербина\n");
    System.out.println(
        "По вопросам рекламы и озвучки ваших проектов обращаться we.ratfamily@gmail.com ");
  }


  /**
   * Enum класс реализующий вывод Главного меню
   */
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

  /**
   * Enum класс реализующий вывод 2. СВОБОДНЫЕ МЕСТА
   */
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

    public static void printFreeSpaceMenu() {
      System.out.println(EnumFreeSpaceMenu.CHANGE_DATE.getMessageEnumFreeSpaceMenu());
      System.out.println(EnumFreeSpaceMenu.RETURN_TO_THE_MAIN_MENU.getMessageEnumFreeSpaceMenu());
      printSeparator();
    }
  }

  /**
   * Enum класс реализующий вывод 3. ПРОДАЖА БИЛЕТОВ
   */
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

    public static void printBuyingTicketsMenu() {
      System.out.println(EnumBuyingTicketsMenu.TO_CONFIRM.getMessageEnumBuyingTicketsMenu());
      System.out.println(EnumBuyingTicketsMenu.CHANGE_SELECTION.getMessageEnumBuyingTicketsMenu());
      System.out.println(EnumBuyingTicketsMenu.CANCELLATION.getMessageEnumBuyingTicketsMenu());
      printSeparator();
    }
  }

  /**
   * Enum класс реализующий вывод 4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ
   */
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

    public static void printTicketsExchangeOrReturnMenu() {
      System.out.println(
          EnumTicketsExchangeOrReturnMenu.TICKETS_EXCHANGE.getMessageEnumTicketsExchangeOrReturnMenu());
      System.out.println(
          EnumTicketsExchangeOrReturnMenu.TO_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
      System.out.println(
          EnumTicketsExchangeOrReturnMenu.CANCELLATION_RETURN_TICKETS.getMessageEnumTicketsExchangeOrReturnMenu());
      printSeparator();
    }

  }

  /**
   * Enum класс реализующий вывод 5. АДМИНИСТРАТОР
   */
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

    public static void printAdministratorMenu() {
      System.out.println(
          EnumAdministratorMenu.STATISTICS_PER_SESSION.getMessageEnumAdministratorMenu());
      System.out.println(
          EnumAdministratorMenu.STATISTICS_FOR_DAY.getMessageEnumAdministratorMenu());
      System.out.println(EnumAdministratorMenu.CHOICE_BONUS.getMessageEnumAdministratorMenu());
      System.out.println(EnumAdministratorMenu.EXIT_MAIN_MENU.getMessageEnumAdministratorMenu());
      printSeparator();
    }
  }
}