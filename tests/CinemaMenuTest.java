import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class CinemaMenuTest {

  /**
   * Проверка правильного ввода для метода readCommandMainMenu
   */
  @Test
  public void testReadCommandMainMenu_ValidInput() {
    String input = "2\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    int result = CinemaMenu.readCommandMainMenu(scanner);

    assertEquals(1, result);
  }

  /**
   * Проверка неправильного ввода для метода readCommandMainMenu
   */
  @Test
  public void testReadCommandMainMenu_InvalidInputThenValidInput() {
    String input = "invalid\n3\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    int result = CinemaMenu.readCommandMainMenu(scanner);

    assertEquals(2, result);
  }

  /**
   * Проверка ввода, выходящего за пределы допустимого диапазона, для метода readCommandMainMenu
   */
  @Test
  public void testReadCommandMainMenu_OutOfRangeInputThenValidInput() {
    String input = "0\n4\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    Scanner scanner = new Scanner(System.in);
    int result = CinemaMenu.readCommandMainMenu(scanner);

    assertEquals(3, result);
  }

  /**
   * Проверка метода buyTickets при отмене выбора даты
   */
  @Test
  public void testBuyTickets_CancelDateSelection() {
    String input = "0\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    CinemaManager cinemaManager = new CinemaManager();
    HallMap hallMap = new HallMap() {
      @Override
      public Character[] getSessionPlacesByDateANdTime(int dateIndex, int timeIndex, int row) {
        if (dateIndex == 1 && timeIndex == 1 && row == 1) {
          return new Character[]{'0', '0', '0', '0', '0'};
        }
        return super.getSessionPlacesByDateANdTime(dateIndex, timeIndex, row);
      }
    };
    Scanner scanner = new Scanner(System.in);
    CinemaMenu.buyTickets(scanner, cinemaManager, hallMap);

    Character[] selectedSeats = hallMap.getSessionPlacesByDateANdTime(1, 1, 1);
    for (int i = 0; i < selectedSeats.length; i++) {
      assertEquals('0', selectedSeats[i]);
    }
  }


  /**
   * Проверка метода buyTickets при отмене выбора времени
   */
  @Test
  public void testBuyTickets_CancelTimeSelection() {
    String input = "1\n0\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    CinemaManager cinemaManager = new CinemaManager();
    HallMap hallMap = new HallMap() {
      @Override
      public Character[] getSessionPlacesByDateANdTime(int dateIndex, int timeIndex, int row) {
        if (dateIndex == 1 && timeIndex == 1 && row == 1) {
          return new Character[]{'0', '0', '0', '0', '0'};
        }
        return super.getSessionPlacesByDateANdTime(dateIndex, timeIndex, row);
      }
    };
    Scanner scanner = new Scanner(System.in);
    CinemaMenu.buyTickets(scanner, cinemaManager, hallMap);

    Character[] selectedSeats = hallMap.getSessionPlacesByDateANdTime(1, 1, 1);
    for (int i = 0; i < selectedSeats.length; i++) {
      assertEquals('0', selectedSeats[i]);
    }
  }

  /**
   * Проверка метода buyTickets при отмене выбора ряда
   */
  @Test
  public void testBuyTickets_CancelRowSelection() {
    String input = "1\n1\n0\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    CinemaManager cinemaManager = new CinemaManager();
    HallMap hallMap = new HallMap() {
      @Override
      public Character[] getSessionPlacesByDateANdTime(int dateIndex, int timeIndex, int row) {
        if (dateIndex == 1 && timeIndex == 1 && row == 1) {
          return new Character[]{'0', '0', '0', '0', '0'};
        }
        return super.getSessionPlacesByDateANdTime(dateIndex, timeIndex, row);
      }
    };
    Scanner scanner = new Scanner(System.in);
    CinemaMenu.buyTickets(scanner, cinemaManager, hallMap);

    Character[] selectedSeats = hallMap.getSessionPlacesByDateANdTime(1, 1, 1);
    for (int i = 0; i < selectedSeats.length; i++) {
      assertEquals('0', selectedSeats[i]);
    }
  }

  /**
   * Проверка метода buyTickets при отмене выбора мест
   */
  @Test
  public void testBuyTickets_CancelSeatSelection() {
    String input = "1\n1\n1\n0\n";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    CinemaManager cinemaManager = new CinemaManager();
    HallMap hallMap = new HallMap() {
      @Override
      public Character[] getSessionPlacesByDateANdTime(int dateIndex, int timeIndex, int row) {
        if (dateIndex == 1 && timeIndex == 1 && row == 1) {
          return new Character[]{'0', '0', '0', '0', '0'};
        }
        return super.getSessionPlacesByDateANdTime(dateIndex, timeIndex, row);
      }
    };
    Scanner scanner = new Scanner(System.in);
    CinemaMenu.buyTickets(scanner, cinemaManager, hallMap);

    Character[] selectedSeats = hallMap.getSessionPlacesByDateANdTime(1, 1, 1);
    for (int i = 0; i < selectedSeats.length; i++) {
      assertEquals('0', selectedSeats[i]);
    }
  }

  /**
   * Проверка метода selectDate при попытке вернуться в главное меню
   */
  @Test
  public void testSelectDateExitToMainMenu() {
    String input = "0\n";
    Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
    CinemaManager cinemaManager = new CinemaManager();
    int selectedDateIndex = CinemaMenu.selectDate(scanner, cinemaManager);

    assertEquals(0, selectedDateIndex);
  }

  /**
   * Проверка метода selectDate при корректном вводе пользователя
   */
  @Test
  public void testSelectDateValidInput() {
    String input = "2\n";
    Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));
    CinemaManager cinemaManager = new CinemaManager();
    int selectedDateIndex = CinemaMenu.selectDate(scanner, cinemaManager);

    assertEquals(2, selectedDateIndex);
  }
}
