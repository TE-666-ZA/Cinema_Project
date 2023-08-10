public class CinemaMenu {

  //меню и проверки меню тут
  public static void mainMenu() {

    System.out.println(Separators.TIMETABLES.ordinal() + "." + Separators.TIMETABLES.getMessage());
    System.out.println(Separators.FREE_SPACE.ordinal() + "." + Separators.FREE_SPACE.getMessage());
    System.out.println(
        Separators.BUYING_TICKETS.ordinal() + "." + Separators.BUYING_TICKETS.getMessage());
    System.out.println(Separators.TIKETS_EXCHANGE_OR_RETURN.ordinal() + "."
        + Separators.TIKETS_EXCHANGE_OR_RETURN.getMessage());
    System.out.println(
        Separators.ADMINISTRATOR.ordinal() + "." + Separators.ADMINISTRATOR.getMessage());
    System.out.println(Separators.EXIT.ordinal() + "." + Separators.EXIT.getMessage());
//    // separator();

  }
}
