import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Session {

  private final int PRICE = 20;
  private final int CHEQUE_NUMBER = 0;
  private final int CHEQUE_DATE = 1;
  private final int CHEQUE_TIME = 2;
  private final int CHEQUE_TITLE = 3;
  private final int CHEQUE_RAW = 4;
  private final int CHEQUE_SEATS_NUMBERS = 5;
  private final int CHEQUE_SUM = 6;
  private final int CHEQUE_ISVALID = 7;
//TODO удалить
//  private String prefix;
//  private String splitter;

  private FileEditor fileEditor;
  //TODO удалить
  //private Session hallMap;
  private DateTimeFormatter timeFormatter;
  private DateTimeFormatter dateFormatter;
  private LocalDate[] dates;
  private LocalTime[] times;
  private String[] title;
  private String[] bonus;
  private String[] chequeData;
//TODO удалить
// private Map<LocalDate, Map<String, LocalTime>> schedule;

  public Session() throws IOException {
    this.fileEditor = new FileEditor();
    // this.schedule = new HashMap<>();
    this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
  }

  public void readDate() throws IOException { // этот метод считывает дату из файла Fullinfo
    String[] temp = fileEditor.readData(EnumFileTools.DATE_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
    this.dates = new LocalDate[temp.length];
    for (int i = 0; i < temp.length; i++) {
      dates[i] = LocalDate.parse(temp[i], dateFormatter);
    }
  }

  public void writeDate(LocalDate[] dates) throws IOException { // этот метод записывает дату в файла Fullinfo
    String[] data = new String[dates.length];
    for (int i = 0; i < dates.length; i++) {
      data[i] = dates[i].format(dateFormatter);
    }
    fileEditor.write(data, EnumFileTools.DATE_INDEX.getTool());
  }

  public void readTime() throws IOException { // этот метод считывает время из файла Fullinfo
    String[] temp = fileEditor.readData(EnumFileTools.TITLE_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.DATE_INDEX.getTool());
    this.times = new LocalTime[temp.length];
    for (int i = 0; i < temp.length; i++) {
      times[i] = LocalTime.parse(temp[i], timeFormatter);
    }
  }

  public void writeTime(LocalTime[] times)
      throws IOException { // этот метод записывает время в файла Fullinfo
    String[] data = new String[times.length];
    for (int i = 0; i < times.length; i++) {
      data[i] = times[i].format(timeFormatter);
    }
    fileEditor.write(data, EnumFileTools.TIME_INDEX.getTool());
  }
//TODO удалить
//  /**
//   * Метод проверки ВВеденной даты пользователем с датой в файле
//   *
//   * @param date
//   * @return
//   */
//  public boolean isDateCorrect(LocalDate date) {
//    for (LocalDate d : dates) {
//      if (d.equals(date)) {
//        return true;
//      }
//    }
//    return false;
//  }
//TODO удалить
//  /**
//   * Метод проверки введенного пользователем Времени с Временем в файле
//   *
//   * @param time -
//   * @return
//   */
//  public boolean isTimeCorrect(LocalTime time) {
//    for (LocalTime t : times) {
//      if (t.equals(time)) {
//        return true;
//      }
//    }
//    return false;
//  }
//TODO удалить
//  /**
//   * @param date
//   * @return
//   */
//  public Map<Integer, Map<Integer, Character[]>> getHallMapsForDate(LocalDate date) { // этот метод показывает карту зала по дате
//    Map<Integer, Map<Integer, Character[]>> hallMaps = new LinkedHashMap<>();
//    for (int i = 0; i < dates.length; i++) {
//      if (dates[i].equals(date)) {
//        hallMaps.put(i + 1, hallMap.getSessionMap(i + 1));
//      }
//    }
//    return hallMaps;
//  }
//TODO удалить
//  /**
//   *
//   */
//  public void showSchedule() { // этот метод показывает расписание сеансов
//    System.out.println("Расписание сеансов: ");
//    for (int i = 0; i < dates.length; i++) {
//      System.out.println("ДЕНЬ " + (i + 1));
//      for (int j = 0; j < times.length; j++) {
//        System.out.println(times[j].format(timeFormatter) + " " + title[j] + " (" + bonus[j] + ")");
//      }
//      System.out.println();
//    }
//  }
//TODO удалить
//  public Map<Integer, Character[]> getSessionMap(int sessionKey) { // этот метод геттер требует ключ
//    return hallMap.getSessionMap(sessionKey);
//  }


  public void readTitle()
      throws IOException { // этот метод считывает название фильма жанр и возрастное ограничение из файла Fullinfo
    this.title = fileEditor.readData(EnumFileTools.TITLE_INDEX.getTool(),
        EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  public void writeTitle(String data)
      throws IOException { // этот метод записывает название фильма  жанр и возрастное ограничение в файл Fullinfo
    String[] temp = new String[1];
    temp[0] = data;
    fileEditor.write(temp, EnumFileTools.TITLE_INDEX.getTool());
  }

  public void readBonus() throws IOException {  // этот метод считывает бонус из Fullinfo
    this.bonus = fileEditor.readData(EnumFileTools.BONUS_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.BONUS_INDEX.getTool());
  }

  public void writeBonus(String data) throws IOException { // этот метод записывает бонус в Fullinfo
    String[] temp = new String[1];
    temp[0] = data;
    fileEditor.write(temp, EnumFileTools.BONUS_INDEX.getTool());
  }

  public void readCheque(int chequeNumber) throws IOException { // этот метод считывает из файла CHECK всю информацию по номеру чека
    String data = fileEditor.readCheque(chequeNumber);
    this.chequeData = data.split(EnumFileTools.CHEQUE_SEPARATOR.getTool());
  }

  public int getChequeNumber() { // этот метод вазвращает номер считанного из файла CHECK с помощью метода public void readCheque(int chequeNumber) по переданному номеру чека
    int result = Integer.parseInt(chequeData[CHEQUE_NUMBER]);
    return result;
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

  public boolean IsChequeValid() { // этот метод вазвращает true если считаный из Check, чек методом public void readCheque(int chequeNumber) не был отменен и FALSE если отмена произогшла
    int temp = Integer.parseInt(chequeData[CHEQUE_ISVALID]);
    if (temp > 0) {
      return true;
    }
    return false;
  }

  // этот метод записывает чек в файл check
  public void writeCheque(int[] selectedSeats, int rowNumber, String selectedDate,
      String selectedTime) throws IOException {
    int sum = PRICE * rowNumber;
    int isValid = 1;
    String[] data = {fileEditor.getChequeNumberAmount() + selectedDate +
        EnumFileTools.CHEQUE_SEPARATOR.getTool() + selectedTime +
        EnumFileTools.CHEQUE_SEPARATOR.getTool() + rowNumber
        + EnumFileTools.CHEQUE_SEPARATOR.getTool() + Arrays.toString(selectedSeats) +
        sum + EnumFileTools.CHEQUE_SEPARATOR.getTool() + isValid};
    fileEditor.write(data, EnumFileTools.CHEQUE_INDEX.getTool());
  }
//TODO удалить
//  public String getSessionKey() { //возвращает ключ сессии
//    LocalDate currentDate = LocalDate.now();
//    LocalTime currentTime = LocalTime.now();
//    for (int i = 0; i < dates.length; i++) {
//      if (currentDate.isEqual(dates[i]) && currentTime.isAfter(times[i])) {
//        return Integer.toString(i + 1);
//      }
//    }
//    // Если нет текущего сеанса, возвращаем сообщение.
//    return "Такого сеанса нет";
//  }
//TODO удалить
//  public void saveScheduleToFile(String filePath) throws IOException {
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//      for (Map.Entry<LocalDate, Map<String, LocalTime>> entry : schedule.entrySet()) {
//        LocalDate date = entry.getKey();
//        Map<String, LocalTime> movies = entry.getValue();
//        for (Map.Entry<String, LocalTime> movieEntry : movies.entrySet()) {
//          String movieTitle = movieEntry.getKey();
//          LocalTime movieTime = movieEntry.getValue();//
//          writer.write(date.toString() + "," + movieTitle + "," + movieTime.toString());
//          writer.newLine();
//        }
//      }
//    }
//  }
//TODO удалить
//  public void loadScheduleFromFile(String filePath) throws IOException {
//    FileEditor fileEditor = new FileEditor(); // Создаем экземпляр FileEditor
//  String fileContent = fileEditor.read(prefix, splitter); // Считываем содержимое файла
//   String[] lines = fileContent.split("\\r?\\n"); // Разбиваем содержимое на строки
//   for (String line : lines) {
//    String[] parts = line.split(",");
//    if (parts.length == 3) {
//      LocalDate date = LocalDate.parse(parts[0]);
//      LocalTime movieTime = LocalTime.parse(parts[2]);
//     String movieTitle = parts[1];
//     addSession(date, movieTime, movieTitle); // Правильный порядок параметров
//      }
//    }
//   }

//TODO удалить
//  public Map<Integer, String> getMoviesForDate(LocalDate date) {
//    Map<Integer, String> movieMap = new HashMap<>();
//    int count = 1;//
//    if (schedule.containsKey(date)) {
//      for (String movieTitle : schedule.get(date).keySet()) {
//        movieMap.put(count, movieTitle);
//        count++;
//      }
//    }
//    return movieMap;
//  }
//TODO удалить
//  public void selectMovie(LocalDate date, String selectedMovieTitle) {
//    Map<String, LocalTime> movies = schedule.get(date);
//    if (movies != null && movies.containsKey(selectedMovieTitle)) {
//      LocalTime selectedTime = movies.get(selectedMovieTitle);
//      System.out.println("Вы выбрали фильм '" + selectedMovieTitle + "' на " + selectedTime);
//    } else {
//      System.out.println("Выбранный фильм не доступен.");
//    }
//  }
//TODO удалить
//  public void addSession(LocalDate date, LocalTime time, String title) {
//    if (!schedule.containsKey(date)) {
//      schedule.put(date, new HashMap<>());
//    }
//    schedule.get(date).put(title, time);
//  }
//TODO удалить
//  public Map<LocalDate, Map<String, LocalTime>> getSchedule() {
//    return schedule;
//  }

  public LocalDate[] getDates() {
    return dates;
  }

  public LocalTime[] getTimes() {
    return times;
  }

  public String[] getTitle() {
    return title;
  }

  public String[] getBonus() {
    return bonus;
  }
}