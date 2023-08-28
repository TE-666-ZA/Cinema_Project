import java.util.Comparator;

public class RowComparator implements Comparator<Character[]> {

  @Override
  public int compare(Character[] row1, Character[] row2) {
    int freeSeats1 = countFreeSeats(row1);
    int freeSeats2 = countFreeSeats(row2);

    return Integer.compare(freeSeats1, freeSeats2);
  }

  int countFreeSeats(Character[] row) {
    int count = 0;
    for (Character seat : row) {
      if (seat == 'X') {
        count++;
      }
    }
    return count;
  }
}