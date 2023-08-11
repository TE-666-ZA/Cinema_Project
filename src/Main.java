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
        case TIMETABLES:// Расписание
          System.out.println("Расписание таратат");
          // тут вставить вывод на экран
          break;
        // 2
        case FREE_SPACE: // Карта Свободных мест,
          boolean runFreeSpaseMenu = true;
          while (runFreeSpaseMenu) {
            System.out.println("Выберите дату - >");
            //ввод даты
            //сюда вставить функцию ввода даты
            int commandFreeSpaseMenu = CinemaMenu.readCommandMainMenu(scanner);
            CinemaMenu.EnumFreeSpaceMenu selectedFreeSpaseMenu = CinemaMenu.EnumFreeSpaceMenu.values()[commandFreeSpaseMenu];
            switch (selectedFreeSpaseMenu) {
              case CHANGE_DATE: // изменить дату
                System.out.println();
                //сюда вставить функцию ввода даты
                //ввод даты
                break;
              case RETURN_TO_THE_MAIN_MENU: // возврат в предыдущее меню
                runFreeSpaseMenu = false;
                break;
            }
          }
          break;
        //3

        case BUYING_TICKETS: //  Покупка билетов
          System.out.println("Выберите дату - >");
          // ввод
          System.out.println("Выберите выберите время сеанса - >");
          //ввод
          // вывод карты мест для конкретного сеанса
          System.out.println("Введите ряд - >");
          //ввод ряда
          // Вывод карты ряда на экран
          System.out.println("Введите количество мест - >");
          //ввод мест
          //Вывод карты хала с посвечеными местами выбранными
          System.out.println("Сумма покупки ...");
          //Вывод нового меню
          boolean runBuyingTicketsMenu = true;
          while (runBuyingTicketsMenu) {
            int commandBuyingTicketsMenu = CinemaMenu.readCommandMainMenu(scanner);
            CinemaMenu.EnumBuyingTicketsMenu selectedBuyingTicketsMenu = CinemaMenu.EnumBuyingTicketsMenu.values()[commandBuyingTicketsMenu];
            switch (selectedBuyingTicketsMenu) {
              case CHANGE_SELECTION: // изменить выбор мест
                // заново запросить ряд
                //количество мест
                //места
                break;
              case TO_CONFIRM:
                System.out.println("Подтвердите покупку вводом ФАМИЛИИ ->");
                //ввод фамилии
                break;
              case CANCELLATION: // отмена, выход в главное меню
                runBuyingTicketsMenu = false;
                break;
            }
          }
          break;
        //4
        case TICKETS_EXCHANGE_OR_RETURN: // 4 Обмен/Возврат билетов
          System.out.println("Ввелите фамилию покупателя ->");
          // ввод фио
          // вывод из файла  ПЕТРОВ 2 билета  Завтра 12.00 Русалочка
          boolean runTicketsExchangeOrReturnMenu = true;
          while (runTicketsExchangeOrReturnMenu) {
            int commandTicketsExchangeOrReturnMenu = CinemaMenu.readCommandMainMenu(scanner);
            CinemaMenu.EnumTicketsExchangeOrReturnMenu selectedTicketsExchangeOrReturnMenu = CinemaMenu.EnumTicketsExchangeOrReturnMenu.values()[commandTicketsExchangeOrReturnMenu];
            switch (selectedTicketsExchangeOrReturnMenu) {
              case TICKETS_EXCHANGE: // обмен билетов
                // вывод РАСПИСАНИЯ
                System.out.println("Выберите дату - >");
                // ввод
                System.out.println("Выберите выберите время сеанса - >");
                //ввод
                // вывод карты мест для конкретного сеанса
                System.out.println("Введите ряд - >");
                //ввод ряда
                // Вывод карты ряда на экран
                System.out.println("Введите количество мест - >");
                System.out.println("Вы обменяли______билета на ______   ______");
                runTicketsExchangeOrReturnMenu = false;
                break;
              case TO_RETURN_TICKETS: //сдать билеты
                System.out.println(" ФИО ___билетов на ____ _____");
                System.out.println(" Возврат _____ евро");
                System.out.println(" Спасибо, что воспоьзовались нашим сервисом!");
                runTicketsExchangeOrReturnMenu = false;
                break;
              case CANCELLATION_RETURN_TICKETS: // отмена действия и выход в главное меню
                runTicketsExchangeOrReturnMenu = false;
                break;
            }
          }
          break;
        // 5
        case ADMINISTRATOR: // Меню Администратора
          System.out.println(" Введите пароль -> ");
          //пользовательский ввод
          boolean runAdministratorMenu = true;
          while (runAdministratorMenu) {
            int commandAdministratorMenu = CinemaMenu.readCommandMainMenu(scanner);
            CinemaMenu.EnumAdministratorMenu selectedAdministratorMenu = CinemaMenu.EnumAdministratorMenu.values()[commandAdministratorMenu];
            switch (selectedAdministratorMenu) {
              case STATISTICS_PER_SESSOIN: // Статистика за СЕАНС
                break;
              case STATISTCS_FOR_DAY: // Статистика за День
                break;
              case CHOICE_BONUS: // Изменить бонус
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
