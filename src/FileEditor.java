import java.io.*;
import java.util.Map;

public class FileEditor {

    private final int KEY = 0;
    private final int VALUE = 1;
    private final String MAP_PREFIX = "|";
    private final String MAP_SPLITTER = "";
    private final String MAP_KEY_VALUE_SPLITTER = ">";
    private final int REMOVE_FIRST_INDEX = 1;

     private int startLine;

    public FileEditor() {
        this.startLine = 0;
    }

    private String read(String prefix , String splitter) throws IOException, NullPointerException {
        FileReader in = new FileReader("res/InfoFull.txt");
        BufferedReader reader = new BufferedReader(in);
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
        String[] temp = read(MAP_PREFIX,MAP_SPLITTER).split(MAP_KEY_VALUE_SPLITTER);
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

    public String readData(String prefix,String splitter) throws IOException {
        return read(prefix,splitter);
    }

    public void write(String[] data, String prefix) throws IOException {
        FileWriter out= new FileWriter("res/InfoFull.txt");
         BufferedWriter writer = new BufferedWriter(out);
        FileReader in = new FileReader("res/InfoFull.txt");
        BufferedReader reader = new BufferedReader(in);
        String temp;
        int currentLine = 0;
           boolean writeSection = false;

           while ((temp = reader.readLine()) != null){
                   currentLine++;
                if(currentLine >= startLine ){
                    startLine = currentLine;
                    writeSection = true;
                }
                if(writeSection) {
                    for (String output : data) {
                        writer.write(prefix + output);
                        writer.newLine();
                    }
                }
           }
        writer.close();
    }

    public void writeMap(Map<Integer, Character[]> map) throws IOException {
        FileWriter output = new FileWriter("res/InfoFull.txt");
        BufferedWriter writer = new BufferedWriter(output);
        for (Map.Entry<Integer, Character[]> thisMap : map.entrySet()) {
            writer.write(MAP_PREFIX + thisMap.getKey() + MAP_KEY_VALUE_SPLITTER);
            Character[] temp = thisMap.getValue();
            for (Character value : temp) {
                writer.write(value);
            }
            writer.newLine();
        }
        writer.close();
    }
}


