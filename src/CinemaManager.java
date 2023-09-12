import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс CinemaManager управляет всей функциональностью кинотеатра, включая чтение и запись данных,
 * управление расписанием сеансов, бронирование билетов и др.
 */
public class CinemaManager {

  // Фиксированная цена билета в кинотеатре
  private final int PRICE = 20;
  // Объект для работы с файлами, используемый для чтения и записи данных
  private static FileEditor fileEditor;
  // Объект для управления данными о залах и билетах
  private static HallMap hallMap;
  // Форматтер времени для преобразования времени в строку и обратно
  private static DateTimeFormatter timeFormatter;
  // Форматтер даты для преобразования даты в строку и обратно
  private static DateTimeFormatter dateFormatter;
  // Массив дат сеансов, используется для составления расписания
  private static LocalDate[] dates;
  // Массив времени сеансов, используется для составления расписания
  private static LocalTime[] times;
  // Массив названий фильмов, соответствующих сеансам в расписании
  private static String[] titles;
  // Массив бонусов
  private static String[] bonus;
  // Расписание сеансов, сопоставляет даты и времена с фильмами
  private Map<LocalDate, Map<String, LocalTime>> schedule;
  // Массив способов оплаты
  String[] paymentMethods;
  // Номер чека
  private int chequeNumber;

  /**
   * Конструктор класса CinemaManager
   * Инициализирует необходимые поля и считывает данные из файлов
   */
  public CinemaManager() {
    this.fileEditor = new FileEditor();
    this.schedule = new HashMap<>();
    hallMap = new HallMap();
    this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    this.paymentMethods = new String[]{"PayPal", "GiroPay", "MasterCard", "Visa", "Bitcoin", "Cash",
        "AmericanExpress", "Apple Pay", "Google Pay"};
    this.chequeNumber = 0;
    readDate();
    readTime();
    readTitles();
    readBonus();
  }

  /**
   * Метод считывает даты сеансов из файла.
   */
  public void readDate() {
    String[] temp = fileEditor.readData(EnumFileTools.DATE_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
    this.dates = new LocalDate[temp.length];
    for (int i = 0; i < temp.length; i++) {
      dates[i] = LocalDate.parse(temp[i], dateFormatter);
    }
  }

  /**
   * Метод записывает даты сеансов в файл.
   *
   * @param dates массив дат сеансов
   */
  public static void writeDate(LocalDate[] dates) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < dates.length; i++) {
      result.append(dates[i].format(dateFormatter)).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(result.toString(), EnumFileTools.DATE_INDEX.getTool());
  }

  /**
   * Метод считывает время сеансов из файла.
   */
  public void readTime() {
    String[] temp = fileEditor.readData(EnumFileTools.TIME_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
    this.times = new LocalTime[temp.length];
    for (int i = 0; i < temp.length; i++) {
      times[i] = LocalTime.parse(temp[i], timeFormatter);
    }
  }

  /**
   * Метод записывает время сеансов в файл.
   *
   * @param times массив времени сеансов
   */
  public static void writeTime(LocalTime[] times) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < times.length; i++) {
      result.append(times[i].format(timeFormatter)).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(result.toString(), EnumFileTools.TIME_INDEX.getTool());
  }

  /**
   * Метод отображает расписание сеансов.
   */
  public void showSchedule() {
    System.out.println("Расписание сеансов: ");
    for (int i = 0; i < dates.length; i++) {
      System.out.println("ДЕНЬ " + dates[i]);
      for (int j = 0; j < times.length; j++) {
        System.out.println(
            times[j].format(timeFormatter) + " " + titles[j] + " (" + bonus[j] + ")");
      }
      System.out.println();
    }
  }

  /**
   * Метод считывает названия фильмов из файла.
   */
  public void readTitles() {
    this.titles = fileEditor.readData(EnumFileTools.TITLE_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  /**
   * Метод записывает названия фильмов в файл.
   *
   * @param data массив дат сеансов
   */
  public static void writeTitle(String[] data) {
    StringBuilder resul = new StringBuilder();
    for (String s : data) {
      resul.append(s).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(resul.toString(), EnumFileTools.TITLE_INDEX.getTool());
  }

  /**
   * Метод считывает информацию о бонусах из файла.
   */
  public void readBonus() {
    this.bonus = fileEditor.readData(EnumFileTools.BONUS_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  /**
   * Метод записывает информацию о бонусах в файл.
   *
   * @param data массив дат сеансов
   */
  public static void writeBonus(String[] data) {
    StringBuilder result = new StringBuilder();
    for (String s : data) {
      result.append(s).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(result.toString(), EnumFileTools.BONUS_INDEX.getTool());
  }

  /**
   * Метод возвращает номер последнего чека
   *
   * @return номер последнего чека
   */
  public int getChequeNumber() {
    return chequeNumber;
  }

  /**
   * Записывает чек о покупке билетов в файл
   *
   * @param dateIndex индекс выбранной даты
   * @param timeIndex индекс выбранного времени
   * @param row ряд, в котором куплены билеты
   * @param seats массив мест, которые куплены
   * @param paymentMethodIndex индекс выбранного метода оплаты
   * @param selectedPaymentMethod выбранный метод оплаты
   */
  public void writeCheque(int dateIndex, int timeIndex, int row, Character[] seats,
      int paymentMethodIndex,
      String selectedPaymentMethod) {
    int sum = PRICE * row;
    chequeNumber++;
    String[] data = {chequeNumber + dates[dateIndex - 1].toString() +
        EnumFileTools.CHEQUE_SEPARATOR.getTool() + times[timeIndex - 1].toString() +
        EnumFileTools.CHEQUE_SEPARATOR.getTool() + row
        + EnumFileTools.CHEQUE_SEPARATOR.getTool() + Arrays.toString(seats) +
        sum + EnumFileTools.CHEQUE_SEPARATOR.getTool() + paymentMethods[paymentMethodIndex - 1]};
    fileEditor.writeData(Arrays.toString(data), EnumFileTools.CHEQUE_INDEX.getTool());
  }

  /**
   * Метод записывает все данные в соответствующие файлы
   */
  public static void writeAll() {
    writeDate(dates);
    writeTime(times);
    writeTitle(titles);
    writeBonus(bonus);
    hallMap.writeAllMaps();
  }

  /**
   * Геттер для расписания сеансов
   *
   * @return расписание сеансов
   */
  public Map<LocalDate, Map<String, LocalTime>> getSchedule() {
    return schedule;
  }

  /**
   * Геттер для массива дат сеансов
   *
   * @return массив дат сеансов
   */
  public LocalDate[] getDates() {
    return dates;
  }

  /**
   * Геттер для форматтера даты
   *
   * @return форматтер даты
   */
  public DateTimeFormatter getDateFormatter() {
    return dateFormatter;
  }

  /**
   * Возвращает массив времени сеансов
   *
   * @return массив времени сеансов
   */
  public LocalTime[] getTimes() {
    return times;
  }

  /**
   * Геттер для форматтера времени
   *
   * @return форматтер времени
   */
  public DateTimeFormatter getTimeFormatter() {
    return timeFormatter;
  }

  /**
   * Геттер для массива способов оплаты
   *
   * @return массив способов оплаты
   */
  public String[] getPaymentMethods() {
    return paymentMethods;
  }
}