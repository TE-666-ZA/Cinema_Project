import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HallMap {
  //Список( Мапа(ряд,номера сидений)
  private Map<LocalDate, Map> dateMap;
  private Map<LocalDate, Map> timeMap;
  private Map<Integer, Character[]> hallMap;
  // поле для чтения и записи данных
  private FileEditor fileEditor;
  private Session session;

  //
  //

  /**
   * конструктор HallMap()
   * создается массив из 9 элементов в которых лежат в которые должны попасть карты зала
   * и поле с экземпляром класса, который читает файл
   *
   * @throws IOException
   */
  public HallMap() throws IOException {
    this.dateMap = new HashMap<>();
    this.fileEditor = new FileEditor();
    this.session = new Session();
    readAllSeats();
  }

  // вызываем метод для записи данных
  // делаем это в следующе методе:
  private void writeAllSeats() throws IOException {
    for (int i = 0; i < 9; i++) {
      //  fileEditor.writeMap(exposition.get(i));
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
      this.timeMap = new HashMap<>();

      for (int j = 0; j < session.getDates().length; j++) {
        this.hallMap = new HashMap<>();
        timeMap.put(LocalDate.from(session.getTimes()[j]), fileEditor.readMap(hallMap));
        dateMap.put(session.getDates()[j],timeMap);
      }
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
    return exposition.get(sessionKey - 1);
  }

  public Set<Integer> getAllSessionKeys() {
    Set<Integer> sessionKeys = new HashSet<>();
    for (int i = 0; i < exposition.size(); i++) {
      sessionKeys.add(i + 1);
    }
    return sessionKeys;
  }
}