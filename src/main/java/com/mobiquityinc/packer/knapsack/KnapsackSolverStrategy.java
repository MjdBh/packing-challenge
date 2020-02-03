package com.mobiquityinc.packer.knapsack;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Pack;

import java.util.List;

/**
 * A simple contract for different Knapsack implementations. Basically, given a collection of
 * {@link Item}s, we're going pick a few of them in way that:
 * <ul>
 *     <li>The total weight of all selected items is less than or equal to a maximum weight.</li>
 *     <li>The total cost is the maximum possible.</li>
 * </ul>
 * Even though it's possible to pick one item both entirely or partially, it's totally up the implementor's
 * discretion. That is, we may have different implementations for 0-1 Knapsack and Fractional Knapsack
 * algorithms.
 *
 */
@FunctionalInterface
public interface KnapsackSolverStrategy {

/**
 * Given a collection of items, we're going a pick a few of them in a way that we maximize the total
 * cost while the total weight is less than or equal to {@code maximumWeight}. When we can't pick any
 * item or there is nothing to pick, we should return an empty {@link java.util.Optional} value.
 * Moreover, the implementations should never throw exceptions and only communicate their result with
 * the actual return values.
 *
 * @param pack         pack contain items and max weight.
 * @return collection of items that picked from items.
 */
    List<Item> solve(Pack pack);
}
