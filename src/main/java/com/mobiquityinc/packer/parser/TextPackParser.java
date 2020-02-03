package com.mobiquityinc.packer.parser;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Pack;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This parser is a implementation of {@link PackParserStrategy} for load items from text file.
 */
public class TextPackParser implements PackParserStrategy {
    /**
     * A pattern to extract the item data with the following format:
     * <pre>
     *     (*) (*) ...
     * </pre>
     */
    private static final Pattern ITEMS_PATTERN = Pattern.compile("\\((.*?)\\)");
    /**
     * Path of file that contain packs
     */
    private final String filePath;

    @Override
    public List<Pack> load() {
        try (Stream<String> input = Files.lines(Paths.get(filePath))) {
            return input.map(this::convertLineToPack).collect(Collectors.toList());
        } catch (IOException e) {
            throw new APIException("Invalid file in given path", e);
        }
    }

    public TextPackParser(String filePath) {
        this.filePath = filePath;
    }

    private  List<Item> extractItems(String itemPart) throws APIException {
        List<Item> items = new ArrayList<>();
        Matcher matcher = ITEMS_PATTERN.matcher(itemPart);

        while (matcher.find()) {
            String insideParentheses = matcher.group(1);
            String[] itemData = insideParentheses.split(",", 3);
            if (itemData.length != 3)
                throw new APIException("Unexpected items format: " + insideParentheses);

            try {
                int index = Integer.parseInt(itemData[0].trim());
                BigDecimal weight = new BigDecimal(itemData[1].trim());
                int cost = Integer.parseInt(itemData[2].trim().substring(1));

                items.add(new Item(index, weight, cost));
            } catch (NumberFormatException e) {
                throw new APIException("Item data should be a valid number: " + insideParentheses, e);
            } catch (IndexOutOfBoundsException e) {
                throw new APIException("Item cost should be in valid format: " + itemData[2], e);
            }
        }
        return items;
    }

    private Pack convertLineToPack(String line) {
        String[] parts = line.trim().split(":", 2);
        if (parts.length != 2) throw new APIException("Unexpected line format: " + line);

        BigDecimal weightLimit = extractWeightLimit(parts[0]);
        List<Item> items = extractItems(parts[1]);

        return new Pack(weightLimit, items);
    }

    private  BigDecimal extractWeightLimit(String number) {
        return new BigDecimal(number.trim());
    }
}
