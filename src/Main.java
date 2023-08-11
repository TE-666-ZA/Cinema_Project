//import CinemaMenu.EnumMainMenu;

import java.util.Scanner;

public class Main {
  //
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    boolean run = true;
    int command;

    CinemaMenu.FreeSpaceMenu();
    CinemaMenu.BuyingTicketsMenu();
    CinemaMenu.TicketsExchangeOrReturnMenu();
    CinemaMenu.AdministratorMenu();

    while (run) {
      // Выводим главного меню
      System.out.println("\u001B[32m" + "Добро пожаловать в Кинотеатр 'CINEMA'" + "\u001B[0m");
      command = CinemaMenu.readCommandMainMenu(scanner);
      CinemaMenu.EnumMainMenu selectedMenu = CinemaMenu.EnumMainMenu.values()[command];
      switch (selectedMenu) {
        case TIMETABLES:
          System.out.println("Расписание таратат");
          break;
        case FREE_SPACE:
          break;
        case BUYING_TICKETS:
          break;
        case TICKETS_EXCHANGE_OR_RETURN:
          break;
        case ADMINISTRATOR:
          break;
        case EXIT:
          run = false;
          break;
      }
    }
  }
}
