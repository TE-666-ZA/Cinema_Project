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

  private String prefix;
  private String splitter;

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
  }

  public void readDate() throws IOException {
    String[] temp = fileEditor.readData(EnumFileTools.DATE_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
    this.dates = new LocalDate[temp.length];
    for (int i = 0; i < temp.length; i++) {
      dates[i] = LocalDate.parse(temp[i], dateFormatter);
    }
  }

  public void writeDate(LocalDate[] dates) throws IOException {
    String[] data = new String[dates.length];
    for (int i = 0; i < dates.length; i++) {
      data[i] = dates[i].format(dateFormatter);
    }
    fileEditor.write(data, EnumFileTools.DATE_INDEX.getTool());
  }

  public void readTime() throws IOException {
    String[] temp = fileEditor.readData(EnumFileTools.TITLE_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.DATE_INDEX.getTool());
    this.times = new LocalTime[temp.length];
    for (int i = 0; i < temp.length; i++) {
      times[i] = LocalTime.parse(temp[i], timeFormatter);
    }
  }

  public void writeTime(LocalTime[] times) throws IOException {
    String[] data = new String[times.length];
    for (int i = 0; i < times.length; i++) {
      data[i] = times[i].format(timeFormatter);
    }
    fileEditor.write(data, EnumFileTools.TIME_INDEX.getTool());
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

  public Map<Integer, Character[]> getSessionMap(int sessionKey) {
    return hallMap.getSessionMap(sessionKey);
  }


  public void readTitle() throws IOException {
    this.title = fileEditor.readData(EnumFileTools.TITLE_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.SPLITTER.getTool());
  }

  public void writeTitle(String data) throws IOException {
    String[] temp = new String[1];
    temp[0] = data;
    fileEditor.write(temp, EnumFileTools.TITLE_INDEX.getTool());
  }

  public void readBonus() throws IOException {
    this.bonus = fileEditor.readData(EnumFileTools.BONUS_INDEX.getTool(), EnumFileTools.SPLITTER.getTool()).split(EnumFileTools.BONUS_INDEX.getTool());
  }

  public void writeBonus(String data) throws IOException {
    String[] temp = new String[1];
    temp[0] = data;
    fileEditor.write(temp, EnumFileTools.BONUS_INDEX.getTool());
  }

  public void writeCheque(int[] selectedSeats, int rowNumber, String selectedDate, String selectedTime) throws IOException {
    int sum = PRICE * rowNumber;
    String[] data = {fileEditor.getChequeNumber() + EnumFileTools.DATE_INDEX.getTool() +
            selectedDate + EnumFileTools.TIME_INDEX.getTool() + selectedTime +
            rowNumber + EnumFileTools.MAP_KEY_VALUE_SPLITTER.getTool() + Arrays.toString(selectedSeats) + sum + EnumFileTools.Money_index};
    fileEditor.write(data, EnumFileTools.CHEQUE_INDEX.getTool());
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