package com.mobiquityinc.packer.model;

import java.math.BigDecimal;
import java.util.List;

/*
 * An immutable object contain a pack with list of items and max weight of pack.
 */
public final class Pack {
    /**
     * Represents the max weight of this pack.
     */
    private final BigDecimal weight;
    /**
     * List of items exist in pack.
     */
    private final List<Item> items;

    public Pack(BigDecimal weight, List<Item> items) {
        this.weight = weight;
        this.items = items;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public List<Item> getItems() {
        return items;
    }
}
