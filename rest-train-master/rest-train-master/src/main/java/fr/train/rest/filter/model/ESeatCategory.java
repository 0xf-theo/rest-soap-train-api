package fr.train.rest.filter.model;

import io.ebean.annotation.DbEnumValue;

public enum ESeatCategory {

    FIRST_CLASS(2d),
    PREMIUM_CLASS(1.60),
    STANDARD(1d),
    ;


    private Double priceFactor;

    ESeatCategory(Double priceFactor) {
        this.priceFactor = priceFactor;
    }

    public Double getPriceFactor() {
        return priceFactor;
    }

    @DbEnumValue
    public String dbValue() {
        return this.name();
    }
}
