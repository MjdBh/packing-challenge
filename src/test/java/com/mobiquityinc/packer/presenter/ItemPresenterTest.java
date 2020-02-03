package com.mobiquityinc.packer.presenter;

import com.mobiquityinc.packer.parser.TextPackParser;
import com.mobiquityinc.packer.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ItemPresenterTest {

    @Test
    @DisplayName("When Items sent to presenter only items to string printed")
    void presenterShouldPrintOnlyToStringOfItems() {
        TextPackParser textPackParser = new TextPackParser(Utils.getAbsolutePath("item_presenter.txt"));
        ItemPresenter itemPresenter = new ItemPresenter();
        System.out.println(itemPresenter.print(textPackParser.load().get(0).getItems()));
        assertThat(itemPresenter.print(textPackParser.load().get(0).getItems())).isEqualTo("{index=14, weight=98.23, cost=28}");
    }

    @Test
    @DisplayName("When Items is empty must print -.")
    void presenterShouldPrintDashIndexOfForEmptyItem() {
        ItemPresenter itemPresenter = new ItemPresenter();
        assertThat(itemPresenter.print(new ArrayList<>())).isEqualTo("-");

    }

    @Test
    @DisplayName("When Items is null must print -.")
    void presenterShouldPrintDashIndexOfForNullItem() {
        ItemPresenter itemPresenter = new ItemPresenter();
        assertThat(itemPresenter.print(null)).isEqualTo("-");
    }

}