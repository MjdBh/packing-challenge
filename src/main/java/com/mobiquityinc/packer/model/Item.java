package com.mobiquityinc.packer.model;

import java.math.BigDecimal;

/**
 * An immutable object of pack items
 */
public final class Item {
    /**
     * Represents the unique index of this particular item.
     */
    private final int index;

    /**
     * Represents the weight of this particular item.
     */
    private final BigDecimal weight;

    /**
     * Represents the cost of this particular item.
     */
    private final int cost;

    public Item(int index, BigDecimal weight, int cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "{" +
                "index=" + index +
                ", weight=" + weight +
                ", cost=" + cost +
                '}';
    }
}
