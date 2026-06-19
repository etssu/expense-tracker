package com.expense_tracker;

import java.time.LocalDate;

public class Expense {
    private final int id;
    private double amount;
    private LocalDate date;
    private String description;

    public Expense(int id, LocalDate date, String description, double amount) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getId() { return id; }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() { return date; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "Date: " + this.date + "Description: " + this.description + "Amount: " + this.amount;
    }
}
