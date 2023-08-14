//import CinemaMenu.EnumMainMenu;

import java.io.IOException;
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
          "\u001B[32m" + "\t\t\t\tДобро пожаловать в Кинотеатр 'CINEMA'" + "\u001B[0m");
      command = CinemaMenu.readCommandMainMenu(scanner);
      CinemaMenu.EnumMainMenu selectedMenu = CinemaMenu.EnumMainMenu.values()[command];
      switch (selectedMenu) {
        //1
        case TIMETABLES:// РАСПИСАНИЕ

          System.out.println(
              "\u001B[32m" + "\t\t\t\t1. РАСПИСАНИЕ:" + "\u001B[0m");
          session.showSchedule();
          CinemaMenu.printHallMapsForAllDays(session);
          break;
        // 2

        case FREE_SPACE: // КАРТА СВОБОДНЫХ МЕСТ
          CinemaMenu.freeSpace(scanner, session, hallMap);
          break;
        //3
        case BUYING_TICKETS: //  ПОКУПКА БИЛЕТОВ
          CinemaMenu.buyingTickets(scanner, session);
          break;
        //4
        case TICKETS_EXCHANGE_OR_RETURN: // 4 ОБМЕН/ВОЗВРАТ БИЛЕТОВ
          CinemaMenu.ticketsExchangeOrReturn(scanner, session);
          break;
        // 5
        case ADMINISTRATOR: // МЕНЮ АДМИНИСТРАТОРА
          CinemaMenu.printSeparator(); // вывод разделительной линии
          System.out.println("\u001B[32m" + "\t\t\t\t5. АДМИНИСТРАТОР:" + "\u001B[0m");
          boolean password = CinemaMenu.inputPass(scanner); // Метод ввода пароля Администратора
          if (!password) {
            break;
          }  // если пароль не совпадает, возвразаемся в главное меню
          CinemaMenu.administartor(scanner, session);
          break;
        //0
        case EXIT:
          System.out.println(
              "\u001B[32m" + "\t\t\t\tБЛАГОДАРИМ, ЧТО ВОСПОЛЬЗОВАЛИСЬ НАШИМ СЕРВИСОМ! "
                  + "\u001B[0m");
          System.out.println(
              "\u001B[32m" + "\t\t\t\t\t\t\t\t\t\t\t\tДО НОВЫХ ВСТРЕЧ! " + "\u001B[0m");
          run = false;
          break;
      }
    }
  }
}
