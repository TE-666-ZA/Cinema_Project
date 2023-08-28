import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CinemaManager {
  private final int PRICE = 20;
  private final int CHEQUE_DATE = 1;
  private final int CHEQUE_TIME = 2;
  private final int CHEQUE_TITLE = 3;
  private final int CHEQUE_RAW = 4;
  private final int CHEQUE_SEATS_NUMBERS = 5;
  private final int CHEQUE_SUM = 6;
  private final int PAYMENT_METHOD = 7;

  private FileEditor fileEditor;
  private HallMap hallMap;
  private DateTimeFormatter timeFormatter;
  private DateTimeFormatter dateFormatter;
  private LocalDate[] dates;
  private LocalTime[] times;
  private String[] titles;
  private String[] bonus;
  private Map<LocalDate, Map<String, LocalTime>> schedule; //TODO что тут хранится????
  private String[] chequeData;
  String[] paymentMethods;
  private int chequeNumber;

  public CinemaManager() {
    this.fileEditor = new FileEditor();
    this.schedule = new HashMap<>();
    hallMap = new HallMap();
    this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    this.paymentMethods = new String[]{"PayPal", "GiroPay", "MasterCard", "Visa", "Bitcoin", "Cash", "AmericanExpress", "Apple Pay", "Google Pay"};
    this.chequeNumber = 0;
    readDate();
    readTime();
    readTitles();
    readBonus();
  }

  public void readDate() {
    String[] temp = fileEditor.readData(EnumFileTools.DATE_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
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
    String[] temp = fileEditor.readData(EnumFileTools.TIME_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
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

  /**
   * Метод проверки ВВеденной даты пользователем с датой в файле
   *
   * @param date
   * @return
   */
  public boolean isDateCorrect(LocalDate date) {
    for (LocalDate d : dates) {
      if (d.equals(date)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Метод проверки введенного пользователем Времени с Временем в файле
   *
   * @param time -
   * @return
   */
  public boolean isTimeCorrect(LocalTime time) {
    for (LocalTime t : times) {
      if (t.equals(time)) {
        return true;
      }
    }
    return false;
  }


  public void showSchedule() {
    System.out.println("Расписание сеансов: ");
    for (int i = 0; i < dates.length; i++) {
      System.out.println("ДЕНЬ " + dates[i]);
      for (int j = 0; j < times.length; j++) {
        System.out.println(times[j].format(timeFormatter) + " " + titles[j] + " (" + bonus[j] + ")");
      }
      System.out.println();
    }
  }



  public void readTitles() {
    this.titles = fileEditor.readData(EnumFileTools.TITLE_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  public void writeTitle(String[] data) {
    StringBuilder resul = new StringBuilder();
    for (String s : data) {
      resul.append(s).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(resul.toString(), EnumFileTools.TITLE_INDEX.getTool());
  }

  public void readBonus() {
    this.bonus = fileEditor.readData(EnumFileTools.BONUS_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  public void writeBonus(String[] data) { // этот метод записывает бонус в Fullinfo
    StringBuilder result = new StringBuilder();
    for (String s : data) {
      result.append(s).append(EnumFileTools.SPLITTER.getTool());
    }
    fileEditor.writeData(result.toString(), EnumFileTools.BONUS_INDEX.getTool());
  }

  public void readCheque(int chequeNumber) { // этот метод считывает из файла CHECK всю информацию по номеру чека
    String data = fileEditor.readCheque(chequeNumber);
    this.chequeData = data.split(EnumFileTools.CHEQUE_SEPARATOR.getTool());
  }

  public int getChequeNumber() { // этот метод вазвращает номер считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber) по переданному номеру чека

    return chequeNumber;
  }

  public LocalDate getChequeDate() { // этот метод вазвращает дату считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber)по переданному номеру чека
    LocalDate result = (LocalDate) dateFormatter.parse(chequeData[CHEQUE_DATE]);
    return result;
  }

  public LocalTime getChequeTime() {// этот метод вазвращает время считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber)по переданному номеру чека
    LocalTime result = (LocalTime) timeFormatter.parse(chequeData[CHEQUE_TIME]);
    return result;
  }

  public String getChequeTitle() {// этот метод вазвращает название жанр и возрастной рейтинг считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber)по переданному номеру чека
    return chequeData[CHEQUE_TITLE];
  }

  public int getChequeRaw() { // этот метод вазвращает ряд считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber)по переданному номеру чека
    int result = Integer.parseInt(chequeData[CHEQUE_RAW]);
    return result;
  }

  public int[] getChequeSeatsNumbers() {// этот метод вазвращает места считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber)по переданному номеру чека
    int[] result = new int[]{Integer.parseInt(chequeData[CHEQUE_SEATS_NUMBERS])};
    return result;
  }

  public int getChequeSum() {// этот метод вазвращает сумму чека считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber)по переданному номеру чека
    int result = Integer.parseInt(chequeData[CHEQUE_SUM]);
    return result;
  }

  public String getPaymentMethodFromCheque() {
    return chequeData[PAYMENT_METHOD];
  }

  // этот метод записывает чек в файл check
  public void writeCheque(int dateIndex, int timeIndex, int row, Character[] seats, int paymentMethodIndex) {
    int sum = PRICE * row;
    paymentMethodIndex = paymentMethodIndex - 1;
    chequeNumber++;
    String[] data = {chequeNumber + dates[dateIndex - 1].toString() +
            EnumFileTools.CHEQUE_SEPARATOR.getTool() + times[timeIndex - 1].toString() +
            EnumFileTools.CHEQUE_SEPARATOR.getTool() + row
            + EnumFileTools.CHEQUE_SEPARATOR.getTool() + Arrays.toString(seats) +
            sum + EnumFileTools.CHEQUE_SEPARATOR.getTool() + paymentMethods[paymentMethodIndex]};
    fileEditor.writeData(Arrays.toString(data), EnumFileTools.CHEQUE_INDEX.getTool());
  }

  public Map<Integer, String> getMoviesForDate(LocalDate date) {
    Map<Integer, String> movieMap = new HashMap<>();
    int count = 1;

    if (schedule.containsKey(date)) {
      for (String movieTitle : schedule.get(date).keySet()) {
        movieMap.put(count, movieTitle);
        count++;
      }
    }
    return movieMap;
  }

  public void writeAll() {
    writeDate(dates);
    writeTime(times);
    writeTitle(titles);
    writeBonus(bonus);
    hallMap.writeAllMaps();
  }

  public void selectMovie(LocalDate date, String selectedMovieTitle) {
    Map<String, LocalTime> movies = schedule.get(date);
    if (movies != null && movies.containsKey(selectedMovieTitle)) {
      LocalTime selectedTime = movies.get(selectedMovieTitle);
      System.out.println("Вы выбрали фильм '" + selectedMovieTitle + "' на " + selectedTime);
    } else {
      System.out.println("Выбранный фильм не доступен.");
    }
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

  public String[] getTitles() {
    return titles;
  }

  public String[] getBonus() {
    return bonus;
  }

  public String[] getPaymentMethods() {
    return paymentMethods;
  }

  public void setDate(LocalDate date, int index) {
    this.dates[index - 1] = date;
    writeAll();
  }

  public void setTime(LocalTime times, int index) {
    this.times[index - 1] = times;
    writeAll();
  }

  public void setTitle(String title, int index) {
    this.titles[index - 1] = title;
    writeAll();
  }

  public void setBonus(String bonus, int index) {
    this.bonus[index - 1] = bonus;
    writeAll();
  }
}
