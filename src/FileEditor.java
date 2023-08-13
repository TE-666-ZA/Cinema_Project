import java.io.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FileEditor {
    private final int KEY = 0;
    private final int VALUE = 1;

    private BufferedReader reader;
    private FileReader infoFull;





    FileEditor() throws IOException {
        this.infoFull = new FileReader("res/InfoFull.txt");
        reader = new BufferedReader(infoFull);
    }

    private String read() throws IOException, NullPointerException {
        String input;
      while((input = reader.readLine()) != null){
          if(!input.trim().startsWith("#")){
              return input;
          }
        }
        return input;
    }


    public LocalDate reeadDate() throws IOException{

        return null;
    }


    public Map<Integer, Character[]> readMap(Map<Integer, Character[]> thisMap) throws IOException {
    String[] temp = read().split(">");
    int key = Integer.parseInt(temp[KEY]);
    char[] charArray = temp[VALUE].toCharArray();
    Character[] value = new Character[charArray.length];
    for (int i = 0; i < charArray.length; i++){
        value[i] = charArray[i];
    }
    thisMap.put(key,value);

        return thisMap;
    }


}
