import java.io.*;
import java.util.Map;

public class FileEditor {

    private final int KEY = 0;
    private final int VALUE = 1;
    private final String MAP_PREFIX = "|";
    private final String COMMENT_PREFIX = "#";
    private final int REMOVE_FIRST_INDEX = 1;


    private BufferedReader reader;
    private FileReader input;
    private BufferedWriter writer;
    private FileWriter output;


    FileEditor() throws IOException {
        this.input = new FileReader("res/InfoFull.txt");
        this.reader = new BufferedReader(input);
        this.output = new FileWriter("res/InfoFull.txt");
        this.writer = new BufferedWriter(output);

    }

    private String read(String prefix) throws IOException, NullPointerException {
        String input;
        while ((input = reader.readLine()) != null) {
            if (!input.trim().startsWith(COMMENT_PREFIX) && input.trim().startsWith(prefix)) {
                return input;
            }
        }
        return input;
    }

    public Map<Integer, Character[]> readMap(Map<Integer, Character[]> thisMap) throws IOException {
        String[] temp = read(MAP_PREFIX).split(">");
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
    public void write(String[] data, String prefix) throws IOException {
        for(String output : data) {
            if(!output.trim().startsWith(COMMENT_PREFIX) && output.trim().startsWith(prefix)){
                writer.write(output);
                writer.newLine();
            }
        }
    }
}
