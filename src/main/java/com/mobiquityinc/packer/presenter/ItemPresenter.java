package com.mobiquityinc.packer.presenter;

import com.mobiquityinc.packer.model.Item;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This presenter is a implementation of {@link ItemPresenterStrategy} used items toString and joined by comma.
 */
public class ItemPresenter implements ItemPresenterStrategy {
    @Override
    public String present(List<Item> items) {
        return items.stream().map(Item::toString).collect(Collectors.joining(","));
    }
}
