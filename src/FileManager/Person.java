package FileManager;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Base64;

// Пометили класс как сереализуемый
// Оказывается, Externalizable требует конструктор по умолчанию, узнал случайно
public class Person implements Externalizable {

    // Пометили, что поле относится к сереализации
    @Serial
    // Уникальный номер каждого объекта
    private static final long serialVersionUID = 100L;

    // Поля класса
    private String Name;
    private String Surname;
    private String Password;

    // Конструкторы класса
    public Person(String name, String surname, String pswd) {
        Name = name;
        Surname = surname;
        Password = pswd;
    }

    public Person() {
        Name = null;
        Surname = null;
        Password = null;
    }

    // Геттеры и сеттеры
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    // Переопределил метод toString()
    @Override
    public String toString() {
        return "Name:" +
                Name +
                "\nSurname:" +
                Surname +
                "\nPswd: " +
                Password;

    }

    // Шифрование пароля на основе Base64
    private String encryptString(@NotNull String data) {
        //        System.out.println(encryptedData);
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    // Дешифрование пароля на основе Base64
    private String decryptString(@NotNull String data) {
        //        System.out.println(decrypted);
        return new String(Base64.getDecoder().decode(data));
    }

    // Реализация методов интерфейса

    // Записываем данные в нужном нам формате в сереализованном виде
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getName());
        out.writeObject(getSurname());
        out.writeObject(encryptString(getPassword()));
    }

    // Читаем данные в нужном нам формате в сереализованном виде
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Name = (String)in.readObject();
        Surname = (String)in.readObject();
        Password = decryptString((String)in.readObject());
    }
}
