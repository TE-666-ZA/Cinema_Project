import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CinemaMenu2 {

  private static int selectedDateIndex;
  HallMap hallMap = new HallMap();
  CinemaManager cinemaManager = new CinemaManager();
  static final int TICKET_PRICE = 2;

  public CinemaMenu2(HallMap hallMap, CinemaManager cinemaManager) {
    this.hallMap = hallMap;
    this.cinemaManager = cinemaManager;
  }

  public enum EnumMainMenu {
    TIMETABLES("Расписание"),
    BUYING_TICKETS("Покупка билетов"),
    SEE_BUSY_ROWS("Просмотр свободных рядов"),
    RETURN_TICKETS("Возврат билетов"),
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
    System.out.println("\u001B[32mВыберите дату и время сеанса:\u001B[0m");
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

  public static void seeBusyRows(Scanner scanner, CinemaManager cinemaManager, HallMap hallMap) {
    System.out.println("\u001B[35mВы можете посмотреть загруженность зала\u001B[0m");
    List<Integer> rowNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    int selectedDateIndex = selectDate(scanner, cinemaManager);
    if (selectedDateIndex == 0) {
      return;
    }
    int selectedTimeIndex = selectTime(scanner, cinemaManager);
    if (selectedTimeIndex == 0) {
      return;
    }
    List<RowInfo> rowsInfo = new ArrayList<>();
    for (Integer rowNumber : rowNumbers) {
      Character[] rowSeats = hallMap.getSessionPlacesByDateANdTime(selectedDateIndex,
          selectedTimeIndex, rowNumber);
      int occupiedSeats = rowSeats.length - countFreeSeats(rowSeats);
      rowsInfo.add(new RowInfo(rowNumber, rowSeats, occupiedSeats));
    }
    RowComparator rowComparator = new RowComparator();
    rowsInfo.sort(
        Comparator.comparingInt(rowInfo -> rowComparator.countFreeSeats(rowInfo.getRowSeats())));
    System.out.println("\u001B[35mВыше - самые свободные ряды. Ниже - самые загруженные:\u001B[0m");
    for (RowInfo rowInfo : rowsInfo) {
      Character[] rowSeats = rowInfo.getRowSeats();
      int occupiedSeats = rowInfo.getOccupiedSeats();
      System.out.println(
          "Ряд " + rowInfo.getRowNumber() + " Места: " + Arrays.toString(rowSeats) + " Свободно: "
              + occupiedSeats + " мест(а)");
    }
  }

  private static int countFreeSeats(Character[] row) {
    int count = 0;
    for (Character seat : row) {
      if (seat == 'X') {
        count++;
      }
    }
    return count;
  }

  static void returnTickets(Scanner scanner, CinemaManager cinemaManager, HallMap hallMap) {
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
    Character[] checkedFreeSeats = hallMap.getSessionPlacesByDateANdTime(selectedDateIndex,
        selectedTimeIndex, selectedRow);
    List<Integer> occupiedSeatIndices = new ArrayList<>();
    for (int i = 0; i < checkedFreeSeats.length; i++) {
      if (checkedFreeSeats[i] == 'X') {
        occupiedSeatIndices.add(i);
      }
    }
    int[] selectedSeats = selectReturnedSeats(scanner, occupiedSeatIndices);
    for (int seatIndex : selectedSeats) {
      checkedFreeSeats[seatIndex] = Character.forDigit(seatIndex + 1, 10);
    }
    hallMap.returnTickets(selectedDateIndex, selectedTimeIndex, selectedRow, checkedFreeSeats);
    cinemaManager.writeAll();
  }

  private static int[] selectReturnedSeats(Scanner scanner, List<Integer> occupiedSeatIndices) {
    System.out.println("Выберите места для возврата:");
    for (int i = 0; i < occupiedSeatIndices.size(); i++) {
      System.out.println((i + 1) + ". Место " + (occupiedSeatIndices.get(i) + 1));
    }

    return readReturningInput(scanner, "Введите номера мест для возврата (через пробел): ",
        occupiedSeatIndices.size());
  }

  private static int[] readReturningInput(Scanner scanner, String textForUser, int max) {
    System.out.println(textForUser);
    while (true) {
      String inputStr = scanner.nextLine();
      String[] seatNumbers = inputStr.split(" ");
      int[] selectedSeats = new int[seatNumbers.length];
      boolean isValid = true;
      for (int i = 0; i < seatNumbers.length; i++) {
        if (!seatNumbers[i].matches("\\d+")) {
          isValid = false;
          break;
        }
        selectedSeats[i] = Integer.parseInt(seatNumbers[i]) - 1; // Adjust to 0-based index
        if (selectedSeats[i] < 0 || selectedSeats[i] >= max) {
          isValid = false;
          break;
        }
      }
      if (isValid) {
        return selectedSeats;
      } else {
        System.out.println("\u001B[31mНеверно. Введите допустимые номера мест: \u001B[0m");
      }
    }
  }

  public static void printExit() {
    System.out.println(
        "\u001B[32m\t\t\t\tБЛАГОДАРИМ, ЧТО ВОСПОЛЬЗОВАЛИСЬ НАШИМ СЕРВИСОМ! \u001B[0m");
    System.out.println(
        "\u001B[32m\t\t\t\t\t\t\t\t\t\t\t\tДО НОВЫХ ВСТРЕЧ! \u001B[0m");
  }
}
