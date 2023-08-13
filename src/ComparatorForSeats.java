import java.util.Comparator;

public class ComparatorForSeats implements Comparator<Character[]> {

  @Override
  public int compare(Character[] seats1, Character[] seats2) {
    if (seats1.length != seats2.length) {
      System.out.println("Количество мест в ряду должно быть одинаковым");
    }
    for (int i = 0; i < seats1.length; ++i) {
      char seat1 = seats1[i];
      char seat2 = seats2[i];
      if (seat1 != seat2) {
        return seat1 - seat2; // вернём результат сравнения, если места не совпали
      }
    }
    return 0; // вернём, если места совпали
  }
}
