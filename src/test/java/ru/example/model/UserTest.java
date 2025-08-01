package ru.example.model;

import junit.framework.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.example.exeption.WrongLoginException;
import ru.example.exeption.WrongPasswordException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
public class UserTest {
    public User userTest;
    public User userTestTwo;
    String logO = "Val";
    String pass = "1234";


    @BeforeAll
    public void setupUser() {
        userTest = new User("vit_", "8624qwe", "8624qwe");
        userTestTwo = new User("ffff", "dddd", "dddd");
        System.out.println("Создан пользователь перед всеми тестами: " + userTest);
    }

    @AfterAll
    public void deleteUser() {
        userTest = null;
        userTestTwo = null;
        System.out.println("Очистка после теста");
    }

    @Test
    @DisplayName("создание пользователя ")
    void userCreatTwoParam() {
        User user = new User("Алекс", "we4");
        assertNotNull(user);
        assertEquals("Алекс", user.getLogin());
        assertEquals("we4", user.getPassword());
    }

    @Test
    @DisplayName("Получение логина")
    void getLoginTest() {
        String loginExpexted = "vit_";
        assertEquals(loginExpexted, userTest.getLogin());
        Assertions.assertTrue(userTest.getLogin().contains("v"));
        Assertions.assertFalse(userTest.getLogin().isBlank());
    }

    @Test
    @DisplayName("запись логина")
    void setLoginTest() {
        userTestTwo.setLogin(logO);
        assertEquals("Val", userTestTwo.getLogin());
        Assertions.assertFalse(userTestTwo.getLogin().contains("g"));
    }

    @Test
    @DisplayName("Валидация логина")
    void setLoginTestValid() {
        Assertions.assertThrows(WrongLoginException.class, () -> userTestTwo.setLogin("пп "), "Логин должен состоять из латинских букв и подчеркивания не менее 3 и не более 20 символов ");
    }


    @Test
    @DisplayName("Получение пароля")
    void getPasswordTest() {
        String passwordExpected = "8624qwe";
        assertEquals(passwordExpected, userTest.getPassword());
    }

    @Test
    @DisplayName("запись пароля ")
    void setPasswordTest() {
        userTestTwo.setPassword(pass);
        assertNotNull(userTestTwo.getPassword());
        assertEquals("1234", userTestTwo.getPassword());
    }

    @Test
    @DisplayName("Валидация пароля")
    void setPasswordTestValid() {
        Assertions.assertThrows(WrongPasswordException.class, () -> userTestTwo.setPassword("пп"), "Пароль должен состоять из латинских букв и подчеркивания не менее 3 и не более 20 символов ");
    }

    @Test
    @DisplayName("Подтверждение пароля ")
    void getConfirmPasswordTest() {
        String confirfPasswodExpexted = "8624qwe";
        Assert.assertEquals(confirfPasswodExpexted, userTest.getConfirmPassword());
    }

    @Test
    @DisplayName("Проверка пароля ")
    void setConfirmPasswordTest() {
        userTestTwo.setPassword(pass);
        userTestTwo.setConfirmPassword("1234");
        assertNotNull(userTestTwo.getConfirmPassword());
        assertEquals("1234", userTestTwo.getPassword());
        assertEquals("1234", userTestTwo.getConfirmPassword());
        assertEquals(userTestTwo.getPassword(), userTestTwo.getConfirmPassword());

    }

    @Test
    @DisplayName("Проверка валидации подтверждения пароля ")
    void setConfirmPasswordTestValid() {
        userTestTwo.setPassword(pass);
        Assertions.assertThrows(WrongPasswordException.class, () -> userTestTwo.setConfirmPassword("пп"), "Неверно введено подтверждение пароля ");
        Assertions.assertThrows(WrongPasswordException.class, () -> userTestTwo.setConfirmPassword("dd"));
    }

    @Nested
    class getUserTestClass {
        @Test
        @DisplayName("Получение логина")
        void getLoginTestNest() {
            String loginExpexted = "vit_";
            assertEquals(loginExpexted, userTest.getLogin());
            Assertions.assertTrue(userTest.getLogin().contains("v"));
            Assertions.assertFalse(userTest.getLogin().isBlank());
        }

        @Test
        @DisplayName("Получение пароля")
        void getPasswordTestNest() {
            String passwordExpected = "8624qwe";
            assertEquals(passwordExpected, userTest.getPassword());
        }

    }
    @ParameterizedTest
    @DisplayName("Параметризованный тест создание user")
    @CsvSource({"vit_,8624"})
    void addUser (String log1, String passO) {
        User userThree = new User(log1,passO);

        Assertions.assertAll("test user",
                ()-> assertNotNull(userThree.getLogin()),
                ()->assertEquals(log1,userThree.getLogin()),
                ()-> assertEquals(passO,userThree.getPassword()));

    }
    static Stream <User>  provideUsers() {
        return Stream.of(new User("alice", "8624"));
    }
    @ParameterizedTest
    @MethodSource("provideUsers")
    void testUserCreat (User user) {
        assertNotNull(user.getLogin());
    }
}