package ru.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.example.exeption.InvalidInput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonTest {

    private Person personTest;

    @BeforeEach
    void setupPerson() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String birthDay = "15.06.1983";
        Date date = formatter.parse(birthDay);
        personTest = new Person("Попцов", "Виталий", "Александрович", date, 89179309086L, "m", 42);
    }

    @Test
    @DisplayName("Проверка получения имени Person ")
    void getLastName() {
        assertAll("person",
                () -> assertNotNull(personTest.getLastName()),
                () -> assertEquals("Попцов", personTest.getLastName()),
                () -> assertFalse(personTest.getLastName().contains("gg")));
    }

    @Test
    void setLastName() {
        personTest.setLastName("Пупкин");
        Assertions.assertEquals("Пупкин", personTest.getLastName());
    }

    @Test
    void setLastNameValid() {
        Exception exception = assertThrows(InvalidInput.class, () -> personTest.setLastName(""));
        Assertions.assertEquals("Фамилия не должна быть пустой строкой,и содержать не менее 3 букв ", exception.getMessage());
        Exception exceptionTwo = assertThrows(InvalidInput.class, () -> personTest.setLastName("54555"));
        Assertions.assertEquals("Фамилия должна состоять из русских букв", exceptionTwo.getMessage());
    }

    @Test
    void getName() {
        Assertions.assertEquals("Виталий", personTest.getName());
    }

    @Test
    void setName() {
        personTest.setName("Илья");
        Assertions.assertEquals("Илья", personTest.getName());
    }

    @Test
    void setNameValid() {

        Exception exception = assertThrows(InvalidInput.class, () -> personTest.setName(""));
        Assertions.assertEquals("Имя не должно быть пустой строкой, и содержать не менее 3 букв ", exception.getMessage());
        Assertions.assertThrows(InvalidInput.class, () -> personTest.setName(null));
        Assertions.assertEquals("Имя не должно быть пустой строкой, и содержать не менее 3 букв ", exception.getMessage());
        Assertions.assertThrows(InvalidInput.class, () -> personTest.setName("55555555")).getMessage().equals("Имя должно состоять из русских букв не менее 3 букв");
    }

    @Test
    void getSurname() {
        Assertions.assertEquals("Александрович", personTest.getSurname());
    }

    @Test
    void setSurname() {
       personTest.setSurname("Иванович");
       Assertions.assertEquals("Иванович",personTest.getSurname());
    }
    @Test
    void setSurnameValid() {
        Assertions.assertThrows(InvalidInput.class, () -> personTest.setSurname(null));
        Assertions.assertThrows(InvalidInput.class, () -> personTest.setSurname(""));
        Exception exception = assertThrows(InvalidInput.class, () -> personTest.setSurname("565656565"));
        Assertions.assertEquals("Имя должно состоять из русских букв не менее 3 букв", exception.getMessage());
    }

    @Test
    void getDateOfBirthday() {
        Assertions.assertNotNull(personTest.getDateOfBirthday());
    }

    @Test
    void setDateOfBirthday()throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

        String birth = "15.10.2021";
        Date date = formatter.parse(birth);
        personTest.setDateOfBirthday(date);

        Assertions.assertEquals(date,personTest.getDateOfBirthday());

    }
    @Test
    void setDateOfBirthdayValid(){
      Assertions.assertThrows(InvalidInput.class,()->personTest.setDateOfBirthday(null));

    }

    @Test
    void getPhoneNumber() {
        Assertions.assertEquals(Long.parseLong("89179309086"),personTest.getPhoneNumber());
        Assertions.assertNotNull(personTest.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        personTest.setPhoneNumber(89179319089L);
        Assertions.assertEquals(89179319089L,personTest.getPhoneNumber());
    }
    @Test
    void setPhoneNumberValid() {
        Assertions.assertThrows(InvalidInput.class,()->personTest.setPhoneNumber(null));
        Assertions.assertThrows(InvalidInput.class,()->personTest.setPhoneNumber(0L));
        Assertions.assertThrows(InvalidInput.class,()->personTest.setPhoneNumber(44545454L));
        Exception exception = assertThrows(InvalidInput.class,()->personTest.setPhoneNumber(4545L));
        Assertions.assertEquals("Номер телефона должен быть заполнен,не менее 11 цифр",exception.getMessage());

    }
    @Test
    void getGender() {
        Assertions.assertEquals("m",personTest.getGender());
    }

    @Test
    void setGender() {
        personTest.setGender("f");
        Assertions.assertEquals("f",personTest.getGender());

    }

    @Test
    void setGenderValid() {
        Assertions.assertThrows(InvalidInput.class,()->personTest.setGender("df"));
        Assertions.assertThrows(InvalidInput.class,()->personTest.setGender(null));
        Assertions.assertThrows(InvalidInput.class,()->personTest.setGender(""));
    }

    @Test
    void getAge() {
        Assertions.assertEquals(42,personTest.getAge());
    }

    @Test
    void setAge() {
        personTest.setAge(60);
        Assertions.assertEquals(60,personTest.getAge());
    }
    @Test
    void setAgeValid() {
        Assertions.assertThrows(InvalidInput.class,()->personTest.setAge(null));
        Assertions.assertThrows(InvalidInput.class,()->personTest.setAge(0));
        Assertions.assertThrows(InvalidInput.class,()->personTest.setAge(-1));
        Assertions.assertThrows(InvalidInput.class,()->personTest.setAge(100));
    }
}