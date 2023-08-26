import java.util.*;

public class HallMap {

  private static List<Map<Integer, Character[]>> sessions;

  private FileEditor fileEditor; // для чтения и записи данных

  // тут создаём лист с местами, его можно забрать в FileEditor
  private CinemaManager cinemaManager;

  public HallMap() {
    sessions = new ArrayList<>(9);
    this.fileEditor = new FileEditor();
    cinemaManager = new CinemaManager();
    readAllSeats();
  }

  // вызываем метод для записи данных
  // делаем это в следующе методе:
  private void writeAll() {
    cinemaManager.writeAll();
    for (int i = 0; i < 9; i++) {
      fileEditor.writeMap(sessions.get(i));
    }
  }

  // при выходе из проги нужен метод, в который записывается вся инфа о местах
  // сначала заполняем мапу, потом всё остальное
  public void saveSeatsToFile() {
    writeAll();
  }

  /**
   * теперь вызываем метод для чтения каждой мапы делаем это в методе:
   *
   * @
   */
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

      System.out.print("Ряд: " + key + "  Места: ");
      for (Character value : values) {
        System.out.print(value + " ");
      }
      System.out.println();
    }
  }

  public void buyTickets(int date, int time, int row, int[] places) {
    int index = sessionsIndexGenerator(date, time);
    Character[] seats = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    for (int i : places) {
      seats[i - 1] = 'X';
    }
    String chequeDate = cinemaManager.getDates()[date - 1].toString();
    String chequeTime = cinemaManager.getTimes()[time - 1].toString();
    cinemaManager.writeCheque(places, row, chequeDate, chequeTime, "paypal");
    this.sessions.get(index).put(row, seats);
    writeAll();
  }

  public static int getSessionsLength() {
    return sessions.size();
  }
}