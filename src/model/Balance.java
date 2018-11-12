package model;

public class Balance {

    private static Balance balance;
    private static int currentBalance;

    private Balance() {
//        currentBalance = 0;
    }

    public Balance getInstance() {
        return balance;
    }

    void deposit(int amount) {
        currentBalance += amount;
    }

    void withdrawal(int amount) {
        currentBalance -= amount;
    }

    int get() {
        return currentBalance;
    }
}
