package com.mobiquityinc.packer.parser;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.Packer;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Pack;
import com.mobiquityinc.packer.util.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TextPackParserTest {

    private static final String INCORRECT_PATH = "incorrect";
    private static final String INVALID_LINE_FORMAT = "invalid_line_format.txt";
    private static final String INVALID_CHARACTER = "invalid_character.txt";
    private static final String INVALID_ITEM_FORMAT = "invalid_item_format.txt";
    private static final String INVALID_COST = "invalid_cost.txt";


    @Test
    @DisplayName("Input file path must be valid")
    void shouldThrowExceptionIfFileNotFound() {
        TextPackParser textPackParser = new TextPackParser(INCORRECT_PATH);

        assertThatThrownBy(textPackParser::load)
                .isInstanceOf(APIException.class)
                .hasMessage("Invalid file in given path");
    }

    @Test
    @DisplayName("Input file must be contain a valid item")
    void shouldThrowExceptionIfItemFormatIsInvalid() {
        TextPackParser textPackParser = new TextPackParser(Utils.getAbsolutePath(INVALID_ITEM_FORMAT));

        assertThatThrownBy(textPackParser::load)
                .isInstanceOf(APIException.class)
                .hasMessage("Unexpected items format: 1,53.38");
    }

    @Test
    @DisplayName("Input file must be contain valid line")
    void shouldThrowExceptionIfFileContainInvalidLine() {
        TextPackParser textPackParser = new TextPackParser(Utils.getAbsolutePath(INVALID_LINE_FORMAT));

        assertThatThrownBy(textPackParser::load)
                .isInstanceOf(APIException.class)
                .hasMessage("Unexpected line format: 81");
    }

    @Test
    @DisplayName("Input file must be contain valid character")
    void shouldThrowExceptionIfFileContainInvalidChar() {
        TextPackParser textPackParser = new TextPackParser(Utils.getAbsolutePath(INVALID_CHARACTER));

        assertThatThrownBy(textPackParser::load)
                .isInstanceOf(APIException.class)
                .hasMessage("Item data should be a valid number: 1,G,€45");
    }

    @Test
    @DisplayName("Input file must be contain valid cost")
    void shouldThrowExceptionIfFileContainInvalidCost() {
        TextPackParser textPackParser = new TextPackParser(Utils.getAbsolutePath(INVALID_COST));

        assertThatThrownBy(textPackParser::load)
                .isInstanceOf(APIException.class)
                .hasMessage("Item cost should be in valid format: ");
    }
}