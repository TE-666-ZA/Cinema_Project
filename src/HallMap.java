import java.util.HashMap;
import java.util.Map;

public class HallMap {

  // создаём класс, в котором будет храниться вся изначальная и обновлённая инфа о местах
  private Map<Integer, Character[]> hallSessions;

  public HallMap() {
    this.hallSessions = new HashMap<>(); // добавляем новую мапу
    initializeAllSeatMaps();
  }

  // метод для инициализации всех сеансов
  private void initializeAllSeatMaps() {
    putSeatMap(1, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(2, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(3, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(4, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(5, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(6, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(7, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(8, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
    putSeatMap(9, new Character[]{' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'});
  }

  // метод для инициализации сеанса с местами и рядами
  private void putSeatMap(int sessionKey, Character[] session) {
    hallSessions.put(sessionKey, session);
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