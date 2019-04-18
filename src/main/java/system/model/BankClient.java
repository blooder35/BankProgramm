package system.model;

import java.io.Serializable;

public class BankClient implements Serializable {
    int id;
    String name;
    String surName;
    String patrName;
    String passportNumber;
    String dateOfBirth;

    public BankClient() {

    }

    public BankClient(String name, String surName, String patrName, String passportNumber, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.patrName = patrName;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getPatrName() {
        return patrName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPatrName(String patrName) {
        this.patrName = patrName;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "BankClient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", patrName='" + patrName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
