import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallMap {

  // создаём класс, в котором будет храниться вся изначальная и обновлённая инфа о местах
  private Map<String, List<List<String>>> hallSessions; // мапа для хранения информации о местах

  public HallMap() {
    hallSessions = new HashMap<>();
  }

  // метод для создания сеанса с местами и рядами
  public void createSession(String sessionKey, List<List<String>> rows) {
    hallSessions.put(sessionKey, rows);
  }

  // метод для того, чтобы выбрать купленное место
  public void chooseSeat(String sessionKey, int row, int seatNumber) {

  }

  // метод для того, чтобы вернуть купленное место
  public void returnSeat(String sessionKey, int row, int seatNumber) {

  }

  // метод для демонстрации карты мест
  public void showSessionMap(String sessionKey) {

  }
}