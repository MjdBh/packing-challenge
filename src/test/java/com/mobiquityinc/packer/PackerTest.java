package com.mobiquityinc.packer;

import com.mobiquityinc.packer.util.Utils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PackerTest {

    @Test
    @DisplayName("execute packer and create valid pack")
    void pack() {
        String VALID_INPUT = "input_correct.txt";
        String pack = Packer.pack(Utils.getAbsolutePath(VALID_INPUT));
        assertThat(pack).isEqualTo("4" +
                System.lineSeparator() +
                System.lineSeparator() +
                "2,7" +
                System.lineSeparator() +
                "8,9");
    }

    @Test
    @DisplayName("execute packer and create valid pack if there is no item")
    void packForNoItemsLine() {
        String pack = Packer.pack(Utils.getAbsolutePath("valid_input_without_item.txt"));
        assertThat(pack).isEqualTo("");
    }
}