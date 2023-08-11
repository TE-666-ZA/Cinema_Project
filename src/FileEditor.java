import java.io.*;
import java.text.ParseException;
import java.util.Date;
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
    private char beginIndex;
    private char endIndex;
    private int lastIndex;
    private char indexManager;
    private int currentSymbol;
    private int targetline;
    private int currentLine;
    StringBuilder content;
    boolean insideSection;
    private FileReader infoFull;
    private SimpleDateFormat dateFormat;
    private Date date;


    FileEditor() throws IOException {
        this.endIndex = EnumSeparators.STOP_DATE.STOP_DATE.getSeparator();
        this.infoFull = new FileReader("res/InfoFull.txt");
        this.reader = new BufferedReader(infoFull);
        this.targetline = 0;
        this.insideSection = false;
        this.dateFormat = new SimpleDateFormat("dd-MM-yy");
        this.content = new StringBuilder();
    }

    public Date date() throws IOException, ParseException, NullPointerException {
        beginIndex = EnumSeparators.START_DATE.getSeparator();
        endIndex = EnumSeparators.STOP_DATE.getSeparator();

        while ((currentSymbol = reader.read()) != -1) {
            if(insideSection){
                indexManager = (char) currentSymbol;
                content.append(indexManager);
            }
            if(currentSymbol == endIndex){
               this.date = dateFormat.parse(content.toString());
               return getDate();
            }
                if(targetline == currentLine){
                    indexManager = (char)currentSymbol;
                    if(indexManager == beginIndex){
                        targetline++;
                        insideSection = true;
                    }
                }
        }
        return null;
    }

    public Date getDate() {
        return date;
    }
}
