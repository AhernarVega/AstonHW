package FileManager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// "Менеджер" для работы с файлами
public class FileManager {

    // Запись данных Person в файл по указанному пути
    public static void writePerson(Path path, Person person) {
        // Отлавливаем исключения
        try {
            // Записываем в файл объект person, приведенный к строке и к массиву байт
            Files.write(path, person.toString().getBytes());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Чтение данных Person из файла по указанному пути
    public static Person readPerson(Path path) {
        // Отлавливаем исключения
        try {
            // Проверяем, что файл по указанному пути существует
            if (Files.exists(path)) {
                // Считываем все строки в список
                List<String> temp = Files.readAllLines(path);
                // Если считано неверное количество данных, принимаем считанное за мусор
                if (temp.size() == 3) {
                    // Разбиваем каждую полученную строку на части до и после : и берем вторую
                    // Записывая их в нужные поля Person
                    return new Person(temp.get(0).split(":")[1],
                            temp.get(1).split(":")[1],
                            temp.get(2).split(":")[1]);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    // Запись данных Person в файл по указанному пути с сереализацией
    public static void writePersonSerializable(String path, Person person) {
        // Отлавливаем исключения и очищаем память, выделенную под объект в ()
        try (ObjectOutputStream serializeWriter = new ObjectOutputStream(new FileOutputStream(path))) {
            // Записываем данные в сереализованном виде
            serializeWriter.writeObject(person);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Чтение данных Person из файла по указанному пути с десериализацией
    public static Person readPersonDeserializable(String path) {
        // Отлавливаем исключения и очищаем память, выделенную под объект в ()
        try (ObjectInputStream serializeReader = new ObjectInputStream(new FileInputStream(path))) {
            // Читаем данные в десериализованном виде
            return (Person) serializeReader.readObject();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
