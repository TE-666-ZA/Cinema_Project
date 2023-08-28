import java.util.*;

public class HallMap {

  private static List<Map<Integer, Character[]>> sessions;

  private FileEditor fileEditor; // для чтения и записи данных

  public HallMap() {
    sessions = new ArrayList<>(9);
    this.fileEditor = new FileEditor();
    readAllSeats();
  }

  // вызываем метод для записи данных
  // делаем это в следующе методе:
  public void writeAllMaps() {
    for (int i = 0; i < 9; i++) {
      fileEditor.writeMap(sessions.get(i));
    }
  }

  private void readAllSeats() {
    for (int i = 0; i < 9; i++) {
      Map<Integer, Character[]> sessionMap = new HashMap<>();
      for (int j = 1; j <= 5; j++) {
        Character[] rowArray = fileEditor.readMap(new HashMap<>()).values().iterator().next();
        sessionMap.put(j, rowArray);
      }
      sessions.add(sessionMap);
    }
  }

  public int sessionsIndexGeneratorFromDateAndTime(int date, int time) {
    time = time - 1;
    if (date < 2) {
      date = 0;
    } else if (date > 2) {
      date = 6;
    } else {
      date = 3;
    }
    return time + date;
  }

  public void showPlacesByDateTime(int date, int time) {
    int index = sessionsIndexGeneratorFromDateAndTime(date, time);

    for (Map.Entry<Integer, Character[]> entry : sessions.get(index).entrySet()) {
      Integer key = entry.getKey();
      Character[] values = entry.getValue();

      System.out.print("Ряд: " + key + "  Места: ");
      for (Character value : values) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }

  public void buyTickets(int date, int time, int row, Character[] seats) {
    int index = sessionsIndexGeneratorFromDateAndTime(date, time);
    this.sessions.get(index).put(row, seats);
  }

  public static int getSessionsLength() {
    return sessions.size();
  }

  public Character[] getSessionPlacesByDateANdTime(int selectedDateIndex, int selectedTimeIndex, int selectedRow) {
    int index = sessionsIndexGeneratorFromDateAndTime(selectedDateIndex, selectedTimeIndex);
    Map<Integer, Character[]> sessionMap = sessions.get(index);
    if (sessionMap.containsKey(selectedRow)) {
      return sessionMap.get(selectedRow);
    } else {
      System.out.println("Указанный ряд не найден.");
      return new Character[0];
    }
  }
}