import java.util.Comparator;

public class ComparatorForFilms implements Comparator<String> {

  @Override
  public int compare(String name1, String name2) {
    return name1.compareToIgnoreCase(name2);
  }
}
