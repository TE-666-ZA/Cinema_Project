import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Класс, предоставляющий интерфейс для управления меню кинотеатра
 */
public class CinemaMenu {
  // Поля для хранения экземпляров HallMap и CinemaManager
  HallMap hallMap = new HallMap();
  CinemaManager cinemaManager = new CinemaManager();
  // Константа для стоимости билета
  static final int TICKET_PRICE = 2;

  /**
   * Конструктор класса CinemaMenu
   *
   * @param hallMap объект HallMap для работы с данными о залах
   * @param cinemaManager объект CinemaManager для управления сеансами
   */
  public CinemaMenu(HallMap hallMap, CinemaManager cinemaManager) {
    this.hallMap = hallMap;
    this.cinemaManager = cinemaManager;
  }

  /**
   * Enum для хранения констант с названиями пунктов меню
   * Содержит также конструктор, константу для хранения текста для вывода в консоль и геттер
   */
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

  /**
   * Метод для отображения разделительной линии в консоли
   */
  public static void printSeparator() {
    System.out.println("============================================================");
  }

  /**
   * Метод для считывания команд из меню
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @return индекс выбранного пункта меню
   */
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

  /**
   * Метод для осуществления покупки билетов на сеанс
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param cinemaManager объект CinemaManager для управления сеансами
   * @param hallMap объект HallMap для работы с данными о залах
   */
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
      System.out.println("\u001B[35mВозвращаемся в главное меню...\u001B[0m");
      return;
    }
    String selectedPaymentMethod = cinemaManager.getPaymentMethods()[paymentMethodChoice - 1];
    cinemaManager.writeCheque(selectedDateIndex, selectedTimeIndex, selectedRow, checkedFreeSeats,
        paymentMethodChoice, selectedPaymentMethod);
    hallMap.buyTickets(selectedDateIndex, selectedTimeIndex, selectedRow, checkedFreeSeats);
    cinemaManager.writeAll();
    int price = selectedSeats.length * TICKET_PRICE;
    System.out.println("\u001B[32mСтоимость одного билета: " + TICKET_PRICE + " евро\u001B[0m");
    System.out.println("\u001B[32mВаша покупка: " + price + " евро\u001B[0m");
    System.out.println("\u001B[32mНомер чека: " + cinemaManager.getChequeNumber() + "\u001B[0m");
  }

  /**
   * Метод для выбора даты сеанса
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param cinemaManager объект CinemaManager для управления сеансами
   * @return индекс выбранной даты
   */
  static int selectDate(Scanner scanner, CinemaManager cinemaManager) {
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

  /**
   * Метод для выбора времени сеанса
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param cinemaManager объект CinemaManager для управления сеансами
   * @return индекс выбранного времени
   */
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

  /**
   * Метод для выбора ряда мест в зале
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @return номер выбранного ряда
   */
  private static int selectRow(Scanner scanner) {
    return readBuyingInput(scanner, 0,
        "Пожалуйста, введите номер ряда (или введите 0 для выхода в главное меню): ", 1, 5);
  }

  /**
   * Метод для выбора количества билетов
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @return выбранное количество билетов
   */
  private static int selectNumberOfSeats(Scanner scanner) {
    return readBuyingInput(scanner, 0,
        "Пожалуйста, выберите количество билетов (или введите 0 для выхода в главное меню): ", 1,
        90);
  }

  /**
   * Метод для выбора мест на сеансе
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param numberOfSeats количество выбираемых мест
   * @return массив выбранных мест
   */
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

  /**
   * Метод для считывания и обработки ввода пользователя для выбора опций покупки билетов
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param cancelValue значение, которое указывает на отмену операции
   * @param textForUser текстовое сообщение для пользователя, указывающее на необходимость ввода
   * @param min минимальное допустимое значение для ввода
   * @param max максимальное допустимое значение для ввода
   * @return выбранное пользователем значение
   */
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

  /**
   * Метод для просмотра свободных и занятых рядов на определённом сеансе
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param cinemaManager объект CinemaManager для управления сеансами
   * @param hallMap объект HallMap для работы с данными о залах
   */
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

  /**
   * Метод для подсчёта свободных мест в выбранном ряду
   *
   * @param row массив мест в ряду
   * @return количество свободных мест в ряду
   */
  private static int countFreeSeats(Character[] row) {
    int count = 0;
    for (Character seat : row) {
      if (seat == 'X') {
        count++;
      }
    }
    return count;
  }

  /**
   * Метод для возврата билетов
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param cinemaManager объект CinemaManager для управления сеансами
   * @param hallMap объект HallMap для работы с данными о залах
   */
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
    List<Integer> selectedSeatIndices = selectReturnedSeats(scanner, occupiedSeatIndices);
    for (int seatIndex : selectedSeatIndices) {
      checkedFreeSeats[seatIndex] = Character.forDigit(seatIndex + 1, 10);
    }
    hallMap.returnTickets(selectedDateIndex, selectedTimeIndex, selectedRow, checkedFreeSeats);
    cinemaManager.writeAll();
  }

  /**
   * Метод для выбора мест, которые пользователь хочет вернуть
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param occupiedSeatIndices список индексов занятых мест
   * @return список индексов выбранных мест для возврата
   */
  private static List<Integer> selectReturnedSeats(Scanner scanner, List<Integer> occupiedSeatIndices) {
    System.out.println("Выберите места для возврата:");
    for (int i = 0; i < occupiedSeatIndices.size(); i++) {
      int seatIndex = occupiedSeatIndices.get(i);
      System.out.println((i + 1) + ". Место " + (seatIndex + 1));
    }
    int[] selectedSeatIndices = readReturningInput(scanner, "Введите номера мест для возврата:", occupiedSeatIndices.size());

    List<Integer> selectedSeats = new ArrayList<>();
    for (int seatIndex : selectedSeatIndices) {
      selectedSeats.add(occupiedSeatIndices.get(seatIndex));
    }
    return selectedSeats;
  }

  /**
   * Метод для считывания и обработки ввода пользователя при возврате билета(-ов)
   *
   * @param scanner объект Scanner для считывания ввода пользователя
   * @param textForUser текстовое сообщение для пользователя перед вводом
   * @param max максимальное значение для ввода
   * @return массив индексов выбранных мест для возврата
   */
  private static int[] readReturningInput(Scanner scanner, String textForUser, int max) {
    System.out.println(textForUser);
    while (true) {
      String inputStr = scanner.nextLine();
      if (inputStr.equals("0")) {
        return new int[0];
      }
      String[] seatNumbers = inputStr.split(" ");
      int[] selectedSeats = new int[seatNumbers.length];
      boolean isValid = true;
      for (int i = 0; i < seatNumbers.length; i++) {
        if (!seatNumbers[i].matches("\\d+")) {
          isValid = false;
          break;
        }
        selectedSeats[i] = Integer.parseInt(seatNumbers[i]) - 1;
        if (selectedSeats[i] < 0 || selectedSeats[i] >= max) {
          isValid = false;
          break;
        }
      }
      if (isValid) {
        return selectedSeats;
      } else {
        System.out.println("\u001B[31mНеверно. Введите допустимые номера мест (или 0 для выхода в предыдщее меню): \u001B[0m");
      }
    }
  }

  /**
   * Метод для вывода сообщения о завершении работы программы
   */
  public static void printExit() {
    System.out.println(
        "\u001B[32m\t\t\t\tБЛАГОДАРИМ, ЧТО ВОСПОЛЬЗОВАЛИСЬ НАШИМ СЕРВИСОМ! \u001B[0m");
    System.out.println(
        "\u001B[32m\t\t\t\t\t\t\t\t\t\t\t\tДО НОВЫХ ВСТРЕЧ! \u001B[0m");
  }
}