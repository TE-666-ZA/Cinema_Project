import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CinemaMenuTest {//тесты

  @ParameterizedTest(name = "all ok for {0}")
  @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, -1})
  public void isCommandEnumMainMenuTest(int count) {
    boolean expected = true;
    boolean actual = CinemaMenu.isCommandEnumMainMenu(count);
    assertEquals(expected, actual);
  }
//  @Test
//  public void () {
//    boolean expected = true;
//    boolean actual = CinemaMenu.isCommandEnumMainMenu(0);
//    assertEquals(expected, actual);
//  }

}
