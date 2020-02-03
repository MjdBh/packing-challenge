package com.mobiquityinc.packer.presenter;

import com.mobiquityinc.packer.model.Item;

import java.util.List;

/**
 * This strategy used for present solved problem results
 */
@FunctionalInterface
public interface ItemPresenterStrategy {
    /**
     * Present method must be implemented on every strategy
     * @param items that founded from pack
     * @return String representation of Items.
     */
    String present(List<Item> items);

    /**
     * Default method for present - character for empty item on all presenters.
     * @param items Items that found in pack for present
     * @return String representation of Items that add - for empty list.
     */
    default String print(List<Item> items) {
        if (items == null || items.isEmpty()) {
            return "-";
        }
        return present(items);
    }
}
