import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws InstantiationException, IllegalAccessException {
    CinemaManager cinemaManager = new CinemaManager();
    HallMap hallMap = new HallMap();
    Scanner scanner = new Scanner(System.in);
    List<Integer> rowNumbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    System.out.println("\u001B[32m\t\t\t\tДобро пожаловать в Кинотеатр 'CINEMA'\u001B[0m");
    boolean run = true;
    int command;
    while (run) {
      // Вывод главного меню
      CinemaMenu2.printSeparator();
      command = CinemaMenu2.readCommandMainMenu(scanner);
      CinemaMenu2.EnumMainMenu selectedMenu = CinemaMenu2.EnumMainMenu.values()[command];
      String menuText = selectedMenu.getMenuText();
      switch (selectedMenu) {

        //1.  РАСПИСАНИЕ
        case TIMETABLES:
          CinemaMenu2.printSeparator();
          cinemaManager.showSchedule();
          break;
        //2.  ПОКУПКА БИЛЕТОВ
        case BUYING_TICKETS:
          CinemaMenu2.printSeparator();
          CinemaMenu2.buyTickets(scanner, cinemaManager, hallMap);
          break;
        // 3. ПРОСМОТР СВОБОДНЫХ РЯДОВ
        case SEE_BUSY_ROWS:
          CinemaMenu2.seeBusyRows(scanner, cinemaManager, hallMap);
          break;
        //3.  Выход
        case EXIT:
          System.out.println(menuText);
          CinemaMenu2.printExit();
          run = false;
          break;
        default:
          throw new IllegalStateException("Неверное значение: " + selectedMenu);
      }
    }
  }
}