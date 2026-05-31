package com.expense_tracker;

public class App
{
    public static void main( String[] args )
    {
        AppController ac = new AppController();
        ac.chooseAction(args);
    }
}
