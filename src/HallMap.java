import java.util.*;

/**
 * Класс для хранения и управления данными о местах в кинозалах.
 */
public class HallMap {

  // Список сеансов и информации о занятых/свободных местах
  private static List<Map<Integer, Character[]>> sessions;
  // Объект для работы с файловой системой и данными
  private FileEditor fileEditor;

  /**
   * Конструктор класса HallMap
   * Инициализирует список сеансов 'sessions' и объект FileEditor
   */
  public HallMap() {
    sessions = new ArrayList<>(9);
    this.fileEditor = new FileEditor();
    readAllSeats();
  }

  /**
   * Метод для записи данных о сеансах в файл
   */
  public void writeAllMaps() {
    for (int i = 0; i < 9; i++) {
      fileEditor.writeMap(sessions.get(i));
    }
  }

  /**
   * Метод для чтения информации о состоянии мест в кинозале из файла и инициализации
   * внутренней структуры данных о статусе мест (свободных/занятых).
   *
   * Метод считывает данные о местах для 9 сеансов, каждый из которых имеет 5 рядов.
   *
   * @note Этот метод использует объект `fileEditor` для чтения данных из файла.
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

  /**
   * Метод для генерации индекса сеанса на основе даты и времени
   *
   * @param date дата сеанса
   * @param time время сеанса
   * @return индекс сеанса в списке 'sessions'
   */
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

  /**
   * Метод для отображения состояния мест в кинозале на выбранную дату и время сеанса
   *
   * @param date индекс выбранной даты сеанса
   * @param time индекс выбранного времени сеанса
   */
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

  /**
   * Метод для покупки билетов на выбранные места в заданном ряду и сеансе
   *
   * @param date индекс выбранной даты сеанса.
   * @param time индекс выбранного времени сеанса.
   * @param row номер выбранного ряда в кинозале.
   * @param seats массив символов, представляющих состояние выбранных мест в ряду.
   *              Цифра 1-9 - свободное место, 'X' - занятое место.
   */
  public void buyTickets(int date, int time, int row, Character[] seats) {
    int index = sessionsIndexGeneratorFromDateAndTime(date, time);
    this.sessions.get(index).put(row, seats);
  }

  /**
   * Возвращает длину списка сеансов
   *
   * @return длина списка сеансов
   */
  public static int getSessionsLength() {
    return sessions.size();
  }

  /**
   * Метод, который получает информацию о местах в выбранном ряду и сеансе
   *
   * @param selectedDateIndex индекс выбранной даты
   * @param selectedTimeIndex индекс выбранного времени сеанса
   * @param selectedRow номер выбранного ряда
   * @return массив символов, представляющих состояние мест в ряду
   */
  public Character[] getSessionPlacesByDateANdTime(int selectedDateIndex, int selectedTimeIndex, int selectedRow) {
    int index = sessionsIndexGeneratorFromDateAndTime(selectedDateIndex, selectedTimeIndex);
    Map<Integer, Character[]> sessionMap = sessions.get(index);
    if (sessionMap.containsKey(selectedRow)) {
      return sessionMap.get(selectedRow);
    } else {
      System.out.println("Указанный ряд не найден");
      return new Character[0];
    }
  }

  /**
   * Метод для возврата билетов на выбранные места в ряду и сеансе
   *
   * @param selectedDateIndex индекс выбранной даты
   * @param selectedTimeIndex индекс выбранного времени сеанса
   * @param selectedRow номер выбранного ряда
   * @param seatNumbers массив индексов выбранных мест для возврата
   */
  public void returnTickets(int selectedDateIndex, int selectedTimeIndex, int selectedRow, Character[] seatNumbers) {
    int sessionIndex = sessionsIndexGeneratorFromDateAndTime(selectedDateIndex, selectedTimeIndex);
    Map<Integer, Character[]> sessionMap = sessions.get(sessionIndex);
    if (sessionMap != null && sessionMap.containsKey(selectedRow)) {
      Character[] originalSeats = sessionMap.get(selectedRow);
      for (int seatNumber : seatNumbers) {
        int seatIndex = seatNumber - 1;
        if (seatIndex >= 0 && seatIndex < originalSeats.length && originalSeats[seatIndex] != '0') {
          originalSeats[seatIndex] = '0';
        } else {
          System.out.println("\u001B[32mБилеты успешно возвращены!\u001B[0m");
          return;
        }
      }
    } else {
      System.out.println("Ошибка при возврате билетов: указанный сеанс или ряд не найдены");
    }
  }
}