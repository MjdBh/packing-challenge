package com.mobiquityinc.packer.presenter;

import com.mobiquityinc.packer.model.Item;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This presenter is a implementation of {@link ItemPresenterStrategy} that print {@link Item} itemIndex field and joined by comma.
 */
public class ItemIndexPresenter implements ItemPresenterStrategy {
    @Override
    public String present(List<Item> items) {
        return items.stream().map(Item::getIndex).map(String::valueOf).collect(Collectors.joining(","));
    }
}
