import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class CinemaMenu2 {

  public enum EnumMainMenu {
    TIMETABLES("Расписание"),
    FREE_SPACE("Карта свободных мест"),
    BUYING_TICKETS("Покупка билетов"),
    TICKETS_EXCHANGE_OR_RETURN("Обмен/Возврат билетов"),
    ADMINISTRATOR("Меню администратора"),
    EXIT("Выход");
    private final String menuText;

    EnumMainMenu(String menuText) {
      this.menuText = menuText;
    }

    public String getMenuText() {
      return menuText;
    }
  }

  public static void printSeparator() {
    System.out.println("=====================================");
  }

  public static int readCommandMainMenu(Scanner scanner) {
    System.out.println("Меню:");
    for (int i = 0; i < EnumMainMenu.values().length; i++) {
      System.out.println(i + 1 + ". " + EnumMainMenu.values()[i].getMenuText());
    }
    System.out.print("Выберите пункт меню: ");
    return scanner.nextInt() - 1;
  }

  public static void printMapWithYourLocation(Map<Integer, Character[]> sessionMap,
      int selectedRow,
      int[] selectedSeats,
      boolean isMoreThanFourTickets) {
    System.out.println("Карта мест:");
    for (Map.Entry<Integer, Character[]> entry : sessionMap.entrySet()) {
      int rowNumber = entry.getKey();
      Character[] rowSeats = entry.getValue();

      System.out.print("Ряд " + rowNumber + ": ");

      for (int i = 0; i < rowSeats.length; i++) {
        if (selectedRow == rowNumber && contains(selectedSeats, i + 1)) {
          System.out.print("\u001B[31m"); // Красный цвет для выбранных мест
        } else if (isMoreThanFourTickets && i < 4) {
          System.out.print("\u001B[33m"); // Желтый цвет для более четырех мест
        } else {
          System.out.print("\u001B[0m"); // Обычный цвет
        }

        System.out.print(rowSeats[i] + " ");
      }

      System.out.println("\u001B[0m"); // Восстановление обычного цвета
    }
  }

  private static boolean contains(int[] array, int value) {
    for (int num : array) {
      if (num == value) {
        return true;
      }
    }
    return false;
  }

  public static boolean inputPass(Scanner scanner) {
    // метод ввода пароля администратора
    return true;
  }

  public static void administrator(Scanner scanner, Session session) {
    // меню администратора
  }

  public static void buyingTickets(Scanner scanner, Session session, HallMap hallMap) {
    // функционал покупки билетов
  }

  public static void buyTickets(Scanner scanner, Session session) {
    // метод для проведения покупки билетов
  }

  public static void ticketsExchangeOrReturn(Scanner scanner, Session session) {
    // функционал обмена/возврата билетов
  }

  public static void freeSpace(Scanner scanner, Session session, HallMap hallMap) {
    // вывод свободных мест
  }

  public static void printExit() {
    System.out.println("Программа завершена.");
  }

  public static void printMapWithSelectedSeats(Map<Integer, Character[]> sessionMap,
      int selectedRow,
      int[] selectedSeats) {
    // метод для вывода карты с выбранными местами
  }
}
