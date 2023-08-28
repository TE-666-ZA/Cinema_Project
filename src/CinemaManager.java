import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CinemaManager {

  private final int PRICE = 20;

  private FileEditor fileEditor;
  private HallMap hallMap;
  private DateTimeFormatter timeFormatter;
  private DateTimeFormatter dateFormatter;
  private LocalDate[] dates;
  private LocalTime[] times;
  private String[] titles;
  private String[] bonus;
  private Map<LocalDate, Map<String, LocalTime>> schedule;
  String[] paymentMethods;
  private int chequeNumber;

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

  public void readDate() {
    String[] temp = fileEditor.readData(EnumFileTools.DATE_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
    this.dates = new LocalDate[temp.length];
    for (int i = 0; i < temp.length; i++) {
      dates[i] = LocalDate.parse(temp[i], dateFormatter);
    }
  }

  public void writeDate(LocalDate[] dates) {
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < dates.length; i++) {
      result.append(dates[i].format(dateFormatter)).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(result.toString(), EnumFileTools.DATE_INDEX.getTool());
  }

  public void readTime() {
    String[] temp = fileEditor.readData(EnumFileTools.TIME_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
    this.times = new LocalTime[temp.length];
    for (int i = 0; i < temp.length; i++) {
      times[i] = LocalTime.parse(temp[i], timeFormatter);
    }
  }

  public void writeTime(LocalTime[] times) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < times.length; i++) {
      result.append(times[i].format(timeFormatter)).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(result.toString(), EnumFileTools.TIME_INDEX.getTool());
  }

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

  public void readTitles() {
    this.titles = fileEditor.readData(EnumFileTools.TITLE_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  public void writeTitle(String[] data) {
    StringBuilder resul = new StringBuilder();
    for (String s : data) {
      resul.append(s).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(resul.toString(), EnumFileTools.TITLE_INDEX.getTool());
  }

  public void readBonus() {
    this.bonus = fileEditor.readData(EnumFileTools.BONUS_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  public void writeBonus(String[] data) { // этот метод записывает бонус в Fullinfo
    StringBuilder result = new StringBuilder();
    for (String s : data) {
      result.append(s).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(result.toString(), EnumFileTools.BONUS_INDEX.getTool());
  }

  public int getChequeNumber() { // этот метод вазвращает номер считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber) по переданному номеру чека
    return chequeNumber;
  }

  // этот метод записывает чек в файл check
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

  public void writeAll() {
    writeDate(dates);
    writeTime(times);
    writeTitle(titles);
    writeBonus(bonus);
    hallMap.writeAllMaps();
  }

  public Map<LocalDate, Map<String, LocalTime>> getSchedule() {
    return schedule;
  }

  public LocalDate[] getDates() {
    return dates;
  }

  public DateTimeFormatter getDateFormatter() {
    return dateFormatter;
  }

  public LocalTime[] getTimes() {
    return times;
  }

  public DateTimeFormatter getTimeFormatter() {
    return timeFormatter;
  }

  public String[] getPaymentMethods() {
    return paymentMethods;
  }
}
