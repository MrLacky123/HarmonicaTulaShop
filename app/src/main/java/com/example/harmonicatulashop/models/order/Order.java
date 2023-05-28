package com.example.harmonicatulashop.models.order;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {

    public static final String NAME = "Заказ";

    public static final String ID = "com.example.harmonicatulashop.models.order.ID";
    public static final String HARMONICA_IDS = "com.example.harmonicatulashop.models.order.HARMONICA_IDS";
    public static final String BAYAN_IDS = "com.example.harmonicatulashop.models.order.BAYAN_IDS";
    public static final String ACCORDION_IDS = "com.example.harmonicatulashop.models.order.ACCORDION_IDS";
    public static final String PRICE = "com.example.harmonicatulashop.models.order.PRICE";
    public static final String PERSON_ID = "com.example.harmonicatulashop.models.order.PERSON_ID";
    public static final String PICKING_UP = "com.example.harmonicatulashop.models.order.PICKING_UP";
    public static final String DELIVERY_ADDRESS = "com.example.harmonicatulashop.models.order.DELIVERY_ADDRESS";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "harmonicas")
    private byte[] harmonicaIds;

    @ColumnInfo(name = "bayans")
    private byte[] bayanIds;

    @ColumnInfo(name = "accordions")
    private byte[] accordionIds;
    @ColumnInfo(name = "price")
    private int price;

    @ColumnInfo(name = "personId")
    private int personId;
    @ColumnInfo(name = "pickingUp")
    private boolean pickingUp;

    @ColumnInfo(name = "deliveryAddress")
    private String deliveryAddress;

    public Order() {}

    public Order(byte[] harmonicaIds, byte[] bayanIds, byte[] accordionIds, int price, int personId, boolean pickingUp, String deliveryAddress) {
        this.harmonicaIds = harmonicaIds;
        this.bayanIds = bayanIds;
        this.accordionIds = accordionIds;
        this.price = price;
        this.personId = personId;
        this.pickingUp = pickingUp;
        this.deliveryAddress = deliveryAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getHarmonicaIds() {
        return harmonicaIds;
    }

    public void setHarmonicaIds(byte[] harmonicaIds) {
        this.harmonicaIds = harmonicaIds;
    }

    public byte[] getBayanIds() {
        return bayanIds;
    }

    public void setBayanIds(byte[] bayanIds) {
        this.bayanIds = bayanIds;
    }

    public byte[] getAccordionIds() {
        return accordionIds;
    }

    public void setAccordionIds(byte[] accordionIds) {
        this.accordionIds = accordionIds;
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

    public boolean isPickingUp() {
        return pickingUp;
    }

    public void setPickingUp(boolean pickingUp) {
        this.pickingUp = pickingUp;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
