package com.example.demo.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table
public class Transaction {
    @Id
    @SequenceGenerator(name = "transaction_seq", sequenceName = "TRANSACTION_SEQ")
    @GeneratedValue(generator ="transaction_seq" ,strategy = GenerationType.AUTO)
    private int id;
    private int balance;
    private LocalDate date;
    private LocalTime time;
    private int transactionMoney;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(int transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

}
