package com.ozorkin.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

public class Television extends Items {
    private int diagonal;
    private String country;
    public Television() {
    }

    public Television(String series, String screenType, int price, int diagonal, String country) {
        super(series, screenType, price);
        this.diagonal = diagonal;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Television{" +
                "diagonal=" + diagonal +
                ", country='" + country + '\'' +
                "series='" + super.getSeries() + '\'' +
                ", screenType='" + super.getScreenType() + '\'' +
                ", price=" + super.getPrice() +
                '}';
    }
}
