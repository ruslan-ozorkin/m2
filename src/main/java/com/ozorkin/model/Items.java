package com.ozorkin.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public abstract class Items {
    private String series;
    private String screenType;
    private int price;
    private ItemsType itemsType;

    public Items(String series, String screenType, int price) {
        this.series = series;
        this.screenType = screenType;
        this.price = price;
    }

    public Items() {
    }

    @Override
    public String toString() {
        return "Items{" +
                "series='" + series + '\'' +
                ", screenType='" + screenType + '\'' +
                ", price=" + price +
                ", itemsType=" + itemsType +
                '}';
    }
}
