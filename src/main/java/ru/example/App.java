package ru.example;

import ru.example.exeption.InvalidInput;
import ru.example.exeption.WrongLoginException;
import ru.example.exeption.WrongPasswordException;
import ru.example.model.Person;
import ru.example.model.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static final System.Logger logger = System.getLogger("MyApp");


    public static void main(String[] args) {

        SimpleDateFormat dateOfBirth = new SimpleDateFormat("dd.MM.yyyy");
        List<Person> personList = new ArrayList<>();

        int minCount = 0;
        int maxCount;

        Scanner userCreat = new Scanner(System.in);
        System.out.println("Создание пользователя для работы с базой данных :");
        System.out.println("Введите логин :");
        String log = userCreat.nextLine();

        System.out.println("Задайте пароль :");
        String pass = userCreat.nextLine();
        System.out.println("Поддвердите пароль:");

        try {
            while (true) {
                String passConfirm = userCreat.nextLine();
                if (passConfirm.equals(pass)) {
                    break;
                } else {
                    System.out.println("Введен не верный пароль :");
                }
            }
        } catch (WrongPasswordException e) {
            logger.log(Level.ERROR,"Ошибка ввода пароля",e);
        }

        User user = new User(log, pass);


        Scanner confirmScan = new Scanner(System.in);
        System.out.println("Войдите в систему: ");
        int i = 0;
        while (i < 4) {
            System.out.println("Ведите логин : ");
            String logIn = confirmScan.nextLine();
            i++;
            try {
                if (user.getLogin().equals(logIn)) {
                    break;

                } else if (i==3) {
                    System.exit(1);

                } else {
                    throw new WrongLoginException("Не верно введен логин,осталось попыток :" + (3-i));
                }
            } catch (WrongLoginException e) {
                e.printStackTrace();
            }
        }
        while (i < 4) {
            System.out.println("Ведите пароль : ");
            String passIn = confirmScan.nextLine();
            i++;
            try {
                if (user.getPassword().equals(passIn)) {
                    break;

                }else if(i == 3) {
                    System.exit(1);
                }
                else {
                    throw new WrongLoginException("Не верно введен пароль,осталось попыток :" + (3-i));
                }
            } catch (WrongPasswordException e) {
                e.printStackTrace();
            }
        }

        Scanner personCountScanner = new Scanner(System.in);
        System.out.println("Введите количество человек заносимых в базу : ");
        while (true) {
            maxCount = personCountScanner.nextInt();
            if (maxCount > 0) {
                break;
            } else {
                System.out.println("Введенны не верные данные, попробуйте еще раз ");
                personCountScanner.nextInt(); /*может не понадобиться*/
            }
        }


        Person person = new Person();
        Scanner inputPerson = new Scanner(System.in);
        while (minCount < maxCount) {

            while (true) {
                try {
                    System.out.println("Введите фамилию : ");
                    String lastNamePerson = inputPerson.nextLine().toLowerCase();
                    person.setLastName(lastNamePerson);

                } catch (InvalidInput e) {
                    e.printStackTrace();
                }
                if (!(person.getLastName() == null || person.getLastName().isEmpty())) {
                    break;

                }

            }
            while (true) {
                try {
                    System.out.println("Введите имя : ");
                    String namePerson = inputPerson.nextLine().toLowerCase();
                    person.setName(namePerson);

                } catch (InvalidInput e) {
                    e.printStackTrace();
                }

                if (!(person.getName() == null || person.getName().isEmpty())) {

                    break;

                }

            }
            while (true) {
                try {
                    System.out.println("Введите отчество : ");
                    String surnamePerson = inputPerson.nextLine().toLowerCase();
                    person.setSurname(surnamePerson);

                } catch (InvalidInput e) {
                    e.printStackTrace();
                }

                if (!(person.getSurname() == null || person.getSurname().isEmpty())) {
                    break;

                }

            }
            while (true) {
                try {
                    System.out.println("Введите дату рождения : ");
                    String dateBirth = inputPerson.next();
                    Date date = dateOfBirth.parse(dateBirth);
                    person.setDateOfBirthday(date);

                } catch (InvalidInput e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    System.out.println("Ошибка парсинга даты: " + e.getMessage());
                }

                if (!(person.getDateOfBirthday() == null)) {
                    break;

                }

            }
            while (true) {
                try {
                    System.out.println("Введите номер телефона : ");
                    String phoneNumbers = inputPerson.next();
                    person.setPhoneNumber(Long.valueOf(phoneNumbers));

                } catch (InvalidInput e) {
                    e.printStackTrace();
                }
                if (!(person.getPhoneNumber() == null)) {
                    break;

                }

            }

            while (true) {
                try {
                    System.out.println("Введите пол : ");
                    String genderPerson = inputPerson.next().toLowerCase();
                    person.setGender(genderPerson);

                } catch (InvalidInput e) {
                    e.printStackTrace();
                }
                if (!(person.getGender() == null || person.getGender().isEmpty())) {

                    break;

                }

            }

            while (true) {
                try {
                    System.out.println("Введите возвраст : ");
                    String agePerson = inputPerson.next();
                    person.setAge(Integer.valueOf(agePerson));

                } catch (InvalidInput e) {
                    e.printStackTrace();
                }
                if (!(person.getAge() == null)) {
//                    minCount += 1;
                    break;

                }
            }

            personList.add(person);
            System.out.println(personList);

            String fileName = person.getLastName() + ".txt";
            String dirPath = "C:\\project\\MavenProjectThree\\src\\main\\java\\ru\\example\\bd\\";
            File file = new File(dirPath + fileName);
            boolean flag = false;
            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("Файл " + file + " успешно создан.");
                } else {
                    System.out.println("Файл " + file + " уже существует и будет дозаписан.");
                    flag = true;
                }
            } catch (IOException e) {
                System.err.println("Ошибка при создании файла: " + e.getMessage());
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, flag))) {
                for (Person p : personList) {
                    writer.write(String.valueOf(p));
                    writer.newLine();
                    writer.flush();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            minCount++;
        }

        personCountScanner.close();
        inputPerson.close();
        userCreat.close();
        confirmScan.close();
    }

}





