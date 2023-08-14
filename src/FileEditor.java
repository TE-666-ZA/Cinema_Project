import java.io.*;
import java.util.Map;

public class FileEditor {

    private final int KEY = 0;
    private final int VALUE = 1;
    private final String MAP_PREFIX = "|";
    private final String MAP_SPLITTER = "";
    private final String MAP_KEY_VALUE_SPLITTER = ">";
    private final int REMOVE_FIRST_INDEX = 1;
    private final int INFO_FULL_LENGHT = 67;
    private BufferedReader reader;
    private FileReader in;
    private FileWriter out;
    private BufferedWriter writer;
    private String file;


    FileEditor() {
        this.file = "res/InfoFull.txt";
    }


    private String read(String prefix, String splitter) throws IOException, NullPointerException {
        this.in = new FileReader(file);
        this.reader = new BufferedReader(in);
        StringBuilder result = new StringBuilder();
        String input;
        while ((input = reader.readLine()) != null) {
            if (input.trim().startsWith(prefix)) {
                result.append(input.substring(REMOVE_FIRST_INDEX)).append(splitter);
            }
        }
        reader.close();
        return result.toString();
    }

    public Map<Integer, Character[]> readMap(Map<Integer, Character[]> thisMap) throws IOException {
        String[] temp = read(MAP_PREFIX, MAP_SPLITTER).split(MAP_KEY_VALUE_SPLITTER);
        if (temp.length < 2) {
            throw new IOException("Неверный формат даты");
        }
        int key = Integer.parseInt(temp[0]);
        char[] charArray = temp[1].toCharArray();

        Character[] value = new Character[9];
        for (int i = 0; i < 9; i++) {
            value[i] = charArray[i];
        }

        thisMap.put(key, value);
        return thisMap;
    }


    public String readData(String prefix, String splitter) throws IOException {
        return read(prefix, splitter);
    }

    private int getLineCount() throws IOException {
        this.in = new FileReader(file);
        this.reader = new BufferedReader(in);
        int lines = 0;
        while (reader.readLine() != null) {
            lines++;
        }
        reader.close();
        return lines;
    }

    public void write(String[] data, String prefix) throws IOException {
        this.out = new FileWriter(file, true);
        this.writer = new BufferedWriter(out);
        if (getLineCount() > INFO_FULL_LENGHT) {
            resetFile();
        }
        for (String output : data) {
            writer.write(prefix + output);
            writer.newLine();
        }
        writer.close();
    }

    private void resetFile() throws IOException {
        this.out = new FileWriter(file, false);
        this.writer = new BufferedWriter(out);
    }

    public void writeMap(Map<Integer, Character[]> map) throws IOException {
        this.out = new FileWriter(file);
        this.writer = new BufferedWriter(out);
        for (Map.Entry<Integer, Character[]> thisMap : map.entrySet()) {
            writer.write(MAP_PREFIX + thisMap.getKey() + MAP_KEY_VALUE_SPLITTER);
            Character[] temp = thisMap.getValue();
            for (Character value : temp) {
                writer.write(value + " ");
            }
            writer.newLine();
        }
        writer.close();
    }
}