import java.io.*;
import java.time.LocalDate;
import java.util.Map;

public class FileEditor {

    private final int KEY = 0;
    private final int VALUE = 1;
    private final String READ_MAP_PREFIX = "|";
    private final int REMOVE_FIRST_INDEX = 1;


    private BufferedReader reader;
    private FileReader infoFull;


    FileEditor() throws IOException {
        this.infoFull = new FileReader("res/InfoFull.txt");
        reader = new BufferedReader(infoFull);
    }

    private String read(String prefix) throws IOException, NullPointerException {
        String input;
        while ((input = reader.readLine()) != null) {
            if (!input.trim().startsWith("#") && input.trim().startsWith(prefix)) {
                return input;
            }
        }
        return input;
    }

    public Map<Integer, Character[]> readMap(Map<Integer, Character[]> thisMap) throws IOException {
        String[] temp = read(READ_MAP_PREFIX).split(">");
        temp[KEY] = temp[KEY].substring(REMOVE_FIRST_INDEX);
        int key = Integer.parseInt(temp[KEY]);
        char[] charArray = temp[VALUE].toCharArray();
        Character[] value = new Character[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            value[i] = charArray[i];
        }
        thisMap.put(key, value);

        return thisMap;
    }

    public String readData(String prefix) throws IOException {
        return read(prefix).substring(REMOVE_FIRST_INDEX);
    }

    public void close() throws IOException {
        reader.close();
    }
}
