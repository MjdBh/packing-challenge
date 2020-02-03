package com.mobiquityinc.packer.knapsack;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.PackerTemplate;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Pack;
import com.mobiquityinc.packer.parser.PackParserStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PackerTemplateTest {

    @InjectMocks
    private PackerTemplate knapsackTemplate;

    @Mock
    private PackParserStrategy packParser;

    @Test
    @DisplayName("Pack weight limit should not exceed valid value")
    void shouldThrowExceptionIfPackWeightExceedValidValue() {

        BigDecimal exceededPackWeight = new BigDecimal(120);

        when(packParser.load()).thenReturn(Collections.singletonList(new Pack(exceededPackWeight,
                Collections.singletonList(new Item(1, new BigDecimal(70), 1)))));

        assertThatThrownBy(() -> knapsackTemplate.executePacker())
                .isInstanceOf(APIException.class)
                .hasMessage("Pack weight exceeded.");
    }

    @Test
    @DisplayName("Item weight limit should not exceed valid value")
    void shouldThrowExceptionIfItemsWeightExceedValidValue() {

        BigDecimal exceededItemWeight = new BigDecimal(120);

        when(packParser.load()).thenReturn(Collections.singletonList(new Pack(new BigDecimal(80),
                Collections.singletonList(new Item(1, exceededItemWeight, 1)))));

        assertThatThrownBy(() -> knapsackTemplate.executePacker())
                .isInstanceOf(APIException.class)
                .hasMessage("Item weight exceeded.");
    }

    @Test
    @DisplayName("Item cost limit should not exceed valid value")
    void shouldThrowExceptionIfItemsCostExceedValidValue() {

        int exceededItemWeight = 130;

        when(packParser.load()).thenReturn(Collections.singletonList(new Pack(new BigDecimal(80),
                Collections.singletonList(new Item(1, new BigDecimal(80), exceededItemWeight)))));

        assertThatThrownBy(() -> knapsackTemplate.executePacker())
                .isInstanceOf(APIException.class)
                .hasMessage("Item cost exceeded.");
    }

    @Test
    @DisplayName("Item count limit should not exceed valid value")
    void shouldThrowExceptionIfItemsCountExceedValidValue() {
        //We generated data because parser is mocked.
        when(packParser.load()).thenReturn(Collections.singletonList(new Pack(new BigDecimal(80),
                Arrays.asList(new Item(1, new BigDecimal(80), 80),
                        new Item(2, new BigDecimal(81), 80),
                        new Item(3, new BigDecimal(82), 80),
                        new Item(4, new BigDecimal(83), 80),
                        new Item(5, new BigDecimal(84), 80),
                        new Item(6, new BigDecimal(85), 80),
                        new Item(7, new BigDecimal(86), 80),
                        new Item(8, new BigDecimal(87), 80),
                        new Item(9, new BigDecimal(88), 80),
                        new Item(10, new BigDecimal(89), 80),
                        new Item(11, new BigDecimal(90), 80),
                        new Item(12, new BigDecimal(91), 80),
                        new Item(13, new BigDecimal(92), 80),
                        new Item(14, new BigDecimal(93), 80),
                        new Item(15, new BigDecimal(94), 80),
                        new Item(16, new BigDecimal(94), 80)))));

        assertThatThrownBy(() -> knapsackTemplate.executePacker())
                .isInstanceOf(APIException.class)
                .hasMessage("Pack item count exceeded.");
    }
}
