package model;

public class ListItem {

    private final String name;
    private final int amount;

    public ListItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }
}
