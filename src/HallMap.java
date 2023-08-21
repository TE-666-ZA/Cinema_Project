import java.io.IOException;
import java.util.*;

public class HallMap {

  private List<Map<Integer, Character[]>> sessions;

  private FileEditor fileEditor; // для чтения и записи данных

  // тут создаём лист с местами, его можно забрать в FileEditor
  public HallMap() throws IOException {
    sessions = new ArrayList<>(9);
    this.fileEditor = new FileEditor();
    readAllSeats();
  }

  // вызываем метод для записи данных
  // делаем это в следующе методе:
  private void writeAllSeats() throws IOException {
    for (int i = 0; i < 9; i++) {
      fileEditor.writeMap(sessions.get(i));
    }
  }

  // при выходе из проги нужен метод, в который записывается вся инфа о местах
  // сначала заполняем мапу, потом всё остальное
  public void saveSeatsToFile() throws IOException {
    writeAllSeats();
  }

  /**
   * теперь вызываем метод для чтения каждой мапы делаем это в методе:
   *
   * @throws IOException
   */
  private void readAllSeats() throws IOException {
    for (int i = 0; i < 9; i++) {
      Map<Integer, Character[]> sessionMap = new HashMap<>();
      for (int j = 1; j <= 5; j++) {
        Character[] rowArray = fileEditor.readMap(new HashMap<>()).values().iterator().next();
        sessionMap.put(j, rowArray);
      }
      sessions.add(sessionMap);
    }
  }

  // метод для того, чтобы купить/вернуть купленное место
  public void manageSeat(int sessionKey, int row, int seatNumber, boolean chooseSeat) {
    Map<Integer, Character[]> sessionMap = getSessionMap(sessionKey);
    if (sessionMap == null) {
      System.out.println("Такого сеанса нет");
      return;
    }
    Character[] rowArray = sessionMap.get(row);
    if (rowArray == null || seatNumber < 1 || seatNumber >= rowArray.length) {
      System.out.println("Что-то пошло не так при выборе ряда или места");
      return;
    }

    if (rowArray[seatNumber] == 'X' && chooseSeat) {
      System.out.println("Это место уже занято");
    } else if (rowArray[seatNumber] != 'X' && chooseSeat) {
      rowArray[seatNumber] = 'X';
      System.out.println("Вы выбрали место №" + seatNumber + ", ряд " + row);
    } else if (rowArray[seatNumber] == 'X' && !chooseSeat) {
      // тут преобразуем номер в символ и прибавляем его к 0, так будет корректно
      rowArray[seatNumber] = (char) (seatNumber + '0');
      System.out.println("Вы успешно вернули билет за место №" + seatNumber);
    } else {
      System.out.println("Место №" + seatNumber + " уже свободно");
    }
  }

  // метод для демонстрации карты мест
  protected static void showSessionMap(Map<Integer, Character[]> sessionMap) {
    int seatsInRow = 10; // номер ряда + 9 мест
    for (var rowNumber : sessionMap.keySet()) {
      Character[] rowArray = sessionMap.get(rowNumber);
      System.out.printf("Ряд %d: ", rowNumber);
      for (int seat = 0; seat < rowArray.length; ++seat) {
        System.out.print(rowArray[seat] + " ");
        if ((seat + 1) % seatsInRow == 0) {
          System.out.println();
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void showRowMap(int rowNumber, int sessionKey, HallMap hallMap) {
    Map<Integer, Character[]> sessionMap = hallMap.getSessionMap(sessionKey);

    if (sessionMap != null) {
      Character[] seats = sessionMap.get(rowNumber);
      if (seats != null) {
        System.out.print("Ряд " + rowNumber + ": ");
        for (Character seat : seats) {
          System.out.print(seat + " ");
        }
        System.out.println();
      } else {
        System.out.println("Ряд " + rowNumber + " не найден");
      }
    } else {
      System.out.println("Сеанс " + sessionKey + " не найден");
    }
  }

  protected Map<Integer, Character[]> getSessionMap(int sessionKey) {
    return sessions.get(sessionKey - 1);
  }

  public Set<Integer> getAllSessionKeys() {
    Set<Integer> sessionKeys = new HashSet<>();
    for (int i = 0; i < sessions.size(); i++) {
      sessionKeys.add(i + 1);
    }
    return sessionKeys;
  }

  private int sessionsIndexGenerator(int date, int time) {
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
    int index = sessionsIndexGenerator(date, time);

    for (Map.Entry<Integer, Character[]> entry : sessions.get(index).entrySet()) {
      Integer key = entry.getKey();
      Character[] values = entry.getValue();

      System.out.print("ряд: " + key + " Места: ");
      for (Character value : values) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }
}