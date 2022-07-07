package src.main.java.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    //Item DTO Data Transfer Object
    private String name;
    private BigDecimal cost;
    private int inventory; // no of items in inventory


    // constructors
    public Item(String name, String cost, int inventory) {
        this.name = name;
        this.cost = new BigDecimal(cost);
        this.inventory = inventory;
    }

    // getters and setters
    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    // hashCode for checking are two items same
    @Override
    public int hashCode() {
        return Objects.hash(name, cost, inventory);
    }

    // check similarity
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.inventory != other.inventory) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }

    // for putting all values of an item in string
    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", price=" + cost + ", inventory=" + inventory + '}';
    }
}
