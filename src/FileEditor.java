import java.io.*;
import java.util.Map;

public class FileEditor {

    private final int KEY = 0;
    private final int VALUE = 1;
    private final int REMOVE_FIRST_INDEX = 1;
    private final int INFO_FULL_LENGHT = 48;
    private BufferedReader reader;
    private FileReader in;
    private FileWriter out;
    private BufferedWriter writer;
    private String fileAllData;
    private String fileCheque;
    private int lines; // считает кол-во строк в FullInfo
    private int chequeNumberAmount; // считывает кол-во чеков в файле при первом чтении CHeck


    public FileEditor() {
        this.fileAllData = "res/InfoFull.txt";
        this.fileCheque = "res/Check.txt";
        this.lines = 0;
    }

    // В ЭТОМ КЛАССЕ НИ В КОЕМ СЛУЧАЕ НЕЧЕГО НИКОГДА НЕ ЗА ЧТО НЕ МЕНЯТЬ НЕЧЕГО ЛИШНЕГО СЮДА НЕ ПЕРЕДАВАТЬ ! ОСТАВИТЬ В ПОКОЕ И ЗАБЫТЬ ПОЛЬЗУЙТЕСЬ hallmap И Session

    private String read(String prefix, String splitter) throws IOException, NullPointerException { // эту функцию не трогать не для ваших грязныйх ручишек !!!
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
        in.close();
        return result.toString();
    }

    public Map<Integer, Character[]> readMap(Map<Integer, Character[]> thisMap) throws IOException { // эта функция читает и заполняет мапу предназначен для использования в классе HALLMAP
        String[] temp = read(EnumFileTools.MAP_PREFIX.getTool(),
            EnumFileTools.MAP_CHEQUE_SPLITTER.getTool()).split(EnumFileTools.
            MAP_KEY_VALUE_SPLITTER.getTool());
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


    public String readData(String prefix, String splitter) throws IOException { // этот метод считывает дату по переданным значениям предназначен для класса Session
        return read(prefix, splitter);
    }

    public String readCheque(int chequeNumber) throws IOException { // этот метод считывает чек из файла Check по заданному номеру чека предназначен для класса Session
        String[] temp = read(EnumFileTools.CHEQUE_INDEX.getTool(),
            EnumFileTools.MAP_CHEQUE_SPLITTER.getTool())
            .split(EnumFileTools.CHEQUE_SEPARATOR.getTool());
        return temp[chequeNumber];
    }

    public void writeData(String data, String prefix) throws IOException { // этот метод записывает файлы по заданным параметрам предназначен для класса Session
        boolean isCheque = true;
        if (prefix != EnumFileTools.CHEQUE_INDEX.getTool()) {
            isCheque = false;
            this.out = new FileWriter(fileAllData, true);
        } else {
            this.out = new FileWriter(fileCheque, true);
        }
        this.writer = new BufferedWriter(out);
        if (getLines() >= INFO_FULL_LENGHT && !isCheque) {
            resetFile();
        }
        writer.write(prefix + data);
            writer.newLine();

        writer.close();
        out.close();
    }

    private int getLines() throws IOException {
        this.in = new FileReader(fileAllData);
        BufferedReader reader = new BufferedReader(in);
        String input;
        this.lines = 0;
        while ((input = reader.readLine()) != null) {
            lines++;
        }
        reader.close();
        in.close();
        return lines;
    }


    private void resetFile() throws IOException { //этот метод только внутреклассовый он нужен для того чтобы перезаписывать значения в FullInfo а не создавать новые по тз
        writer.close();
        out.close();
        this.out = new FileWriter(fileAllData, false);
        this.writer = new BufferedWriter(out);
    }

    public void writeMap(Map<Integer, Character[]> map) throws IOException { // этот метод записывает карту в файл FullInfo предназначен для класса HallMap
        this.out = new FileWriter(fileAllData, true);
        this.writer = new BufferedWriter(out);
        for (Map.Entry<Integer, Character[]> thisMap : map.entrySet()) {
            writer.write(EnumFileTools.MAP_PREFIX.getTool() + thisMap.getKey()
                + EnumFileTools.MAP_KEY_VALUE_SPLITTER.getTool());
            Character[] temp = thisMap.getValue();
            for (Character value : temp) {
                writer.write(value + EnumFileTools.MAP_CHEQUE_SPLITTER.getTool());
            }
            writer.newLine();
        }
        writer.close();
        out.close();
    }

    /**
     * Возвращает метод общее Количество чеков в файле
     *
     * @return
     */
    public int getChequeNumberAmount() {
        return chequeNumberAmount;
    }
}
