import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// класс, в котором будет храниться вся изначальная и обновлённая инфа о местах
public class HallMap {

  private Map<Integer, Character[]> session1;
  private Map<Integer, Character[]> session2;
  private Map<Integer, Character[]> session3;
  private Map<Integer, Character[]> session4;
  private Map<Integer, Character[]> session5;
  private Map<Integer, Character[]> session6;
  private Map<Integer, Character[]> session7;
  private Map<Integer, Character[]> session8;
  private Map<Integer, Character[]> session9;
  private FileEditor reader;
  private FileEditor writer; // предположим, через него класс файл эдитор будет записывать в мапы

  // тут создаём 9 мап, их можно забрать в FileEditor
  public HallMap() throws IOException {
    this.session1 = new HashMap<>();
    this.session2 = new HashMap<>();
    this.session3 = new HashMap<>();
    this.session4 = new HashMap<>();
    this.session5 = new HashMap<>();
    this.session6 = new HashMap<>();
    this.session7 = new HashMap<>();
    this.session8 = new HashMap<>();
    this.session9 = new HashMap<>();
    this.writer = new FileEditor();
    writeAllSeats();
    this.reader = new FileEditor();
    readAllSeats();
  }

  // вызываем метод для чтения каждой мапы
  // делаем это в методе:
  private void writeAllSeats() throws IOException {
    this.writer.readMap(session1);
    this.writer.readMap(session2);
    this.writer.readMap(session3);
    this.writer.readMap(session4);
    this.writer.readMap(session5);
    this.writer.readMap(session6);
    this.writer.readMap(session7);
    this.writer.readMap(session8);
    this.writer.readMap(session9);
  }

  // теперь вызываем метод для чтения каждой мапы
  // делаем это в методе:
  private void readAllSeats() throws IOException {
    this.reader.readMap(session1);
    this.reader.readMap(session2);
    this.reader.readMap(session3);
    this.reader.readMap(session4);
    this.reader.readMap(session5);
    this.reader.readMap(session6);
    this.reader.readMap(session7);
    this.reader.readMap(session8);
    this.reader.readMap(session9);
  }

  // метод для того, чтобы купить/вернуть купленное место
  public void manageSeat(int sessionKey, int row, int seatNumber, boolean choose) {
    Map<Integer, Character[]> sessionMap = getSessionMap(sessionKey);
    if (sessionMap == null) {
      System.out.println("Такого сеанса нет");
    }
    Character[] rowArray = sessionMap.get(row);
    if (rowArray == null || seatNumber < 1
        || seatNumber >= rowArray.length) {
      System.out.println("Что-то пошло не так при выборе ряда/места");
    }
    choose = true;
    boolean returnSeat = false;
    if (choose) {
      if (rowArray[seatNumber] == 'X') {
        System.out.println("Это место уже занято");
      } else {
        rowArray[seatNumber] = 'X';
        System.out.println("Вы выбрали место №" + seatNumber + ", ряд " + row);
      }
    } else {
      if (rowArray[seatNumber] == 'X') {
        rowArray[seatNumber] = (char) ('0'
            + seatNumber); // тут преобразуем номер в символ и прибавляем его к 0, так будет корректно
        System.out.println("Вы успешно вернули билет за место №" + seatNumber);
      } else {
        System.out.println("Место №" + seatNumber + " уже свободно");
      }
    }
  }

  // метод для демонстрации карты мест
  public void showSessionMap(String sessionName) {

  }

  private Map<Integer, Character[]> getSessionMap(int sessionKey) {
    switch (sessionKey) {
      case 1:
        return session1;
      case 2:
        return session2;
      case 3:
        return session3;
      case 4:
        return session4;
      case 5:
        return session5;
      case 6:
        return session6;
      case 7:
        return session7;
      case 8:
        return session8;
      case 9:
        return session9;
      default:
        return null;
    }
  }
}