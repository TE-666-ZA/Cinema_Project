//import CinemaMenu.EnumMainMenu;

import java.util.Scanner;

public class Main {

  //
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean run = true;
    int command;
    while (run) {
      // Вывод главного меню
      System.out.println("\u001B[32m" + "Добро пожаловать в Кинотеатр 'CINEMA'" + "\u001B[0m");
      command = CinemaMenu.readCommandMainMenu(scanner);
      CinemaMenu.EnumMainMenu selectedMenu = CinemaMenu.EnumMainMenu.values()[command];
      switch (selectedMenu) {
        //1
        case TIMETABLES:// РАСПИСАНИЕ
          System.out.println("Сегодня  трататататтатататта");
          System.out.println("Завтра таратататататтататата");
          System.out.println("День вслед за затра трататата");
          // тут вставить вывод на экран
          break;
        // 2
        case FREE_SPACE: // КАРТА СВОБОДНЫХ МЕСТ
          boolean runFreeSpaseMenu = true;
          while (runFreeSpaseMenu) {
            CinemaMenu.inputDateTime(); //метод ввода ДАТЫ
            //вывод трех карт для выбранной даты
            CinemaMenu.printHallMapsForAllDays(); // метод ВЫВОДА 9 КАРТ ЗА ВСЕ ДНИ
            int commandFreeSpaseMenu = CinemaMenu.readCommandFreeSpaceMenu(scanner);
            CinemaMenu.EnumFreeSpaceMenu selectedFreeSpaseMenu = CinemaMenu.EnumFreeSpaceMenu.values()[commandFreeSpaseMenu];
            switch (selectedFreeSpaseMenu) {
              case CHANGE_DATE: // 2.1 ИЗМЕНИТЬ ДАТУ
                CinemaMenu.inputDateTime(); //метод ввода ДАТЫ и СЕАНСА
                CinemaMenu.printHallMapsPerDay(); // метод ВЫВОДА ТРЕХ КАРТ ЗА ДЕНЬ
                break;
              case RETURN_TO_THE_MAIN_MENU: // 2.2 ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
                runFreeSpaseMenu = false;
                break;
            }
          }
          break;
        //3
        case BUYING_TICKETS: //  ПОКУПКА БИЛЕТОВ
          CinemaMenu.inputDateTime(); //метод ввода ДАТЫ и СЕАНСА
          CinemaMenu.printHallMapPerSession();// метод вывода 1й КАРТЫ НА ВЫБРАННЫЙ СЕАНС
          CinemaMenu.inputRowQuantityPlace(); // метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ

          //Вывод карты зала с подсвечеными местами выбранными

          System.out.println("Сумма покупки ...");
          //Вывод нового меню
          boolean runBuyingTicketsMenu = true;
          while (runBuyingTicketsMenu) {
            int commandBuyingTicketsMenu = CinemaMenu.readCommandBuyingTicketsMenu(scanner);
            CinemaMenu.EnumBuyingTicketsMenu selectedBuyingTicketsMenu = CinemaMenu.EnumBuyingTicketsMenu.values()[commandBuyingTicketsMenu];
            switch (selectedBuyingTicketsMenu) {
              case CHANGE_SELECTION: // 3.1 ИЗМЕНИТЬ ВЫБОР
                CinemaMenu.inputDateTime(); //метод ввода ДАТЫ и СЕАНСА
                // заново запрашиваем ряд/количество мест/места
                CinemaMenu.inputRowQuantityPlace();// метод ввода РЯДА/КОЛЛИЧЕСТВА МЕСТ/МЕСТ
                CinemaMenu.confirmPurchase(); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
                break;
              case TO_CONFIRM: // 3.2 ПОДТВЕРЖДЕНИЕ ПОКУПКИ
                CinemaMenu.confirmPurchase(); //метод ПОДТВЕРЖДЕНИЯ ПОКУПКИ
                //ввод фамилии
                break;
              case CANCELLATION: // 3.3 ОТМЕНА, ВОЗВРАТ В ПРЕДЫДУЩЕЕ МЕНЮ
                runBuyingTicketsMenu = false;
                break;
            }
          }
          break;
        //4
        case TICKETS_EXCHANGE_OR_RETURN: // 4 ОБМЕН/ВОЗВРАТ БИЛЕТОВ
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
                // вывод РАСПИСАНИЯ
                CinemaMenu.inputDateTime();  //метод ввода ДАТЫ и СЕАНСА
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
          CinemaMenu.inputPass(); // Метод ввода пароля Администратора
          boolean runAdministratorMenu = true;
          while (runAdministratorMenu) {
            int commandAdministratorMenu = CinemaMenu.readCommandAdministratorMenu(scanner);
            CinemaMenu.EnumAdministratorMenu selectedAdministratorMenu = CinemaMenu.EnumAdministratorMenu.values()[commandAdministratorMenu];
            switch (selectedAdministratorMenu) {
              case STATISTICS_PER_SESSOIN: // 5.1 СТАТИСТИКА ЗА СЕАНС
                CinemaMenu.printStatisticsForSession(); //метод вывода СТАТИСТИКИ ЗА СЕАНС
                break;
              case STATISTCS_FOR_DAY: // 5.2 СТАТИСТИКА ЗА ДЕНЬ
                CinemaMenu.printStatisticsForDay(); //метод вывода СТАТИСТИКИ ЗА ДЕНЬ
                break;
              case CHOICE_BONUS: // 5.3 ИЗМЕНИТЬ БОНУС
                CinemaMenu.changeBonus(); //метод ИЗМЕНЕНИЯ БОНУСА
                break;
              case EXIT_MAIN_MENU: // Выход в предыдущее меню
                runAdministratorMenu = false;
                break;
            }
          }
          break;
        case EXIT:
          run = false;
          break;
      }
    }
  }
}
