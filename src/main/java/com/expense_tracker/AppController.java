package com.expense_tracker;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

public class AppController { // controls inputs
    private final Map<Integer, Expense> expenses;
    ExpenseStorage es = new ExpenseStorage();

    public AppController() {
        this.expenses = es.loadExpenses();
    }

    public void chooseAction(String[] args) {
        try {
            String command = args[0];
            switch (command) {
                case "add" -> addExpense(args); // add expense + save it
                case "update" -> updateExpense(args);
                case "list" -> listExpenses();
                case "summary" -> summary(args);
                case "delete" -> deleteExpense(args);
                default -> System.out.println("Invalid command: " + command);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Amount and ID must be numbers!");
        }  catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addExpense(String[] args) {
        if (args.length < 5) {
            System.out.println("Not enough arguments.");
            System.exit(1);
        }
        if (!args[1].equals("--description") || !args[3].equals("--amount")) {
            System.out.println("Invalid arguments.");
            System.out.println("Usage: expense-tracker add --description [text] --amount [number]");
            System.exit(1);
        }
        int nextId = expenses.keySet().stream().max(Integer::compareTo).orElse(0) + 1; // find the max ID, the next one is +1.
        String description = args[2];
        double amount = Double.parseDouble(args[4]);

        Expense expense = new Expense(nextId,LocalDate.now(),description,amount); // create expense
        expenses.put(expense.getId(), expense); // save to map
        es.save(expenses); // save to file
        System.out.println("Expense added successfully (ID: "+expense.getId()+")");
    }

    private void updateExpense(String[] args) {
        if (args.length < 5) {
            System.out.println("Not enough arguments.");
            System.exit(1);
        }
        int id = Integer.parseInt(args[4]);
        if (!expenses.containsKey(id)) {
            System.out.println("Expense with ID: " +id+" not found!");
            System.exit(1);
        }
        switch (args[1]) {
            case "--description":
                expenses.get(id).setDescription(args[2]);
                es.save(expenses);
                System.out.println("Expense updated successfully (ID: "+ id+")");
                break;
            case "--amount":
                expenses.get(id).setAmount(Integer.parseInt(args[2]));
                es.save(expenses);
                System.out.println("Expense updated successfully (ID: "+id+")");
                break;
            case "--date":
                expenses.get(id).setDate(LocalDate.parse(args[2]));
                es.save(expenses);
                System.out.println("Expense updated successfully (ID: "+id+")");
                break;
            default: System.out.println("Invalid arguments.");
        }
    }

    private void listExpenses() {
        System.out.printf("%-5s %-12s %-20s %-10s%n", " ID", "Date", "Description", "Amount");
        for (Expense e : expenses.values()) {
            System.out.printf(" %-5s %-12s %-20s %-10.2f%n", e.getId(), e.getDate(), e.getDescription(), e.getAmount());
        }
    }

    private void summary(String[] args) throws IllegalArgumentException {
        if (args.length == 1) { // total expenses
            double totalSum = expenses.values().stream().mapToDouble(Expense::getAmount).sum();
            System.out.println("Total expenses: $" + totalSum);
        } else if (args.length == 3) { // total expenses for particular month
            if (!args[1].equals("--month")) {
                throw new IllegalArgumentException("Wrong argument.");
            }

            int month = Integer.parseInt(args[2]);
            if  (month < 1 || month > 12) {
                throw new IllegalArgumentException("Wrong month argument.");
            }
            double totalSum = expenses.values().stream()
                    .filter(e -> e.getDate().getMonthValue() == month) // leaving only needed month
                    .mapToDouble(Expense::getAmount)
                    .sum();
            System.out.println("Total expenses for " + Month.of(month) + ": $" + totalSum);
        }
    }

    private void deleteExpense(String[] args) {
        if (args.length < 2) {
            System.out.println("Error: Please provide ID to delete.");
            return;
        }
        int id = Integer.parseInt(args[2]);

        if (expenses.containsKey(id)) {
            expenses.remove(id);
            es.save(expenses);
            System.out.println("Expense deleted successfully");
        } else {
            System.out.println("Expense with ID "+ id + " not found");
        }
    }
}
