import java.util.HashMap;
import java.util.Map;

public class HallMap {

  // создаём класс, в котором будет храниться вся изначальная и обновлённая инфа о местах
  private Map<Integer, Character[]> hallSessions;

  public HallMap() {
    this.hallSessions = new HashMap<>(); // добавляем новую мапу
    initializeSeatMaps();
  }

  // метод для создания сеанса с местами и рядами
  private void initializeSeatMaps() {
    Character[] session1 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session2 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session3 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session4 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session5 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session6 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session7 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session8 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    Character[] session9 = {'.', ' ', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    hallSessions.put(1, session1);
    hallSessions.put(2, session2);
    hallSessions.put(3, session3);
    hallSessions.put(4, session4);
    hallSessions.put(5, session5);
    hallSessions.put(6, session6);
    hallSessions.put(7, session7);
    hallSessions.put(8, session8);
    hallSessions.put(9, session9);
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