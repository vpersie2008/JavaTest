package com.jerry.jdbc.model;

import java.math.BigDecimal;

public class BalanceEntity {
    public int id;
    public String user;
    public String password;
    public BigDecimal balance;

    public BalanceEntity() {
    }

    public BalanceEntity(int id, String user, String password, BigDecimal balance) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BalanceEntity{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
