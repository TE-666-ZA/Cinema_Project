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
      return; // Вернуться в главное меню
    }

    int selectedTimeIndex = selectTime(scanner, cinemaManager);
    if (selectedTimeIndex == 0) {
      return; // Вернуться в главное меню
    }

    hallMap.showPlacesByDateTime(selectedDateIndex, selectedTimeIndex);

    int selectedRow = selectRow(scanner);
    if (selectedRow == 0) {
      return; // Вернуться в главное меню
    }

    int numberOfSeats = selectNumberOfSeats(scanner);
    if (numberOfSeats == 0) {
      return; // Вернуться в главное меню
    }

    int[] selectedSeats = selectSeats(scanner, numberOfSeats);
    Character[] checkedFreeSeats = hallMap.getSessionPlacesByDateANdTime(selectedDateIndex,
        selectedTimeIndex, selectedRow);
    for (int i = 0; i < selectedSeats.length; i++) {
      while (checkedFreeSeats[selectedSeats[i] - 1].equals('X')) {
        System.out.println(
            "Это(-и) место(-а) " + selectedSeats[i] + " уже куплено(-ы), выберите другое(-ие)");
        selectedSeats = selectSeats(scanner, numberOfSeats);
      }
      checkedFreeSeats[selectedSeats[i] - 1] = 'X';
    }
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
              "\u001B[31mНе указаны номера мест. Введите, пожалуйста, цифры:\u001B[0m ");
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
          System.out.println("\u001B[31mНе получилось. Можно ввести только цифры:\u001B[0m");
        }
      }
    }
  }

  public static void ticketsExchangeOrReturn(Scanner scanner, CinemaManager cinemaManager) {
    // функционал обмена/возврата билетов
  }


  public static void printExit() {
    System.out.println(
        "\u001B[32m\t\t\t\tБЛАГОДАРИМ, ЧТО ВОСПОЛЬЗОВАЛИСЬ НАШИМ СЕРВИСОМ! \u001B[0m");
    System.out.println(
        "\u001B[32m\t\t\t\t\t\t\t\t\t\t\t\tДО НОВЫХ ВСТРЕЧ! \u001B[0m");
  }

  static void adminMenu(Scanner scanner, CinemaManager cinemaManager) {
    System.out.print("Логин: ");
    String username = scanner.nextLine();
    if (!username.equals(ADMIN_USERNAME)) {
      System.out.println("Неверный логин. Сейчас вы вернетесь в главное меню");
      return;
    }
    System.out.print("Пароль: ");
    String password = scanner.nextLine();
    if (!password.equals(ADMIN_PASSWORD)) {
      System.out.println("Неверный пароль. Сейчас вы вернетесь в главное меню");
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
          System.out.println("\u001B[31mНе получилось. Можно ввести только цифры:\u001B[0m");
        }
      }
    }
  }

  private static void changeSessionDate(Scanner scanner, CinemaManager cinemaManager) {

  }

  private static void changeSessionTime(Scanner scanner, CinemaManager cinemaManager) {

  }

  private static void changeMovieTitle(Scanner scanner, CinemaManager cinemaManager) {

  }

  private static void setNewBonus(Scanner scanner, CinemaManager cinemaManager) {

  }
}
