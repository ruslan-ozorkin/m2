package com.ozorkin.model;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class Telephone extends Items {
    private String model;
    public Telephone() {
    }

    public Telephone(String series, String screenType, int price, String model) {
        super(series, screenType, price);
        this.model = model;
    }

    @Override
    public String toString() {
        return "Telephone{" +
                "model='" + model + '\'' +
                "series='" + super.getSeries() + '\'' +
                ", screenType='" + super.getScreenType() + '\'' +
                ", price=" + super.getPrice() +
                '}';
    }
}
