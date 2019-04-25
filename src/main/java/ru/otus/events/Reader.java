package ru.otus.events;

public class Reader {
    private String firstName;
    private String secondName;
    private String regalia;

    private Reader(String firstName, String secondName, String regalia) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.regalia = regalia;
    }

    public static Reader getNew(String firstName, String secondName, String regalia) {
        return new Reader(firstName, secondName, regalia);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getRegalia() {
        return regalia;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", regalia='" + regalia + '\'' +
                '}';
    }
}
