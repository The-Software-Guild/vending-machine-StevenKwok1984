package src.main.java.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {

    //Item DTO Data Transfer Object
    //User should not be able to change any of these properties

    private String name;
    private BigDecimal price;
    private int inventory; // no of items in inventory



    public Item(String name, String price, int inventory) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.inventory = inventory;
    }

    public Item(String name) {
        this.name = name;
    }



    @Override
    public int hashCode() {
        return Objects.hash(name, price, inventory);
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

    public String getName() {
        return name;
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
