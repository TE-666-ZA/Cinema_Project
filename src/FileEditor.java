import java.io.*;
import java.util.Map;

public class FileEditor {

    private final int KEY = 0;
    private final int VALUE = 1;
    private final int REMOVE_FIRST_INDEX = 1;
    private final int INFO_FULL_LENGHT = 67;
    private BufferedReader reader;
    private FileReader in;
    private FileWriter out;
    private BufferedWriter writer;
    private String fileAllData;
    private String fileCheque;
    private int lines;
    private int chequeNumberAmount; // peremennaya chtobi znat skolko chekov u nas uje est vizivat cherez getChequeNumberCount


    public FileEditor() {
        this.fileAllData = "res/InfoFull.txt";
        this.fileCheque = "res/Check.txt";
        this.lines = 0;
    }

    protected String read(String prefix, String splitter) throws IOException, NullPointerException {
        boolean isCheque = true;
        StringBuilder result = new StringBuilder();
        String input;
        if (prefix != EnumFileTools.CHEQUE_INDEX.getTool()) {
            isCheque = false;
            this.in = new FileReader(fileAllData);
        } else {
            this.in = new FileReader(fileCheque);
        }
        this.reader = new BufferedReader(in);
        while ((input = reader.readLine()) != null) {
            lines++;
            if (!isCheque && input.trim().startsWith(prefix)) {
                result.append(input.substring(REMOVE_FIRST_INDEX)).append(splitter);
                reader.close();
                return result.toString();
            } else if (isCheque) {
                chequeNumberAmount++;
                result.append(input.substring(REMOVE_FIRST_INDEX));
                    reader.close();
                    return result.toString();
            }
        }
        reader.close();
        return result.toString();
    }

    public Map<Integer, Character[]> readMap(Map<Integer, Character[]> thisMap) throws IOException {
        String[] temp = read(EnumFileTools.MAP_PREFIX.getTool(), EnumFileTools.MAP_CHEQUE_SPLITTER.getTool()).split(EnumFileTools.MAP_KEY_VALUE_SPLITTER.getTool());
        if (temp.length < 2) {
            throw new IOException("Неверный формат даты");
        }
        int key = Integer.parseInt(temp[KEY]);
        char[] charArray = temp[VALUE].toCharArray();

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

    public String readCheque(int chequeNumber) throws IOException {
        String[] temp = read(EnumFileTools.CHEQUE_INDEX.getTool(), EnumFileTools.MAP_CHEQUE_SPLITTER.getTool()).split(EnumFileTools.CHEQUE_SEPARATOR.getTool());
        return temp[chequeNumber].toString();
    }

    public void write(String[] data, String prefix) throws IOException {
        boolean isCheque = false;
        if (prefix != EnumFileTools.CHEQUE_INDEX.getTool()) {
            isCheque = true;
            this.out = new FileWriter(fileAllData, true);
        } else {
            this.out = new FileWriter(fileCheque, true);
        }
        this.writer = new BufferedWriter(out);
        if (this.lines > INFO_FULL_LENGHT && !isCheque) {
            resetFile();
        }
        for (String output : data) {
            writer.write(prefix + output);
            writer.newLine();
        }
        writer.close();
    }


    private void resetFile() throws IOException {
        this.out = new FileWriter(fileAllData, false);
        this.writer = new BufferedWriter(out);
    }

    public void writeMap(Map<Integer, Character[]> map) throws IOException {
        this.out = new FileWriter(fileAllData);
        this.writer = new BufferedWriter(out);
        for (Map.Entry<Integer, Character[]> thisMap : map.entrySet()) {
            writer.write(EnumFileTools.MAP_PREFIX.getTool() + thisMap.getKey() + EnumFileTools.MAP_KEY_VALUE_SPLITTER.getTool());
            Character[] temp = thisMap.getValue();
            for (Character value : temp) {
                writer.write(value + EnumFileTools.MAP_CHEQUE_SPLITTER.getTool());
            }
            writer.newLine();
        }
        writer.close();
    }

    public int getChequeNumberAmount() {
        return chequeNumberAmount;
    }
}
