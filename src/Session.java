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
        SPLITTER(",");


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
        readDate(EnumInfoFullIndexes.DATE_INDEX.getMessageEnumInfoFullIndexes(), EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
        readTime(EnumInfoFullIndexes.TIME_INDEX.getMessageEnumInfoFullIndexes(),EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
        readTitle(EnumInfoFullIndexes.TITLE_INDEX.getMessageEnumInfoFullIndexes(),EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
        readBonus(EnumInfoFullIndexes.BONUS_INDEX.getMessageEnumInfoFullIndexes(),EnumInfoFullIndexes.SPLITTER.getMessageEnumInfoFullIndexes());
        fileEditor.close();
    }

    public void readDate(String prefix, String splitter) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String[] temp = fileEditor.readData(prefix).split(splitter);
        this.dates = new LocalDate[temp.length];
        for (int i = 0; i < temp.length; i++) {
            dates[i] = LocalDate.parse(temp[i], formatter);
        }
    }

    public void readTime(String prefix, String splitter) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String[] temp = fileEditor.readData(prefix).split(splitter);
        this.times = new LocalTime[temp.length];
        for(int i = 0; i < temp.length; i++){
            times[i] = LocalTime.parse(temp[i],formatter);
        }
    }

    public boolean isDateCorrect(LocalDate date) {
        for (LocalDate d : dates) {
            if (d.equals(date)) {
                return true;
            }
        }
        return false;
    }

    public void readTitle(String prefix, String splitter) throws IOException {
        this.title = fileEditor.readData(prefix).split(splitter);
    }
    
    public void readBonus(String prefix, String splitter) throws IOException {
        this.bonus = fileEditor.readData(prefix).split(splitter);
    }

    public LocalDate[] getDates() {
        return dates;
    }

    public LocalTime[] getTimes() {
        return times;
    }

    public String[] getTitle() {
        return title;
    }

    public String[] getBonus() {
        return bonus;
    }
}
