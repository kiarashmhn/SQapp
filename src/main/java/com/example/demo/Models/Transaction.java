package com.example.demo.Models;

import javax.persistence.*;


@Entity
@Table
public class Transaction {
    @Id
    @SequenceGenerator(name = "transaction_seq", sequenceName = "TRANSACTION_SEQ")
    @GeneratedValue(generator ="transaction_seq" ,strategy = GenerationType.AUTO)
    private int id;
    private int debit;
    private int date;
    private int time;
    private int transactionMoney;

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTransactionMoney() {
        return transactionMoney;
    }

    public void setTransactionMoney(int transactionMoney) {
        this.transactionMoney = transactionMoney;
    }

}
