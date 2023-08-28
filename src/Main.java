import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    CinemaManager cinemaManager = new CinemaManager();
    HallMap hallMap = new HallMap();
    Scanner scanner = new Scanner(System.in);
    List<Integer> rowNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    System.out.println("\u001B[32m\t\t\t\tДобро пожаловать в Кинотеатр 'CINEMA'\u001B[0m");
    boolean run = true;
    int command;
    while (run) {
      // Вывод главного меню
      CinemaMenu.printSeparator();
      command = CinemaMenu.readCommandMainMenu(scanner);
      CinemaMenu.EnumMainMenu selectedMenu = CinemaMenu.EnumMainMenu.values()[command];
      String menuText = selectedMenu.getMenuText();
      switch (selectedMenu) {
        //1.  РАСПИСАНИЕ
        case TIMETABLES:
          CinemaMenu.printSeparator();
          cinemaManager.showSchedule();
          break;
        //2.  ПОКУПКА БИЛЕТОВ
        case BUYING_TICKETS:
          CinemaMenu.printSeparator();
          CinemaMenu.buyTickets(scanner, cinemaManager, hallMap);
          break;
        // 3. ПРОСМОТР СВОБОДНЫХ РЯДОВ
        case SEE_BUSY_ROWS:
          CinemaMenu.seeBusyRows(scanner, cinemaManager, hallMap);
          break;
        // 4. ВОЗВРАТ БИЛЕТОВ
        case RETURN_TICKETS:
          CinemaMenu.returnTickets(scanner, cinemaManager, hallMap);
          break;
        //5. ВЫХОД
        case EXIT:
          System.out.println(menuText);
          CinemaMenu.printExit();
          run = false;
          break;
        default:
          throw new IllegalStateException("Неверное значение: " + selectedMenu);
      }
    }
  }
}