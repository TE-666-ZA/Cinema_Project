import java.util.Comparator;

public class ComparatorForSeats implements Comparator<Character[]> {

  @Override
  public int compare(Character[] o1, Character[] o2) {
    int comparison = Character.compare(o1[0], o2[0]);
    if (comparison != 0) {
      return comparison;
    } else {
      for (int i = 1; i <= 9; ++i) {
        int number1 = Integer.parseInt(o1[i].toString());
        int number2 = Integer.parseInt(o2[i].toString());
        if (number1 != number2) {
          return Integer.compare(number1, number2);
        }
      }
      return 0;
    }
  }
}
