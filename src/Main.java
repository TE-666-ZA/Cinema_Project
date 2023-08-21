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
          System.out.println(menuText);
          session.showSchedule();
          break;
        //2.  ПОКУПКА БИЛЕТОВ
        case BUYING_TICKETS:
          System.out.println(menuText);
          Map<Integer, Character[]> sessionMap = new HashMap<>();
          int selectedRow = 0;
          int[] selectedSeats = new int[0];
          boolean isMoreThanFourTickets = false;
          CinemaMenu2.printMapWithYourLocation(sessionMap, selectedRow, selectedSeats,
              isMoreThanFourTickets);
          //TODO передать в метод номер чека
          CinemaMenu2.buyingTickets(scanner, session, hallMap);
          CinemaMenu2.buyTickets(scanner, session, hallMap);
          break;
        //3.  ОБМЕН/ВОЗВРАТ БИЛЕТОВ
        case TICKETS_EXCHANGE_OR_RETURN: // 4
          System.out.println(menuText);
          CinemaMenu2.ticketsExchangeOrReturn(scanner, session);
          break;
        //4.  МЕНЮ АДМИНИСТРАТОРА
        case ADMINISTRATOR:
          System.out.println(menuText);
          CinemaMenu2.printSeparator(); // вывод разделительной линии
          boolean password = CinemaMenu2.inputPass(scanner); // Метод ввода пароля Администратора
          if (!password) {
            break;
          }  // если пароль не совпадает, возвразаемся в главное меню
          CinemaMenu2.administrator(scanner, session);
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