package com.mobiquityinc.packer.knapsack;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Pack;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * A 0-1 Knapsack Dynamic Programming based implementation of {@link KnapsackSolverStrategy}.
 */

public class DynamicKnapsackSolver implements KnapsackSolverStrategy {
    /**
     * <p>Since in its traditional definition, the 0-1 Knapsack weight are integers, we have to first convert the decimal
     * weights to integral ones in order to use the classical solution. Here we assumed that the given weights have at
     * most 2 decimal places. Therefore we multiply all weights to 100 and cast them to integers. With this assumption,
     * the time and space complexity of the algorithm would be:
     * <pre>
     *     O(100nW) = O(nW)
     * </pre>
     * Here we're going to stick with 2 decimal places but it's also possible to scan all weights and multiply the
     * weights with the maximum possible power of ten to avoid wrong answers due to lost precisions.
     **/
    @Override
    public List<Item> solve(Pack pack) {
        int precision = 100; // 10 to the power of 2. That is, at most 2 decimal places.

        Item[] items = pack.getItems().stream()
                .map(i -> new Item(i.getIndex(), i.getWeight().multiply(new BigDecimal(precision)), i.getCost())).
                        toArray(Item[]::new);
        BigDecimal maxWeight = pack.getWeight().multiply(new BigDecimal(precision));
        // Items sorted because would prefer to send a package which weighs less in case there
        // is more than one package with the same price.
        // And sort for another requirement for selection on same cost.

        Arrays.sort(items, Comparator.comparing(Item::getCost).thenComparing(Item::getWeight));

        int[][] mementoTable = new int[pack.getItems().size() + 1][maxWeight.intValue() + 1];

        //set fist row of table to zero
        for (int weight = 0; weight < maxWeight.intValue(); weight++) {
            mementoTable[0][weight] = 0;
        }
        //loops through the item numbers
        for (int currentItemIndex = 1; currentItemIndex <= pack.getItems().size(); currentItemIndex++) {
            //loops through the item weights - finds the optimal solution for the given item and the given weight in the array
            for (int currentCapacity = 0; currentCapacity <= maxWeight.intValue(); currentCapacity++) {

                if (items[currentItemIndex - 1].getWeight().intValue() > currentCapacity) {
                    mementoTable[currentItemIndex][currentCapacity] = mementoTable[currentItemIndex - 1][currentCapacity];
                } else {
                    mementoTable[currentItemIndex][currentCapacity] = Math.max(mementoTable[currentItemIndex - 1][currentCapacity],
                            mementoTable[currentItemIndex - 1][currentCapacity - items[currentItemIndex - 1].getWeight().intValue()] + items[currentItemIndex - 1].getCost());
                }
            }
        }

        /*
         *Iterate the matrix , looking for items to include in solution
         * iterate from max cost because items sorted by cost and then weight.
         */

        List<Item> result = new ArrayList<>();
        int maxCost = mementoTable[pack.getItems().size()][maxWeight.intValue()];

        for (int i = pack.getItems().size(); i > 0 && maxCost > 0; i--) {
            if (maxCost != mementoTable[i][maxWeight.intValue()]) {
                result.add(items[i]);
                maxCost -= items[i].getCost();
            }
        }
        //This sort for pretty representation based of example so can be removed.
        result.sort(Comparator.comparing(Item::getIndex));
        return result;
    }
}
