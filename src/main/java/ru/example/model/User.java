package ru.example.model;

import ru.example.exeption.WrongLoginException;
import ru.example.exeption.WrongPasswordException;

import java.util.Objects;
import java.util.regex.Pattern;

public class User {

    private String login;

    private String password;

    private String confirmPassword;

    public User() {
    }

    public User(String login, String password, String confirmPassword) {
        this.login = login;
        this.password = password;
        this.confirmPassword = password;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.isBlank() || login.length()<3 || login.length() > 20) {
            throw new WrongLoginException("Логин должен состоять из латинских букв и подчеркивания не менее 3 и не более 20 символов ");

        } else if (!Pattern.matches("^[a-zA-Z0-9_]+$", login)) {
            throw new WrongLoginException("Логин должен состоять из латинских букв и подчеркивания не менее 3 и не более 20 символов");

        } else {
            this.login = login;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isBlank() || password.length() < 3 || password.length() > 20) {
            throw new WrongPasswordException("Пароль должен состоять из латинских букв и подчеркивания не менее 3 и не более 20 символов ");

        } else if (!Pattern.matches("^[a-zA-Z0-9_]+$", login)) {
            throw new WrongPasswordException("Пароль должен состоять из латинских букв и подчеркивания не менее 3 и не более 20 символов");

        } else {

            this.password = password;
        }
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        if (!(confirmPassword.equals(password))) {
            throw new WrongPasswordException("Неверно введено подтверждение пароля ");

        } else {
            this.confirmPassword = password;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User)) return false;
        User user = (User) object;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, confirmPassword);
    }
}
