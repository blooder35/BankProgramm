package system.model;

import java.io.Serializable;
import java.sql.Date;

public class BankAccount implements Serializable {
    private int id;
    private int ownerID;
    private Date dateOfOpening;
    private int balance;

    public BankAccount() {
    }

    public BankAccount(int id,int ownerID, Date dateOfOpening, int balance) {
        this.ownerID = ownerID;
        this.dateOfOpening = dateOfOpening;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public Date getDateOfOpening() {
        return dateOfOpening;
    }

    public void setDateOfOpening(Date dateOfOpening) {
        this.dateOfOpening = dateOfOpening;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
