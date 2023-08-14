import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Session {
    FileEditor fileEditor;
    private LocalDate[] date;
    private LocalTime[] time;
    private String[] title;
    private String[] bonus;
    String content;

    Session() throws IOException {
        fileEditor = new FileEditor();
        generateAllSessionData();
    }

    public void generateAllSessionData() throws IOException {
       content  = fileEditor.readRest();
    }
}
