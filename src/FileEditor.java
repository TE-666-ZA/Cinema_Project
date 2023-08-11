import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class FileEditor {
    private enum EnumSeparators {
        START_DATE('('),
        STOP_DATE(')'),
        START_TIME('['),
        STOP_TIME(']'),
        START_KEY('<'),
        STOP_KEY('>'),
        NEXT_VALUE('{'),
        STOP_VALUE('#'),
        START_BONUS('*'),
        NEXT_COLUM('-');

        private final char messageEnumSeparators;

        EnumSeparators(char messageEnumSeparators) {
            this.messageEnumSeparators = messageEnumSeparators;
        }

        public char getSeparator() {
            return messageEnumSeparators;
        }
    }
    // чтение/запись из файла

    private BufferedReader reader;
    private int currentindex;
    private char currentSymbo;
    private StringBuilder content;
    private char beginIndex;
    private char endIndex;
    private int dateCount;
    private int timeCount;
    private int keyCount;
    private int valueCount;
    private int bonusCount;
    private int columCount;
    private int targetCount;
    private int mapKey;
    private String[] mapValue;
    private boolean insideSection;
    private FileReader infoFull;
    private SimpleDateFormat dateFormat;
    private Date date;


    FileEditor() throws IOException {
        this.infoFull = new FileReader("res/InfoFull.txt");
        this.reader = new BufferedReader(infoFull);
        this.insideSection = false;
        this.dateFormat = new SimpleDateFormat("dd-MM-yy");
        this.content = new StringBuilder();
        this.dateCount = 0;
        this.timeCount = 0;
        this.keyCount = 0;
        this.valueCount = 0;
        this.bonusCount = 0;
        this.columCount = 0;
    }

    public void read(int currentCount) throws IOException, NullPointerException {
        while ((currentindex = reader.read()) != -1) {
            if (insideSection) {
                currentSymbo = (char) currentindex;
                content.append(currentSymbo);

                if (currentSymbo == endIndex) {
                    insideSection = false;
                    break;
                }
            }
            if (targetCount == currentCount) {
                currentSymbo = (char) currentindex;
                if (currentSymbo == beginIndex) {
                    currentCount++;
                    insideSection = true;
                }
            }
        }
    }

    public Date getDate() throws IOException, ParseException {
        this.beginIndex = EnumSeparators.START_DATE.getSeparator();
        this.endIndex = EnumSeparators.STOP_DATE.getSeparator();
        read(dateCount);
        this.date = dateFormat.parse(content.toString());
        return date;
    }
    public int readKey() throws IOException {
        this.beginIndex = EnumSeparators.START_KEY.getSeparator();
        this.endIndex = EnumSeparators.STOP_KEY.getSeparator();
        read(keyCount);
        mapKey = Integer.parseInt(content.toString());
        return mapKey;
    }

}
