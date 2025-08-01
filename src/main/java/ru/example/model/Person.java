package ru.example.model;

import ru.example.exeption.InvalidInput;

import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class Person {

    private String lastName;

    private String name;

    private String surname;

    private Date dateOfBirthday;

    private Long phoneNumber;

    private String gender;

    private Integer age;

    public Person() {
    }

    public Person(String lastName, String name, String surname, Date dateOfBirthday, Long phoneNumber, String gender, Integer age) {
        this.lastName = lastName;
        this.name = name;
        this.surname = surname;
        this.dateOfBirthday = dateOfBirthday;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isBlank() || lastName.length() <= 2) {
            throw new InvalidInput("Фамилия не должна быть пустой строкой,и содержать не менее 3 букв ");

        } else if (!Pattern.matches("^[А-Яа-яёЁ]+$", lastName)) {
            throw new InvalidInput("Фамилия должна состоять из русских букв");

        } else {
            this.lastName = lastName;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank() || name.length() <= 2) {
            throw new InvalidInput("Имя не должно быть пустой строкой, и содержать не менее 3 букв ");

        } else if (!Pattern.matches("^[А-Яа-яёЁ]+$", name)) {
            throw new InvalidInput("Имя должно состоять из русских букв не менее 3 букв");

        } else {
            this.name = name;
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isBlank() || surname.length() <= 2) {
            throw new InvalidInput("Имя не должно быть пустой строкой, и содержать не менее 3 букв ");

        } else if (!Pattern.matches("^[А-Яа-яёЁ]+$", surname)) {
            throw new InvalidInput("Имя должно состоять из русских букв не менее 3 букв");

        } else {
            this.surname = surname;
        }

    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        if (dateOfBirthday == null) {
            throw new InvalidInput("Дата рождения должна быть введена в формате dd.MM.yyyy");
        } else {
            this.dateOfBirthday = dateOfBirthday;
        }
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        if (phoneNumber == null || phoneNumber == 0L || String.valueOf(phoneNumber).length()<11) {
            throw new InvalidInput("Номер телефона должен быть заполнен,не менее 11 цифр");
        }
        else {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.isBlank()) {
            throw new InvalidInput("Пол не может быть пустой строкой ");
        } else if (!(gender.equals("m") || gender.equals("f"))) {
            throw new InvalidInput("Пол не может быть пустой строкой, введите m или f");
        } else {
            this.gender = gender;
        }
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age == null || age <= 0 || age > 90) {
            throw new InvalidInput("Возраст должен быть указан и быть не менее 15 не более 90");
        } else {
            this.age = age;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Person)) return false;
        Person person = (Person) object;
        return Objects.equals(lastName, person.lastName) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(dateOfBirthday, person.dateOfBirthday) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(gender, person.gender) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, name, surname, dateOfBirthday, phoneNumber, gender, age);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                .add("Фамилия" + lastName + "'")
                .add("Имя" + name + "'")
                .add("Отчество" + surname + "'")
                .add("День рождения" + dateOfBirthday)
                .add("Номер телефона" + phoneNumber)
                .add("Пол" + gender + "'")
                .add("Возраст" + age)
                .toString();
    }
}