//import CinemaMenu.EnumMainMenu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {

  //
  public static void main(String[] args) throws IOException, DataFormatException {
    Session session = new Session();
    HallMap hallMap = new HallMap();
    Scanner scanner = new Scanner(System.in);

    boolean run = true;
    int command;
    while (run) {
      // Вывод главного меню
      CinemaMenu.printSeparator(); // вывод разделительной линии
      System.out.println(
          "\u001B[32m\t\t\t\tДобро пожаловать в Кинотеатр 'CINEMA'\u001B[0m");
      command = CinemaMenu.readCommandMainMenu(scanner);
      CinemaMenu.EnumMainMenu selectedMenu = CinemaMenu.EnumMainMenu.values()[command];
      switch (selectedMenu) {
        //1.  РАСПИСАНИЕ
        case TIMETABLES:
          System.out.println("\u001B[32m\t\t\t\t1. РАСПИСАНИЕ:");
          session.showSchedule();
          break;
        //2.  КАРТА СВОБОДНЫХ МЕСТ
        case FREE_SPACE:
          CinemaMenu.freeSpace(scanner, session, hallMap);
          break;
        //2.  ПОКУПКА БИЛЕТОВ
        case BUYING_TICKETS:
          Map<Integer, Character[]> sessionMap = new HashMap<>();
          int selectedRow = 0;
          int[] selectedSeats = new int[0];
          boolean isMoreThanFourTickets = false;
          CinemaMenu.printMapWithYourLocation(sessionMap, selectedRow, selectedSeats,
              isMoreThanFourTickets);
          CinemaMenu.buyingTickets(scanner, session, hallMap);
          CinemaMenu.buyTickets(scanner, session);
          break;
        //4.  ОБМЕН/ВОЗВРАТ БИЛЕТОВ
        case TICKETS_EXCHANGE_OR_RETURN: // 4
          CinemaMenu.ticketsExchangeOrReturn(scanner, session);
          break;
        //5.  МЕНЮ АДМИНИСТРАТОРА
        case ADMINISTRATOR:
          CinemaMenu.printSeparator(); // вывод разделительной линии
          System.out.println("\u001B[32m\t\t\t\t5. АДМИНИСТРАТОР:");
          boolean password = CinemaMenu.inputPass(scanner); // Метод ввода пароля Администратора
          if (!password) {
            break;
          }  // если пароль не совпадает, возвразаемся в главное меню
          CinemaMenu.administrator(scanner, session);
          break;
        //0.  Выход
        case EXIT:
          CinemaMenu.printExit();
          run = false;
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + selectedMenu);
      }
    }
  }
}