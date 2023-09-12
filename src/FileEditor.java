import java.io.*;
import java.util.Map;

/**
 * Класс для работы с файлами, в которых хранится информация о сеансах и билетах в кинозале.
 * Этот класс обеспечивает чтение и запись/перезапись данных из/в файлы.
 */
public class FileEditor {

  // Индекс ключа (части строки) при разборе данных из файла
  private final int KEY = 0;
  // Индекс значения (части строки) при разборе данных из файла
  private final int VALUE = 1;
  // Индекс для удаления первого символа в строке (нулевой индекс)
  private final int REMOVE_FIRST_INDEX = 1;
  // Максимальная длина строки в файле "InfoFull.txt"
  private final int INFO_FULL_LENGHT = 48;
  // Поток для чтения данных из файла
  private BufferedReader reader;
  // Объект для чтения данных из файла
  private FileReader in;
  // Объект для записи данных в файл
  private FileWriter out;
  // Поток для записи данных в файл
  private BufferedWriter writer;
  // Переменная, содержащая путь к файлу "InfoFull.txt"
  private String fileAllData;
  // Переменная, содержащая путь к файлу "Check.txt"
  private String fileCheque;
  // Количество строк в файле "InfoFull.txt"
  private int lines;
  // Счетчик для отслеживания количества считанных записей из "InfoFull.txt"
  private int halMapReaderCounter;

  /**
   * Конструктор класса
   * Инициализирует объект FileEditor и задает пути к файлам
   */
  public FileEditor() {
    this.fileAllData = "res/InfoFull.txt";
    this.fileCheque = "res/Check.txt";
    this.lines = 0;
    this.halMapReaderCounter = 0;
  }

  /**
   * Метод читает данные из файла на основе указанного префикса и разделителя
   *
   * @param prefix префикс, используемый для поиска соответствующих данных в файле
   * @param splitter разделитель для объединения данных в строку
   * @return строка, содержащая считанные данные из файла, объединенные разделителем
   */
  private String read(String prefix,
      String splitter) {
    boolean isCheque = true;
    StringBuilder result = new StringBuilder();
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

  /**
   * Метод для чтения данных из файла и заполнения ими структуры данных Map
   *
   * @param thisMap структура данных Map для заполнения считанными данными
   * @return заполненная структура данных Map
   */
  public Map<Integer, Character[]> readMap(
      Map<Integer, Character[]> thisMap) {
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
      System.err.println("Файл для чтения не найден! Обратитесь к сервисному руководству ");
    }
    return null;
  }

  /**
   * Метод для чтения данных из файла по заданным параметрам
   *
   * @param prefix префикс для поиска данных в файле
   * @param splitter разделитель между данными
   * @return считанные данные из файла
   */
    public String readData(String prefix,
      String splitter) {
    return read(prefix, splitter);
  }

  /**
   * Метод для записи данных в файлы по заданным параметрам.
   *
   * @param data данные для записи
   * @param prefix префикс для определения файла, в который будет произведена запись
   */
  public void writeData(String data,
      String prefix) {
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

  /**
   * Метод возвращает количество строк в файле
   *
   * @return количество строк в файле
   */
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
      System.out.println("Ошибка чтения!");
    }
    return 0;
  }

  /**
   * Метод используется только внутри класса.
   * Он нужен для того, чтобы перезаписывать значения в файл FullInfo, а не создавать новые файлы.
   *
   * @throws IOException
   */
  private void resetFile()
      throws IOException {
    writer.close();
    out.close();
    this.out = new FileWriter(fileAllData, false);
    this.writer = new BufferedWriter(out);
  }

  /**
   * Метод для записи данных Map в файл для хранения информации о местах в кинозале
   *
   * @param map структура данных Map для записи.
   */
  public void writeMap(
      Map<Integer, Character[]> map) {
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