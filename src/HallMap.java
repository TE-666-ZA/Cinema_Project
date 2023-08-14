import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  // делаем это в методе:
  private void writeAllSeats() throws IOException {
    for (int i = 0; i < 9; i++) {
      // TODO: раскомментить по готовности метода writeMap в FileEditor
      // sessions.add(i, fileEditor.writeMap(sessions.get(i)));
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
      sessions.add(i, fileEditor.readMap(new HashMap<>()));
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
    if (rowArray == null || seatNumber < 1
        || seatNumber >= rowArray.length) {
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
      rowArray[seatNumber] = Integer.toString(seatNumber).charAt(0);
      System.out.println("Вы успешно вернули билет за место №" + seatNumber);
    } else {
      System.out.println("Место №" + seatNumber + " уже свободно");
    }
  }

  // метод для демонстрации карты мест
  public void showSessionMap(int sessionKey) {
    Map<Integer, Character[]> sessionMap = getSessionMap(sessionKey);
    if (sessionMap == null) {
      System.out.println("Такого сеанса нет");
      return;
    }
    int seatsInRow = 11; // номер ряда + 10 мест
    for (var rowNumber : sessionMap.keySet()) {
      Character[] rowArray = sessionMap.get(rowNumber);
      System.out.printf("Ряд %d: ", rowNumber);
      for (int seat = 0; seat < rowArray.length; ++seat) {
        // тут, допустим, разделим номера мест в ряду пробелами
        System.out.println(rowArray[seat] + " ");
        // тут вторая проверка нужна для определения конца строки; условие будет верным только когда
        // последний номер места разделится на кол-во сидений в ряду без остатка
        if (seat > 0 && seat % seatsInRow == 0) {
          System.out.println();
        }
      }
      System.out.println();
    }
  }

  private Map<Integer, Character[]> getSessionMap(int sessionKey) {
    return sessions.get(sessionKey - 1);
  }
}