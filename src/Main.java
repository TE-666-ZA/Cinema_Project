//import CinemaMenu.EnumMainMenu;

import java.util.Scanner;

public class Main {

  //
  public static void main(String[] args) {
    int numberCheck;
    CinemaManager cinemaManager = new CinemaManager();
    HallMap hallMap = new HallMap();
    Scanner scanner = new Scanner(System.in);

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
          CinemaMenu2.buyingTickets(scanner, cinemaManager, hallMap);
          CinemaMenu2.buyTickets(scanner, cinemaManager, hallMap);
          break;
        //3.  ОБМЕН/ВОЗВРАТ БИЛЕТОВ
        case TICKETS_EXCHANGE_OR_RETURN:
          CinemaMenu2.printSeparator();// 4
          System.out.println(menuText);
          CinemaMenu2.ticketsExchangeOrReturn(scanner, cinemaManager);
          break;
        //4.  МЕНЮ АДМИНИСТРАТОРА
        case ADMINISTRATOR:
          CinemaMenu2.adminMenu(scanner, cinemaManager);
          break;
        //5.  Выход
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