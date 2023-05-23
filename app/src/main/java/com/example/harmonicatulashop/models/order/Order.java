package com.example.harmonicatulashop.models.order;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {

    public static final String NAME = "Заказ";

    public static final String CATEGORY_HARMONICA =
            "com.example.harmonicatulashop.models.order.HARMONICA";

    public static final String CATEGORY_BAYAN =
            "com.example.harmonicatulashop.models.order.BAYAN";

    public static final String CATEGORY_ACCORDION =
            "com.example.harmonicatulashop.models.order.ACCORDION";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "number")
    private int number;

    @ColumnInfo(name = "harmonicaId")
    private String harmonicas;

    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "personId")
    private int personId;

    public Order() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getHarmonicas() {
        return harmonicas;
    }

    public void setHarmonicas(String harmonicas) {
        this.harmonicas = harmonicas;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
