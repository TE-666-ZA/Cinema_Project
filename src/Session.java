import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Session {

  private String prefix;
  private String splitter;

  static enum EnumInfoFullIndexes {

    DATE_INDEX("/"),
    TIME_INDEX("["),
    TITLE_INDEX("&"),
    BONUS_INDEX("*"),
    SPLITTER("/n");

    public final String messageEnumInfoFullIndexes;

    private EnumInfoFullIndexes(String messageEnumInfoFullIndexes) {
      this.messageEnumInfoFullIndexes = messageEnumInfoFullIndexes;
    }

    public String getMessageEnumInfoFullIndexes() {
      return this.messageEnumInfoFullIndexes;
    }
  }

  private FileEditor fileEditor;
  private Session hallMap;
  private DateTimeFormatter timeFormatter;
  private DateTimeFormatter dateFormatter;
  private LocalDate[] dates;
  private LocalTime[] times;
  private String[] title;
  private String[] bonus;
  private Map<LocalDate, Map<String, LocalTime>> schedule;

  public Session() throws IOException {
    this.fileEditor = new FileEditor();
    this.schedule = new HashMap<>();
    this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    this.dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    readDate(EnumInfoFullIndexes.DATE_INDEX.getMessageEnumInfoFullIndexes(),
        EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
    readTime(EnumInfoFullIndexes.TIME_INDEX.getMessageEnumInfoFullIndexes(),
        EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
    readTitle(EnumInfoFullIndexes.TITLE_INDEX.getMessageEnumInfoFullIndexes(),
        EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
    readBonus(EnumInfoFullIndexes.BONUS_INDEX.getMessageEnumInfoFullIndexes(),
        EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
//        writeDate(dates,EnumInfoFullIndexes.DATE_INDEX.getMessageEnumInfoFullIndexes());
//        writeTime(times,EnumInfoFullIndexes.TIME_INDEX.getMessageEnumInfoFullIndexes());
//        writeTitle(title ,EnumInfoFullIndexes.TITLE_INDEX.getMessageEnumInfoFullIndexes());
//        writeBonus(bonus,EnumInfoFullIndexes.BONUS_INDEX.getMessageEnumInfoFullIndexes());
  }

  public void readDate(String prefix, String splitter) throws IOException {
    String[] temp = fileEditor.readData(prefix, splitter).split(splitter);
    this.dates = new LocalDate[temp.length];
    for (int i = 0; i < temp.length; i++) {
      dates[i] = LocalDate.parse(temp[i], dateFormatter);
    }
  }

  public void writeDate(LocalDate[] dates, String prefix) throws IOException {
    String[] data = new String[dates.length];
    for (int i = 0; i < dates.length; i++) {
      data[i] = dates[i].format(dateFormatter);
    }
    fileEditor.write(data, prefix);
  }

  public void readTime(String prefix, String splitter) throws IOException {
    String[] temp = fileEditor.readData(prefix, splitter).split(splitter);
    this.times = new LocalTime[temp.length];
    for (int i = 0; i < temp.length; i++) {
      times[i] = LocalTime.parse(temp[i], timeFormatter);
    }
  }

  public void writeTime(LocalTime[] times, String prefix) throws IOException {
    String[] data = new String[times.length];
    for (int i = 0; i < times.length; i++) {
      data[i] = times[i].format(timeFormatter);
    }
    fileEditor.write(data, prefix);
  }

  public boolean isDateCorrect(LocalDate date) {
    for (LocalDate d : dates) {
      if (d.equals(date)) {
        return true;
      }
    }
    return false;
  }

  public boolean isTimeCorrect(LocalTime time) {
    for (LocalTime t : times) {
      if (t.equals(time)) {
        return true;
      }
    }
    return false;
  }

  public Map<Integer, Map<Integer, Character[]>> getHallMapsForDate(LocalDate date) {
    Map<Integer, Map<Integer, Character[]>> hallMaps = new LinkedHashMap<>();
    for (int i = 0; i < dates.length; i++) {
      if (dates[i].equals(date)) {
        hallMaps.put(i + 1, hallMap.getSessionMap(i + 1));
      }
    }
    return hallMaps;
  }

  public void showSchedule() {
    System.out.println("Расписание сеансов: ");
    for (int i = 0; i < dates.length; i++) {
      System.out.println("ДЕНЬ " + (i + 1));
      for (int j = 0; j < times.length; j++) {
        System.out.println(times[j].format(timeFormatter) + " " + title[j] + " (" + bonus[j] + ")");
      }
      System.out.println();
    }
  }

//  public static void printHallMapsPerDay(LocalDate date, Session session)
//      throws DataFormatException, IOException {
//    Map<Integer, Map<Integer, Character[]>> hallMapsForDate = session.getHallMapsForDate(date, session);
//
//    if (hallMapsForDate.isEmpty()) {
//      System.out.println("На выбранную дату нет сеансов.");
//      return;
//    }
//  }

  public Map<Integer, Character[]> getSessionMap(int sessionKey) {
    return hallMap.getSessionMap(sessionKey);
  }


  public void readTitle(String prefix, String splitter) throws IOException {
    this.title = fileEditor.readData(prefix, splitter).split(splitter);
  }

  public void writeTitle(String[] data, String prefix) throws IOException {
    fileEditor.write(data, prefix);
  }

  public void readBonus(String prefix, String splitter) throws IOException {
    this.bonus = fileEditor.readData(prefix, splitter).split(splitter);
  }

  public void writeBonus(String[] data, String prefix) throws IOException {
    fileEditor.write(data, prefix);
  }

  public String getSessionKey() {
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();

    for (int i = 0; i < dates.length; i++) {
      if (currentDate.isEqual(dates[i]) && currentTime.isAfter(times[i])) {
        return Integer.toString(i + 1);
      }
    }
    // Если нет текущего сеанса, возвращаем сообщение.
    return "Такого сеанса нет";
  }

  public void saveScheduleToFile(String filePath) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
      for (Map.Entry<LocalDate, Map<String, LocalTime>> entry : schedule.entrySet()) {
        LocalDate date = entry.getKey();
        Map<String, LocalTime> movies = entry.getValue();

        for (Map.Entry<String, LocalTime> movieEntry : movies.entrySet()) {
          String movieTitle = movieEntry.getKey();
          LocalTime movieTime = movieEntry.getValue();

          writer.write(date.toString() + "," + movieTitle + "," + movieTime.toString());
          writer.newLine();
        }
      }
    }
  }

  public void loadScheduleFromFile(String filePath) throws IOException {
    FileEditor fileEditor = new FileEditor(); // Создаем экземпляр FileEditor

    String fileContent = fileEditor.read(prefix, splitter); // Считываем содержимое файла

    String[] lines = fileContent.split("\\r?\\n"); // Разбиваем содержимое на строки

    for (String line : lines) {
      String[] parts = line.split(",");
      if (parts.length == 3) {
        LocalDate date = LocalDate.parse(parts[0]);
        LocalTime movieTime = LocalTime.parse(parts[2]);
        String movieTitle = parts[1];

        addSession(date, movieTime, movieTitle); // Правильный порядок параметров
      }
    }
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

  public void selectMovie(LocalDate date, String selectedMovieTitle) {
    Map<String, LocalTime> movies = schedule.get(date);
    if (movies != null && movies.containsKey(selectedMovieTitle)) {
      LocalTime selectedTime = movies.get(selectedMovieTitle);
      System.out.println("Вы выбрали фильм '" + selectedMovieTitle + "' на " + selectedTime);
    } else {
      System.out.println("Выбранный фильм не доступен.");
    }
  }

  public void addSession(LocalDate date, LocalTime time, String title) {
    if (!schedule.containsKey(date)) {
      schedule.put(date, new HashMap<>());
    }
    schedule.get(date).put(title, time);
  }

  public Map<LocalDate, Map<String, LocalTime>> getSchedule() {
    return schedule;
  }

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