import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// класс, в котором будет храниться вся изначальная и обновлённая инфа о местах
public class HallMap{

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
    this.reader = new FileEditor();
    initializeAllSeatMaps();
  }

  // теперь вызываем метод для заполнения каждой мапы значениями, я его предварительно обозвала fillMap
  // делаем это в методе:
  private void initializeAllSeatMaps() throws IOException {
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

  // метод для того, чтобы выбрать купленное место
  public void chooseSeat(String sessionKey, int row, int seatNumber) {

  }

  // метод для того, чтобы вернуть купленное место
  public void returnSeat(String sessionKey, int row, int seatNumber) {

  }

  // метод для демонстрации карты мест
  public void showSessionMap(String sessionName) {
    Character[] sessionMap = hallSessions.get(sessionName);
    if (sessionMap == null) {
      System.out.println("Сеанс не найден");
      return;
    }

    int seatsPerRow = 12; // количество мест в ряду, втч нумерация ряда

    int index = 0;
    for (int seat = 0; seat < sessionMap.length; seat++) {
      System.out.print(sessionMap[seat] + " ");
      index++;

      // переносим строчку после вывода определенного количества мест
      if (index % seatsPerRow == 0) {
        System.out.println();
      }
    }
  }
}