import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Manager {
  private HallMap hallMap;
  private FileEditor fileEditor;
  private Session session;
  // список для хранения информации о названиях:
  private List<String> title;


  public Manager() throws IOException {
    title = new ArrayList<>(9);
    hallMap = new HallMap();
    session = new Session();
    fileEditor = new FileEditor();
  }

  public String getFilmByTime(LocalTime time) {
    for (String sessionInfo : title) {
      String[] films = sessionInfo.split(" \\[|\\] , ");
      LocalTime sessionTime = LocalTime.parse(films[0]);
      // сравниваем фильмы по времени показа
      if (sessionTime.equals(time)) {
        // возвращаем инфу о фильме
        return films[1];
      }
    }
    return null;
  }
}
