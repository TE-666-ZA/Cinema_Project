import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Session {

  private Session hallMap;

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
  private DateTimeFormatter timeFormatter;
  private DateTimeFormatter dateFormatter;
  private LocalDate[] dates;
  private LocalTime[] times;
  private String[] title;
  private String[] bonus;

  Session() throws IOException {
    this.fileEditor = new FileEditor();
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

  private Map<Integer, Character[]> getSessionMap(int sessionKey) {
    return hallMap.getSessionMap(sessionKey);
  }

  public Map<Integer, Map<Integer, Character[]>> getHallMapsForDate(LocalDate date) {
    Map<Integer, Map<Integer, Character[]>> hallMaps = new HashMap<>();
    for (int i = 0; i < dates.length; i++) {
      if (dates[i].equals(date)) {
        hallMaps.put(i + 1, getSessionMap(i + 1)); // Получаем карту мест для данного сеанса
      }
    }
    return hallMaps;
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