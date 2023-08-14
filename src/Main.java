//import CinemaMenu.EnumMainMenu;

import java.io.IOException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Main {

  //
  public static void main(String[] args) throws IOException, DataFormatException {
    Session session = new Session();
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
          CinemaMenu.printTimetables(); //метод вывода РАСПИСАНИЯ
          break;
        // 2
        case FREE_SPACE: // КАРТА СВОБОДНЫХ МЕСТ
          CinemaMenu.freeSpace(scanner, session);
          break;
        //3
        case BUYING_TICKETS: //  ПОКУПКА БИЛЕТОВ
          CinemaMenu.printSeparator(); // вывод разделительной линии
          System.out.println("\u001B[32m" + "\t\t\t\t3. ПОКУПКА БИЛЕТОВ" + "\u001B[0m");
          CinemaMenu.buyTickets(); //метод ПОКУПКИ БИЛЕТА С ВЫВОДОМ КАРТЫ С ПОДСВЧЕННЫМИ МЕСТАМИ
          //Вывод нового меню
          boolean runBuyingTicketsMenu = true;
          while (runBuyingTicketsMenu) {
            int commandBuyingTicketsMenu = CinemaMenu.readCommandBuyingTicketsMenu(scanner);
            CinemaMenu.EnumBuyingTicketsMenu selectedBuyingTicketsMenu = CinemaMenu.EnumBuyingTicketsMenu.values()[commandBuyingTicketsMenu];
            switch (selectedBuyingTicketsMenu) {
              case TO_CONFIRM: // 3.1 ПОДТВЕРЖДЕНИЕ ПОКУПКИ
                System.out.println(
                    "\u001B[32m" + "\t\t\t\t3. ПОКУПКА БИЛЕТОВ -> ПОДТВЕРЖДЕНИЕ ПОКУПКИ :"
                        + "\u001B[0m");
                CinemaMenu.confirmPurchase(); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
                //ввод фамилии
                runBuyingTicketsMenu = false;
                break;
              case CHANGE_SELECTION: // 3.2 ИЗМЕНИТЬ ВЫБОР
                System.out.println(
                    "\u001B[32m" + "\t\t\t\t3. ПОКУПКА БИЛЕТОВ -> ИЗМЕНИТЬ ВЫБОР :" + "\u001B[0m");
                CinemaMenu.inputDateTime(scanner); //метод ввода ДАТЫ и СЕАНСА
                // заново запрашиваем ряд/количество мест/места
                CinemaMenu.inputRowQuantityPlace();// метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
                CinemaMenu.confirmPurchase(); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
                break;
              case CANCELLATION: // 3.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
                runBuyingTicketsMenu = false;
                break;
            }
          }
          break;
        //4
        case TICKETS_EXCHANGE_OR_RETURN: // 4 ОБМЕН/ВОЗВРАТ БИЛЕТОВ
          CinemaMenu.printSeparator(); // вывод разделительной линии
          System.out.println("\u001B[32m" + "\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ:" + "\u001B[0m");
          CinemaMenu.inputLastName();  //
          // ввод фио
          // вывод из файла  ПЕТРОВ 2 билета  Завтра 12.00 Русалочка
          boolean runTicketsExchangeOrReturnMenu = true;
          while (runTicketsExchangeOrReturnMenu) {
            int commandTicketsExchangeOrReturnMenu = CinemaMenu.readCommandTicketsExchangeOrReturnMenu(
                scanner);
            CinemaMenu.EnumTicketsExchangeOrReturnMenu selectedTicketsExchangeOrReturnMenu = CinemaMenu.EnumTicketsExchangeOrReturnMenu.values()[commandTicketsExchangeOrReturnMenu];
            switch (selectedTicketsExchangeOrReturnMenu) {
              case TICKETS_EXCHANGE: // 4.1 ОБМЕН БИЛЕТОВ
                System.out.println(
                    "\u001B[32m" + "\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ -> ОБМЕН БИЛЕТОВ :"
                        + "\u001B[0m");
                CinemaMenu.printSeparator(); // вывод разделительной линии
                // вывод РАСПИСАНИЯ
                CinemaMenu.inputDateTime(scanner);  //метод ввода ДАТЫ и СЕАНСА
                // вывод карты мест для конкретного сеанса

                CinemaMenu.inputRowQuantityPlace(); // метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
                //////////////////////////////////////////////////////////////////////////////////
                System.out.println("Введите ряд - >");
                //ввод ряда
                // Вывод карты ряда на экран
                System.out.println("Введите количество мест - >");
                ////////////////////////////////////////////////////////////////////////////////
                System.out.println("Вы обменяли______билета на ______   ______");
                runTicketsExchangeOrReturnMenu = false;
                break;
              case TO_RETURN_TICKETS: //4.2 СДАТЬ БИЛЕТЫ
                System.out.println(
                    "\u001B[32m" + "\t\t\t\t4. ОБМЕН/ВОЗВРАТ БИЛЕТОВ -> СДАТЬ БИЛЕТЫ :"
                        + "\u001B[0m");
                CinemaMenu.toReturnTickets(); // метод СДАТЬ БИЛЕТЫ С ВЫВОДОМ ИНФОРМАЦИИ НА КАКОЙ СЕАНС И ФИО
                runTicketsExchangeOrReturnMenu = false;
                break;
              case CANCELLATION_RETURN_TICKETS: // 4.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
                runTicketsExchangeOrReturnMenu = false;
                break;
            }
          }
          break;
        // 5
        case ADMINISTRATOR: // МЕНЮ АДМИНИСТРАТОРА
          CinemaMenu.printSeparator(); // вывод разделительной линии
          System.out.println("\u001B[32m" + "\t\t\t\t5. АДМИНИСТРАТОР:" + "\u001B[0m");
          boolean password = CinemaMenu.inputPass(scanner); // Метод ввода пароля Администратора
          if (!password) {
            break;
          }  // если пароль не совпадает, возвразаемся в главное меню
          boolean runAdministratorMenu = true;
          while (runAdministratorMenu) {
            int commandAdministratorMenu = CinemaMenu.readCommandAdministratorMenu(scanner);
            CinemaMenu.EnumAdministratorMenu selectedAdministratorMenu = CinemaMenu.EnumAdministratorMenu.values()[commandAdministratorMenu];
            switch (selectedAdministratorMenu) {
              case STATISTICS_PER_SESSION: // 5.1 СТАТИСТИКА ЗА СЕАНС
                System.out.println(
                    "\u001B[32m" + "\t\t\t\t4. АДМИНИСТРАТОР -> СТАТИСТИКА ЗА СЕАНС :"
                        + "\u001B[0m");
                CinemaMenu.printStatisticsForSession(); //метод вывода СТАТИСТИКИ ЗА СЕАНС
                break;
              case STATISTICS_FOR_DAY: // 5.2 СТАТИСТИКА ЗА ДЕНЬ
                System.out.println("\u001B[32m" + "\t\t\t\t4. АДМИНИСТРАТОР -> СТАТИСТИКА ЗА ДЕНЬ :"
                    + "\u001B[0m");
                CinemaMenu.printStatisticsForDay(); //метод вывода СТАТИСТИКИ ЗА ДЕНЬ
                break;
              case CHOICE_BONUS: // 5.3 ИЗМЕНИТЬ БОНУС
                System.out.println(
                    "\u001B[32m" + "\t\t\t\t4. АДМИНИСТРАТОР -> ИЗМЕНИТЬ БОНУС :" + "\u001B[0m");
                CinemaMenu.changeBonus(); //метод ИЗМЕНЕНИЯ БОНУСА
                break;
              case EXIT_MAIN_MENU: // Выход в предыдущее меню
                runAdministratorMenu = false;
                break;
            }
          }
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
