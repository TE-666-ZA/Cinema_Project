import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FileEditor {
    private enum EnumSeparators {
        START_DATE('('),
        STOP_DATE(')'),
        START_TIME('['),
        STOP_TIME(']'),
        START_KEY('<'),
        STOP_KEY('>'),
        START_VALUE('{'),
        STOP_VALUE('}'),
        START_BONUS('*'),
        STOP_BONUS('#'),
        COLUM_Symbol('%');

        private final char messageEnumSeparators;

        EnumSeparators(char messageEnumSeparators) {
            this.messageEnumSeparators = messageEnumSeparators;
        }

        public char getSeparator() {
            return messageEnumSeparators;
        }
    }
    private BufferedReader reader;
    private FileReader infoFull;
    private char beginIndex;
    private char endIndex;
    private int dateCount;
    private int timeCount;
    private int keyCount;
    private int valueCount;
    private int bonusCount;
    private DateTimeFormatter dateFormat;





    FileEditor() throws IOException {
        this.infoFull = new FileReader("res/InfoFull.txt");
        reader = new BufferedReader(infoFull);
        this.dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.dateCount = 1;
        this.timeCount = 1;
        this.keyCount = 1;
        this.valueCount = 1;
        this.bonusCount = 1;

    }

    private String read(int targetCount, BufferedReader reader) throws IOException, NullPointerException {
        int currentCount = 0;
        int currentindex;
        char currentSymbol;
        boolean insideSection = false;
        boolean appendSection = false;
        StringBuilder content = new StringBuilder();
        while ((currentindex = reader.read()) != -1) {
            currentSymbol = (char) currentindex;
            if (insideSection) {
                if (currentSymbol == this.endIndex) {
                    return content.toString();
                }
                if (appendSection && this.endIndex != EnumSeparators.STOP_VALUE.getSeparator()) {
                    content.append(currentSymbol);
                } else if (currentSymbol != ' ' ) {
                    content.append(currentSymbol);
                }
                if (currentSymbol == this.beginIndex) {
                    appendSection = true;
                }
            }
            if (!insideSection) {
                currentCount++;
                if (currentCount == targetCount) {
                    insideSection = true;
                }
            }
        }
        return content.toString();
    }


    public LocalDate reeadDate() throws IOException{
        this.beginIndex = EnumSeparators.START_DATE.getSeparator();
        this.endIndex = EnumSeparators.STOP_DATE.getSeparator();
        LocalDate date = LocalDate.parse(read(this.dateCount, reader), this.dateFormat);

        return date;
    }

    public int readKey() throws IOException, NumberFormatException {

        this.beginIndex = EnumSeparators.START_KEY.getSeparator();
        this.endIndex = EnumSeparators.STOP_KEY.getSeparator();
        int mapKey = Integer.parseInt(read(this.keyCount, reader));


        return mapKey;
    }

    public Character[] readMapValue() throws IOException {
        this.beginIndex = EnumSeparators.START_VALUE.getSeparator();
        this.endIndex = EnumSeparators.STOP_VALUE.getSeparator();
        char[] temp = read(valueCount, reader).toCharArray();
        Character[] mapValue = new Character[temp.length];
        for (int i = 0; i < temp.length; i++) {
            mapValue[i] = temp[i];
        }


        return mapValue;
    }

    public Map<Integer, Character[]> readMap(Map<Integer, Character[]> thisMap) throws IOException {
      thisMap.put(readKey(),readMapValue());

        return thisMap;
    }


}
