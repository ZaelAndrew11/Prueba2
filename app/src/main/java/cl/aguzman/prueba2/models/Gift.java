package cl.aguzman.prueba2.models;

import com.orm.SugarRecord;

public class Gift extends SugarRecord{
    private String name;
    private String description;
    private int price;
    private int priority;
    private boolean bought;

    public Gift(){

    }

    public Gift(String name, String description, int price, int priority, boolean bought) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.priority = priority;
        this.bought = bought;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
