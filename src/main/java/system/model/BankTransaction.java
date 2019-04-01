package system.model;

import java.sql.Date;

public class BankTransaction {
    private int id;
    private int amount;
    private int sender;
    private int recipient;
    private Date dateAndTime;

    public BankTransaction() {

    }

    public BankTransaction(int id, int amount, int sender, int recipient, Date dateAndTime) {
        this.id = id;
        this.amount = amount;
        this.sender = sender;
        this.recipient = recipient;
        this.dateAndTime = dateAndTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
