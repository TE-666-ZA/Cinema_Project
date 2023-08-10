import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class FileEditor {
// чтение/запись из файла
public enum ReaderSeparators {
    STARTDATE("("),
    ENDDATE(")"),
    STARTTIME("["),
    ENDTIME("]"),
    KEY("|"),
    VALUE("#"),
    BONUS("*"),
    NEXTCOLUM("-----------------------------------");


    ReaderSeparators(String s) {
    }
}
    private Scanner fileReader;

    private File InfoFull;

    ReaderSeparators[] separators = ReaderSeparators.values();


    FileEditor(){
        this.fileReader = new Scanner(System.in);
        this.InfoFull = new File("InfoFull.txt");
    }

    public static HashMap<String,Integer[]> readInfo(Scanner scanner) throws IOException {
        int checker = 0;
        String value;
        String key;
        String reader;
        LocalDate date;
        while (scanner.hasNext()) {
                reader = scanner.next();
                // nado privyazat datu i vremya !!

                    key = reader.substring(separators.);
                    System.out.println(reader + " [Debug]");
                   // value = reader.substring();
        }
        return null;
    }
}
