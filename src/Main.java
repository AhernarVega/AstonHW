import FileManager.*;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        // Путь к файлу без сереализации
        String stringPath = "TestFile.txt";
        // Формирование Path на основе пути в виде строки для Files
        Path path1 = Path.of(stringPath);

        // Создание person
        Person person = new Person("Andrey", "Sokirka", "12345");
        // Запись person в файл
        FileManager.writePerson(path1, person);

        // Чтение данных и сразу их вывод в консоль
        System.out.println(FileManager.readPerson(path1));

        // Создание нового пути к файлу, в котором будет сереализован person
        String serPath = "TestFileSer.txt";

        // Сохранение person в файл с сереализацией
        FileManager.writePersonSerializable(serPath, person);
        // Чтение person из сереализованного файла
        System.out.println(FileManager.readPersonDeserializable(serPath));
    }
}