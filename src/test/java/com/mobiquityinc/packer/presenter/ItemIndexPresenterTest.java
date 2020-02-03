package com.mobiquityinc.packer.presenter;

import com.mobiquityinc.packer.parser.TextPackParser;
import com.mobiquityinc.packer.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class ItemIndexPresenterTest {

    @Test
    @DisplayName("When Items sent to presenter only indices printed")
    void presenterShouldPrintOnlyIndexOfItems() {
        TextPackParser textPackParser = new TextPackParser(Utils.getAbsolutePath("item_index_presenter.txt"));
        ItemIndexPresenter itemIndexPresenter = new ItemIndexPresenter();
        assertThat(itemIndexPresenter.print(textPackParser.load().get(0).getItems())).isEqualTo("1,2,3,4,5,6,7");
    }

    @Test
    @DisplayName("When Items is empty must print -.")
    void presenterShouldPrintDashIndexOfForEmptyItem() {
        ItemIndexPresenter itemIndexPresenter = new ItemIndexPresenter();
        assertThat(itemIndexPresenter.print(new ArrayList<>())).isEqualTo("-");

    }

    @Test
    @DisplayName("When Items is null must print -.")
    void presenterShouldPrintDashIndexOfForNullItem() {
        ItemIndexPresenter itemIndexPresenter = new ItemIndexPresenter();
        assertThat(itemIndexPresenter.print(null)).isEqualTo("-");
    }
}