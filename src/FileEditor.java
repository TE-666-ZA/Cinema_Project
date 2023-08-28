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
  private int halMapReaderCounter;

  public FileEditor() {
    this.fileAllData = "res/InfoFull.txt";
    this.fileCheque = "res/Check.txt";
    this.lines = 0;
    this.halMapReaderCounter = 0;
  }

  private String read(String prefix,
      String splitter) { // эту функцию не трогать не для ваших грязныйх ручишек !!!
    boolean isCheque = true;
    StringBuilder result = new StringBuilder();
    int mapCounter = 0;
    String input;
    try {
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
          result.append(input.substring(REMOVE_FIRST_INDEX));
          reader.close();
          return result.toString();
        }
      }
      reader.close();
      in.close();
      return result.toString();
    } catch (IOException | NullPointerException e) {
      System.err.println("файл для чтения  не найден! обратитесь к сервисному руководству ");
    }
    return null;
  }

  public Map<Integer, Character[]> readMap(
      Map<Integer, Character[]> thisMap) { // эта функция читает и заполняет мапу предназначен для использования в классе HALLMAP
    try {
      if (halMapReaderCounter == 0) {
        this.in = new FileReader(fileAllData);
        this.reader = new BufferedReader(in);
      }
      String input;
      while ((input = reader.readLine()) != null) {
        if (input.startsWith(EnumFileTools.MAP_PREFIX.getTool())) {
          String[] temp = input.substring(REMOVE_FIRST_INDEX)
              .split(EnumFileTools.MAP_KEY_VALUE_SPLITTER.getTool());
          int key = Integer.parseInt(temp[KEY]);
          char[] charArray = temp[VALUE].toCharArray();
          Character[] value = new Character[temp[VALUE].length()];
          halMapReaderCounter++;
          for (int i = 0; i < 9; i++) {
            value[i] = charArray[i];
          }
          if (halMapReaderCounter == HallMap.getSessionsLength()) {
            reader.close();
            in.close();
          }
          thisMap.put(key, value);
          return thisMap;
        }
      }
    } catch (IOException | NullPointerException e) {
      System.err.println("Файл для чтения  не найден! Обратитесь к сервисному руководству ");
    }
    return null;
  }


  public String readData(String prefix,
      String splitter) { // этот метод считывает дату по переданным значениям предназначен для класса Session
    return read(prefix, splitter);
  }

  public void writeData(String data,
      String prefix) { // этот метод записывает файлы по заданным параметрам предназначен для класса Session
    boolean isCheque = true;
    try {
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
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private int getLines() {
    try {
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
    } catch (IOException e) {

    }
    return 0;
  }


  private void resetFile()
      throws IOException { //этот метод только внутреклассовый он нужен для того чтобы перезаписывать значения в FullInfo а не создавать новые по тз
    writer.close();
    out.close();
    this.out = new FileWriter(fileAllData, false);
    this.writer = new BufferedWriter(out);
  }

  public void writeMap(
      Map<Integer, Character[]> map) { // этот метод записывает карту в файл FullInfo предназначен для класса HallMap
    try {
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
    } catch (IOException e) {
      System.out.println("Ошибка!");
    }
  }
}
