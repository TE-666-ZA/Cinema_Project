import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

public class CinemaMenu2 {

  HallMap hallMap = new HallMap();
  CinemaManager cinemaManager = new CinemaManager();
  static final int TICKET_PRICE = 2;
  private static final String ADMIN_USERNAME = "admin1";
  private static final String ADMIN_PASSWORD = "cinema";

  public CinemaMenu2() {
  }

  public enum EnumMainMenu {
    TIMETABLES("Расписание"),
    BUYING_TICKETS("Покупка билетов"),
    TICKETS_EXCHANGE_OR_RETURN("Обмен и возврат билетов"),
    ADMINISTRATOR("Меню администратора"),
    EXIT("Выход");
    private final String menuText;

    EnumMainMenu(String menuText) {
      this.menuText = menuText;
    }

    public String getMenuText() {
      return menuText;
    }
  }

  public enum EnumAdminMenu {
    CHANGE_DATE("Изменить дату сеанса"),
    CHANGE_TIME("Изменить время сеанса"),
    CHANGE_TITLE("Изменить название фильма"),
    SET_BONUS("Назначить новый бонус"),
    BACK("Вернуться в главное меню");
    private final String adminMenuText;

    EnumAdminMenu(String menuText) {
      this.adminMenuText = menuText;
    }

    public String getAdminMenuText() {
      return adminMenuText;
    }
  }

  public static void printSeparator() {
    System.out.println("============================================================");
  }

  public static int readCommandMainMenu(Scanner scanner) {
    int menuLength = EnumMainMenu.values().length;
    for (int i = 0; i < menuLength; i++) {
      System.out.println(i + 1 + ". " + EnumMainMenu.values()[i].getMenuText());
    }
    int input;
    do {
      while (!scanner.hasNextInt()) {
        System.out.print(
            "\u001B[31mИзвините, эти символы не подходят. Пожалуйста, введите число: \u001B[0m");
        scanner.next();
      }
      input = scanner.nextInt();
      if (input < 1 || input > menuLength) {
        System.out.print(
            "\u001B[31mНеверно. Пожалуйста, введите число от 1 до " + menuLength + ": \u001B[0m");
      }
    } while (input < 1 || input > menuLength);

    return input - 1;
  }

  public static void printMapWithYourLocation(Map<Integer, Character[]> sessionMap,
      int selectedRow,
      int[] selectedSeats,
      boolean isMoreThanFourTickets) {
    System.out.println("Карта мест:");
    for (Map.Entry<Integer, Character[]> entry : sessionMap.entrySet()) {
      int rowNumber = entry.getKey();
      Character[] rowSeats = entry.getValue();

      System.out.print("Ряд " + rowNumber + ": ");

      for (int i = 0; i < rowSeats.length; i++) {
        if (selectedRow == rowNumber && contains(selectedSeats, i + 1)) {
          System.out.print("\u001B[31m"); // красный цвет для выбранных мест
        } else if (isMoreThanFourTickets && i < 4) {
          System.out.print("\u001B[33m"); // желтый цвет для более четырех мест
        } else {
          System.out.print("\u001B[0m"); // обычный цвет
        }
        System.out.print(rowSeats[i] + " ");
      }

      System.out.println("\u001B[0m"); // восстановление обычного цвета
    }
  }

  private static boolean contains(int[] array, int value) {
    for (int num : array) {
      if (num == value) {
        return true;
      }
    }
    return false;
  }

  public static boolean inputPass(Scanner scanner) {
    // метод ввода пароля администратора
    return true;
  }

  public static void administrator(Scanner scanner, CinemaManager cinemaManager) {
    // меню администратора
  }

  public static void buyingTickets(Scanner scanner, CinemaManager cinemaManager, HallMap hallMap) {

  }

  static void buyTickets(Scanner scanner, CinemaManager cinemaManager, HallMap hallMap) {
    cinemaManager.showSchedule();

    int selectedDateIndex = selectDate(scanner, cinemaManager);
    if (selectedDateIndex == 0) {
      return;
    }

    int selectedTimeIndex = selectTime(scanner, cinemaManager);
    if (selectedTimeIndex == 0) {
      return;
    }

    hallMap.showPlacesByDateTime(selectedDateIndex, selectedTimeIndex);

    int selectedRow = selectRow(scanner);
    if (selectedRow == 0) {
      return;
    }

    int numberOfSeats = selectNumberOfSeats(scanner);
    if (numberOfSeats == 0) {
      return;
    }

    int[] selectedSeats = selectSeats(scanner, numberOfSeats);
    Character[] checkedFreeSeats = hallMap.getSessionPlacesByDateANdTime(selectedDateIndex,
        selectedTimeIndex, selectedRow);
    for (int i = 0; i < selectedSeats.length; i++) {
      while (checkedFreeSeats[selectedSeats[i] - 1].equals('X')) {
        System.out.println(
            "\u001B[31mЭто(-и) место(-а) уже куплено(-ы), выберите другое(-ие)\u001B[0m");
        selectedSeats = selectSeats(scanner, numberOfSeats);
      }
      checkedFreeSeats[selectedSeats[i] - 1] = 'X';
    }
    System.out.println("\u001B[35mКак можно оплатить покупку:\u001B[0m");
    for (int i = 0; i < cinemaManager.getPaymentMethods().length; i++) {
      System.out.println((i + 1) + ". " + cinemaManager.getPaymentMethods()[i]);
    }
    System.out.println();
    int paymentMethodChoice = readBuyingInput(scanner, 0,
        "Введите номер метода оплаты (или введите 0 для отмены): ", 1,
        cinemaManager.getPaymentMethods().length);
    if (paymentMethodChoice == 0) {
      System.out.println("Возвращаемся в главное меню...");
      return;
    }
    String selectedPaymentMethod = cinemaManager.getPaymentMethods()[paymentMethodChoice - 1];
    cinemaManager.writeCheque(selectedDateIndex, selectedTimeIndex, selectedRow, checkedFreeSeats,
        paymentMethodChoice, selectedPaymentMethod);
    hallMap.buyTickets(selectedDateIndex, selectedTimeIndex, selectedRow, checkedFreeSeats);
    cinemaManager.writeAll();

    int price = selectedSeats.length * TICKET_PRICE;
    System.out.println("Стоимость одного билета: " + TICKET_PRICE + " евро");
    System.out.println("Ваша покупка: " + price + " евро");
    System.out.println("Номер чека: " + cinemaManager.getChequeNumber());
  }

  private static int selectDate(Scanner scanner, CinemaManager cinemaManager) {
    System.out.println("\u001B[32mВыберите дату и время сеанса.\u001B[0m");

    int datesLength = cinemaManager.getDates().length;
    for (int i = 0; i < datesLength; i++) {
      System.out.println(
          (i + 1) + ". " + cinemaManager.getDates()[i].format(cinemaManager.getDateFormatter()));
    }

    return readBuyingInput(scanner, 0,
        "Пожалуйста, выберите дату сеанса (или введите 0 для выхода в главное меню):", 1,
        datesLength);
  }

  private static int selectTime(Scanner scanner, CinemaManager cinemaManager) {
    System.out.println("Выберите время сеанса:");
    for (int i = 0; i < cinemaManager.getTimes().length; i++) {
      System.out.println(
          (i + 1) + ". " + cinemaManager.getTimes()[i].format(cinemaManager.getTimeFormatter()));
    }

    return readBuyingInput(scanner, 0,
        "Пожалуйста, введите номер времени сеанса (или введите 0 для выхода в главное меню): ", 1,
        cinemaManager.getTimes().length);
  }

  private static int selectRow(Scanner scanner) {
    return readBuyingInput(scanner, 0,
        "Пожалуйста, введите номер ряда (или введите 0 для выхода в главное меню): ", 1, 5);
  }

  private static int selectNumberOfSeats(Scanner scanner) {
    return readBuyingInput(scanner, 0,
        "Пожалуйста, выберите количество билетов (или введите 0 для выхода в главное меню): ", 1,
        90);
  }

  private static int[] selectSeats(Scanner scanner, int numberOfSeats) {
    int[] selectedSeats = new int[numberOfSeats];
    System.out.println("Пожалуйста, введите номера мест: ");
    for (int i = 0; i < numberOfSeats; i++) {
      while (true) {
        if (scanner.hasNextInt()) {
          selectedSeats[i] = scanner.nextInt();
          break;
        } else {
          System.out.print(
              "\u001B[31mНе указаны номера мест. Введите, пожалуйста, положительные цифры:\u001B[0m ");
          scanner.next();
        }
      }
    }
    return selectedSeats;
  }

  private static int readBuyingInput(Scanner scanner, int cancelValue, String textForUser, int min,
      int max) {
    System.out.println(textForUser);

    while (true) {
      String inputStr = scanner.nextLine();

      if (inputStr.matches("\\d+")) {
        int input = Integer.parseInt(inputStr);
        if (input == cancelValue || (input >= min && input <= max)) {
          return input;
        } else {
          System.out.println(
              "\u001B[31mЧто-то пошло не так. Введите число от " + min + " до " + max + " (или "
                  + cancelValue + " для отмены): \u001B[0m");
        }
      } else {
        if (!inputStr.isEmpty() && !inputStr.equals("0")) {
          System.out.println(
              "\u001B[31mНе получилось. Можно ввести только положительные цифры:\u001B[0m");
        }
      }
    }
  }

  public static void ticketsExchangeOrReturn(Scanner scanner, CinemaManager cinemaManager) {
    // функционал обмена/возврата билетов
  }

  static boolean checkLogAndPass(Scanner scanner) {
    System.out.print("Логин: ");
    scanner.nextLine();
    String username = scanner.nextLine();
    if (!username.equals(ADMIN_USERNAME)) {
      System.out.println("\u001B[31mНеверный логин, доступа к меню администратора нет\u001B[0m");
      return false;
    }
    System.out.print("Пароль: ");
    String password = scanner.nextLine();
    if (!password.equals(ADMIN_PASSWORD)) {
      System.out.println("\u001B[31mНеверный пароль, доступа к меню администратора нет\u001B[0m");
      return false;
    }
    System.out.println("\u001B[32mОтлично, вы прошли авторизацию!\u001B[0m");
    return true;
  }

  static void adminMenu(Scanner scanner, CinemaManager cinemaManager) {
    if (!checkLogAndPass(scanner)) {
      return;
    }
    System.out.println("\u001B[32mМеню администратора:\u001B[0m");
    for (int i = 0; i < EnumAdminMenu.values().length; i++) {
      EnumAdminMenu menuItem = EnumAdminMenu.values()[i];
      System.out.println((i + 1) + ". " + menuItem.getAdminMenuText());
    }
    System.out.print("\nВыберите пункт меню: ");
    int choice = readAdminMenuChoice(scanner);
    EnumAdminMenu selectedMenuItem = EnumAdminMenu.values()[choice - 1];
    switch (selectedMenuItem) {
      case CHANGE_DATE:
        changeSessionDate(scanner, cinemaManager);
        break;
      case CHANGE_TIME:
        changeSessionTime(scanner, cinemaManager);
        break;
      case CHANGE_TITLE:
        changeMovieTitle(scanner, cinemaManager);
        break;
      case SET_BONUS:
        setNewBonus(scanner, cinemaManager);
        break;
      case BACK:
        System.out.println("Возвращаемся в главное меню...");
        break;
    }
  }

  private static int readAdminMenuChoice(Scanner scanner) {
    int maxChoice = EnumAdminMenu.values().length;
    while (true) {
      String inputStr = scanner.nextLine();

      if (inputStr.matches("\\d+")) {
        int input = Integer.parseInt(inputStr);
        if (input >= 1 && input <= maxChoice) {
          return input;
        } else {
          System.out.println(
              "\u001B[31mЧто-то пошло не так. Введите число от 1 до " + maxChoice + ":\u001B[0m");
        }
      } else {
        if (!inputStr.isEmpty()) {
          System.out.println(
              "\u001B[31mНе получилось. Можно ввести только положительные цифры:\u001B[0m");
        }
      }
    }
  }

  private static void changeSessionDate(Scanner scanner, CinemaManager cinemaManager) {
    cinemaManager.showSchedule();
    System.out.println("Выберите дату сеанса, чтобы изменить:");
    LocalDate[] sessionDates = cinemaManager.getDates();
    for (int i = 0; i < sessionDates.length; i++) {
      System.out.println((i + 1) + ". " + sessionDates[i].format(cinemaManager.getDateFormatter()));
    }
    int dateChoice = readBuyingInput(scanner, 0,
        "Введите номер даты (или введите 0 для отмены): ",
        1, sessionDates.length);
    if (dateChoice == 0) {
      System.out.println("Отмена изменения даты сеанса");
      return;
    }
    LocalDate selectedDate = sessionDates[dateChoice - 1];
    System.out.print("Введите новую дату сеанса в формате дд-мм-гггг: ");
    String newDateStr = scanner.nextLine();
    LocalDate newDate = LocalDate.parse(newDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    if (!cinemaManager.isDateCorrect(LocalDate.parse(newDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy")))) {
      System.out.println("\u001B[31mВведена некорректная дата\u001B[0m");
      return;
    }
    cinemaManager.setDate(newDate.toString(), dateChoice - 1);
    System.out.println("\u001B[32mДата успешно изменена.\u001B[0m");
  }

  private static void changeSessionTime(Scanner scanner, CinemaManager cinemaManager) {
    cinemaManager.showSchedule();
    System.out.println("Выберите время сеанса, чтобы изменить:");
    LocalTime[] sessionTimes = cinemaManager.getTimes();
    for (int i = 0; i < sessionTimes.length; i++) {
      System.out.println((i + 1) + ". " + sessionTimes[i].format(cinemaManager.getTimeFormatter()));
    }
    int timeChoice = readBuyingInput(scanner, 0,
        "Введите номер времени (или введите 0 для отмены): ",
        1, sessionTimes.length);
    if (timeChoice == 0) {
      System.out.println("Отмена изменения времени сеанса.");
      return;
    }
    LocalTime selectedTime = sessionTimes[timeChoice - 1];
    System.out.print("Введите новое время сеанса в формате чч:мм: ");
    String newTimeStr = scanner.nextLine();
    LocalTime newTime = LocalTime.parse(newTimeStr, DateTimeFormatter.ofPattern("HH:mm"));
    if (!cinemaManager.isTimeCorrect(newTime)) {
      System.out.println("\u001B[31mВведено некорректное время\u001B[0m");
      return;
    }
    cinemaManager.setTime(newTime.toString(), timeChoice - 1);
    System.out.println("\u001B[32mВремя успешно изменено.\u001B[0m");
  }


  private static int readChoice(Scanner scanner, int maxChoice, String prompt) {
    int choice;
    do {
      System.out.println(prompt);
      while (!scanner.hasNextInt()) {
        System.out.print("\u001B[31mИзвините, эти символы не подходят. Пожалуйста, введите число: \u001B[0m");
        scanner.next();
      }
      choice = scanner.nextInt();
      if (choice < 1 || choice > maxChoice) {
        System.out.print("\u001B[31mНеверно. Пожалуйста, введите число от 1 до " + maxChoice + ": \u001B[0m");
      }
    } while (choice < 1 || choice > maxChoice);
    return choice;
  }

  private static void changeMovieTitle(Scanner scanner, CinemaManager cinemaManager) {
    cinemaManager.showSchedule();
    System.out.println("Выберите день и время сеанса:");
    LocalDate[] sessionDates = cinemaManager.getDates();
    LocalTime[] sessionTimes = cinemaManager.getTimes();
    for (int i = 0; i < sessionDates.length; i++) {
      System.out.println((i + 1) + ". " + sessionDates[i].format(cinemaManager.getDateFormatter()));
    }
    int dateChoice = readBuyingInput(scanner, 0,
        "Введите номер даты (или введите 0 для отмены): ",
        1, sessionDates.length);
    if (dateChoice == 0) {
      System.out.println("Отмена изменения названия фильма.");
      return;
    }
    for (int i = 0; i < sessionTimes.length; i++) {
      System.out.println((i + 1) + ". " + sessionTimes[i].format(cinemaManager.getTimeFormatter()));
    }
    int timeChoice = readBuyingInput(scanner, 0,
        "Введите номер времени (или введите 0 для отмены): ",
        1, sessionTimes.length);
    LocalDate selectedDate = sessionDates[dateChoice - 1];
    LocalTime selectedTime = sessionTimes[timeChoice - 1];

    if (!cinemaManager.isDateCorrect(selectedDate)) {
      System.out.println("\u001B[31mВведена некорректная дата\u001B[0m");
      return;
    }
    int dateIndex = getDateIndex(cinemaManager, selectedDate);
    Map<Integer, String> moviesForDate = cinemaManager.getMoviesForDate(selectedDate);
    if (moviesForDate.isEmpty()) {
      System.out.println("\u001B[31mНа выбранный день нет доступных фильмов.\u001B[0m");
      return;
    }
    System.out.println("Выберите фильм для изменения названия:");
    for (Map.Entry<Integer, String> entry : moviesForDate.entrySet()) {
      System.out.println(entry.getKey() + ". " + entry.getValue());
    }
    int movieChoice = readBuyingInput(scanner, 0,
        "Введите номер фильма (или введите 0 для отмены): ",
        1, moviesForDate.size());
    if (movieChoice == 0) {
      System.out.println("Отмена изменения названия фильма");
      return;
    }
    System.out.print("Введите новое название фильма: ");
    String newTitle = scanner.nextLine();
    System.out.println("Выберите жанр фильма (введите номер из списка):");
    String[] genres = {"боевик", "детектив", "драма", "исторический фильм",
        "комедия", "мелодрама", "трагедия", "трагикомедия"};
    for (int i = 0; i < genres.length; i++) {
      System.out.println((i + 1) + ". " + genres[i]);
    }
    int genreChoice = readBuyingInput(scanner, 0,
        "Введите номер жанра (или введите 0 для отмены): ", 1, genres.length);
    System.out.println("Выберите рейтинг фильма (введите номер из списка):");
    String[] ratings = {"G", "PG", "PG-13", "R", "NC-17"};
    for (int i = 0; i < ratings.length; i++) {
      System.out.println((i + 1) + ". " + ratings[i]);
    }
    int ratingChoice = readBuyingInput(scanner, 0,
        "Введите номер рейтинга (или введите 0 для отмены): ", 1, ratings.length);
    String newMovieInfo = newTitle + " (" + genres[genreChoice - 1] + ", " + ratings[ratingChoice - 1] + ")";
    cinemaManager.setTitle(newMovieInfo, movieChoice);
    System.out.println("\u001B[32mНазвание фильма успешно изменено.\u001B[0m");
  }

  private static void setNewBonus(Scanner scanner, CinemaManager cinemaManager) {
    // getDateIndex(cinemaManager, select);
    System.out.print("Введите новый бонус для фильмов: ");
    String newBonus = scanner.nextLine();

    // cinemaManager.setBonus(newBonus, getDateIndex(cinemaManager, selectDate(scanner, cinemaManager)));
    System.out.println("\u001B[32mБонус для фильмов успешно изменен.\u001B[0m");
  }

  private static int getDateIndex(CinemaManager cinemaManager, LocalDate selectedDate) {
    LocalDate[] sessionDates = cinemaManager.getDates();
    for (int i = 0; i < sessionDates.length; i++) {
      if (sessionDates[i].equals(selectedDate)) {
        return i;
      }
    }
    return -1;
  }

  private static int getTimeIndex(CinemaManager cinemaManager, LocalTime selectedTime) {
    LocalTime[] sessionTimes = cinemaManager.getTimes();
    for (int i = 0; i < sessionTimes.length; i++) {
      if (sessionTimes[i].equals(selectedTime)) {
        return i;
      }
    }
    return -1;
  }

  public static void printExit() {
    System.out.println(
        "\u001B[32m\t\t\t\tБЛАГОДАРИМ, ЧТО ВОСПОЛЬЗОВАЛИСЬ НАШИМ СЕРВИСОМ! \u001B[0m");
    System.out.println(
        "\u001B[32m\t\t\t\t\t\t\t\t\t\t\t\tДО НОВЫХ ВСТРЕЧ! \u001B[0m");
  }
}
