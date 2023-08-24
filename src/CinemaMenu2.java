import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class CinemaMenu2 {

  HallMap hallMap = new HallMap();
  CinemaManager cinemaManager = new CinemaManager();

  public CinemaMenu2() throws IOException {
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

  public static void buyTickets(Scanner scanner, CinemaManager cinemaManager, HallMap hallMap)
      throws IOException {
    cinemaManager.showSchedule();
    System.out.println("\u001B[32mВыберите дату и время сеанса :)\u001B[0m");
    System.out.println("В какой день хотите посмотреть кино?");
    int datesLength = cinemaManager.getDates().length;
    for (int i = 0; i < datesLength; i++) {
      System.out.println(
          (i + 1) + ". " + cinemaManager.getDates()[i].format(cinemaManager.getDateFormatter()));
    }
    int selectedDateIndex = readIntInput(scanner, "Выберите дату: ", 1, datesLength) - 1;
    System.out.println("Выберите время сеанса:");
    for (int i = 0; i < cinemaManager.getTimes().length; i++) {
      System.out.println(
          (i + 1) + ". " + cinemaManager.getTimes()[i].format(cinemaManager.getTimeFormatter()));
    }
    int selectedTimeIndex = scanner.nextInt();
    hallMap.showPlacesByDateTime(selectedDateIndex, selectedTimeIndex);
    System.out.println("Введите номер ряда от 1 до 5:");
    int selectedRow = scanner.nextInt();
    System.out.println("Введите места (через пробел):");
    System.out.println("Введите количество мест:");
    int numberOfSeats = scanner.nextInt();
    int[] selectedSeats = new int[numberOfSeats];
    System.out.println("Введите номера мест (разделяя пробелом):");
    for (int i = 0; i < numberOfSeats; i++) {
      selectedSeats[i] = scanner.nextInt();
    }
    hallMap.buyTickets(selectedDateIndex, selectedTimeIndex, selectedRow, selectedSeats);
    int ticketPrice = selectedSeats.length * 20;
    System.out.println("Стоимость билета: " + ticketPrice + " евро");
    System.out.println("Ваша покупка: " + ticketPrice + " евро");
    System.out.println("Номер чека: " + cinemaManager.getChequeNumber());
  }

  private static int readIntInput(Scanner scanner, String prompt, int min, int max) {
    int input;
    do {
      System.out.print(prompt);
      input = scanner.nextInt();
      if (input < min || input > max) {
        System.out.println(
            "Неверный ввод. Пожалуйста, введите число от " + min + " до " + max + ".");
      }
    } while (input < min || input > max);
    return input;
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

  public static void printMapWithSelectedSeats(Map<Integer, Character[]> sessionMap,
      int selectedRow,
      int[] selectedSeats) {
    // метод для вывода карты с выбранными местами
  }
}
