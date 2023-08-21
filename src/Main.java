//import CinemaMenu.EnumMainMenu;

import static java.awt.SystemColor.menuText;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {

  //
  public static void main(String[] args) throws IOException, DataFormatException {
    int numberCheck;
    Session session = new Session();
    HallMap hallMap = new HallMap();
    Scanner scanner = new Scanner(System.in);

    boolean run = true;
    int command;
    while (run) {
      // Вывод главного меню
      CinemaMenu2.printSeparator();
      System.out.println("\u001B[32m\t\t\t\tДобро пожаловать в Кинотеатр 'CINEMA'\u001B[0m");
      command = CinemaMenu2.readCommandMainMenu(scanner);
      CinemaMenu2.EnumMainMenu selectedMenu = CinemaMenu2.EnumMainMenu.values()[command];
      String menuText = selectedMenu.getMenuText();
      switch (selectedMenu) {
        //1.  РАСПИСАНИЕ
        case TIMETABLES:
          System.out.println("\u001B[32m\t\t\t\t1. " + menuText + ":\u001B[0m");
          session.showSchedule();
          break;
        //2.  КАРТА СВОБОДНЫХ МЕСТ
        case FREE_SPACE:
          System.out.println("\u001B[32m\t\t\t\t1. " + menuText + ":\u001B[0m");
          CinemaMenu2.freeSpace(scanner, session, hallMap);
          break;
        //2.  ПОКУПКА БИЛЕТОВ
        case BUYING_TICKETS:
          System.out.println("\u001B[32m\t\t\t\t1. " + menuText + ":\u001B[0m");
          Map<Integer, Character[]> sessionMap = new HashMap<>();
          int selectedRow = 0;
          int[] selectedSeats = new int[0];
          boolean isMoreThanFourTickets = false;
          CinemaMenu2.printMapWithYourLocation(sessionMap, selectedRow, selectedSeats,
              isMoreThanFourTickets);
          //TODO передать в метод номер чека
          CinemaMenu2.buyingTickets(scanner, session, hallMap);
          CinemaMenu2.buyTickets(scanner, session);
          break;
        //4.  ОБМЕН/ВОЗВРАТ БИЛЕТОВ
        case TICKETS_EXCHANGE_OR_RETURN: // 4
          System.out.println("\u001B[32m\t\t\t\t1. " + menuText + ":\u001B[0m");
          CinemaMenu2.ticketsExchangeOrReturn(scanner, session);
          break;
        //5.  МЕНЮ АДМИНИСТРАТОРА
        case ADMINISTRATOR:
          System.out.println("\u001B[32m\t\t\t\t1. " + menuText + ":\u001B[0m");
          CinemaMenu2.printSeparator(); // вывод разделительной линии
          System.out.println("\u001B[32m\t\t\t\t5. АДМИНИСТРАТОР:");
          boolean password = CinemaMenu2.inputPass(scanner); // Метод ввода пароля Администратора
          if (!password) {
            break;
          }  // если пароль не совпадает, возвразаемся в главное меню
          CinemaMenu2.administrator(scanner, session);
          break;
        //0.  Выход
        case EXIT:
          System.out.println("\u001B[32m\t\t\t\t1. " + menuText + ":\u001B[0m");
          CinemaMenu2.printExit();
          run = false;
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + selectedMenu);
      }
    }
  }
}