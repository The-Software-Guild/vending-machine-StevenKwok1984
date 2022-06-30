package src.main.java.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    private String name;
    private BigDecimal price;
    private int inventory; // number of items in inventory

    // constructors
    public Item(String name, String price, int inventory) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.inventory = inventory;
    }

    public Item(String name) {
        this.name = name;
    }

    // Methods for overriding
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.price);
        hash = 31 * hash + this.inventory;
        return hash;
    }

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
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", price=" + price + ", inventory=" + inventory + '}';
    }



    // getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
}
