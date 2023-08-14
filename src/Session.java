import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Session {
    static enum EnumInfoFullIndexes {

        DATE_INDEX("/"),
        TIME_INDEX("["),
        TITLE_INDEX("&"),
        BONUS_INDEX("*"),
        SEPARATOR(",");


        public final String messageEnumInfoFullIndexes;

        private EnumInfoFullIndexes(String messageEnumInfoFullIndexes) {
            this.messageEnumInfoFullIndexes = messageEnumInfoFullIndexes;
        }

        public String getMessageEnumInfoFullIndexes() {
            return this.messageEnumInfoFullIndexes;
        }
    }

    FileEditor fileEditor;
    private LocalDate[] dates;
    private LocalTime[] times;
    private String[] title;
    private String[] bonus;


    Session() throws IOException {
        fileEditor = new FileEditor();
        readDate(EnumInfoFullIndexes.DATE_INDEX.getMessageEnumInfoFullIndexes(), EnumInfoFullIndexes.SEPARATOR.getMessageEnumInfoFullIndexes());
        readTime(EnumInfoFullIndexes.TIME_INDEX.getMessageEnumInfoFullIndexes(),EnumInfoFullIndexes.SEPARATOR.getMessageEnumInfoFullIndexes());
    }

    public void readDate(String prefix, String separator) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String[] temp = fileEditor.readData(prefix).split(separator);
        this.dates = new LocalDate[temp.length];
        for (int i = 0; i < temp.length; i++) {
            dates[i] = LocalDate.parse(temp[i], formatter);
        }
    }

    public void readTime(String prefix, String separator) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String[] temp = fileEditor.readData(prefix).split(separator);
        this.times = new LocalTime[times.length];
        for(int i = 0; i < title.length; i++){
            times[i] = LocalTime.parse(temp[i],formatter);
        }
    }

}
