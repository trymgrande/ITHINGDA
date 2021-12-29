import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@javax.persistence.Entity
@javax.persistence.Table(name = "account")

public class Account {
    @javax.persistence.Id
    @javax.persistence.Column(name = "accountNumber")
    @javax.persistence.GeneratedValue(generator = "incrementor")
    @org.hibernate.annotations.GenericGenerator(name = "incrementor", strategy = "increment")
    private int accountNumber;

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int id) {
        this.accountNumber = id;
    }

    @javax.persistence.Column(name = "ownerName")
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @javax.persistence.Column(name = "balance")
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean addBalance(double amount) throws InterruptedException {
//        this.balance += amount;
        double balanceRead = this.balance;
        Thread.sleep(5000);
        this.balance = balanceRead + amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (this.balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }

    @javax.persistence.Column(name = "updated_at")
    private java.sql.Date updated_at;

    public java.sql.Date getUpdated_at() {
        return updated_at;
    }
}

