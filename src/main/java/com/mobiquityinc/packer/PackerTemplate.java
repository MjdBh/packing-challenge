package com.mobiquityinc.packer;

import com.mobiquityinc.exception.*;
import com.mobiquityinc.packer.knapsack.DynamicKnapsackSolver;
import com.mobiquityinc.packer.knapsack.KnapsackSolverStrategy;
import com.mobiquityinc.packer.model.Pack;
import com.mobiquityinc.packer.parser.PackParserStrategy;
import com.mobiquityinc.packer.parser.TextPackParser;
import com.mobiquityinc.packer.presenter.ItemIndexPresenter;
import com.mobiquityinc.packer.presenter.ItemPresenterStrategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.toMap;

/**
 * A simple concrete template class that accept required strategies and execute process of algorithm
 * that at first load items by{@link PackParserStrategy} then validate items and solved problem
 * by {@link KnapsackSolverStrategy} and at the end present result by {@link ItemPresenterStrategy}.
 */
public final class PackerTemplate {

    private static final BigDecimal MAX_PACK_WEIGHT = new BigDecimal(100L);
    private static final int MAX_ITEM_COST = 100;
    private static final BigDecimal MAX_ITEM_WEIGHT = new BigDecimal(100L);
    private static final int MAX_ITEMS_IN_PACK = 15;

    private final PackParserStrategy packParser;
    private final KnapsackSolverStrategy solver;
    private final ItemPresenterStrategy itemPresenter;

    /**
     *
     * @param packParser parser that parse input and convert to {@link Pack}
     * @param solver Knapsack solver strategy
     * @param itemPresenter representation of solved problem.
     */
    public PackerTemplate(PackParserStrategy packParser, KnapsackSolverStrategy solver, ItemPresenterStrategy itemPresenter) {
        this.packParser = packParser;
        this.solver = solver;
        this.itemPresenter = itemPresenter;
    }

    /**
     * This constructor used default Strategies based on requested requirement.
     * @param filePath
     */
    public PackerTemplate(String filePath) {

        packParser = new TextPackParser(filePath);
        itemPresenter = new ItemIndexPresenter();
        solver = new DynamicKnapsackSolver();
    }

    /**
     * Executor method on template that run all process
     * @return result as string that produce by {@link ItemPresenterStrategy}
     */

    public String executePacker() {

        List<Pack> packs = packParser.load();
        packs.parallelStream().forEach(this::validate);

        return IntStream.range(0, packs.size())
                .boxed()
                .collect(toMap(i -> i, packs::get)).
                        entrySet().stream().
                        collect(Collectors.toMap(Map.Entry::getKey, e -> solver.solve(e.getValue()))).
                        entrySet().stream().
                        sorted(comparingByKey()).
                        map(Map.Entry::getValue).
                        map(itemPresenter::present).
                        collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Validation for constraints done by this methods.
     * @param pack that must be validate based on constraint
     */
    private void validate(Pack pack) {

        if (MAX_PACK_WEIGHT.compareTo(pack.getWeight()) < 0)
            throw new APIException("Pack weight exceeded.", new MaxPackWeightException());

        if (MAX_ITEMS_IN_PACK < pack.getItems().size())
            throw new APIException("Pack item count exceeded.", new MaxItemInPackException());

        pack.getItems().forEach(i -> {
            if (i.getCost() > MAX_ITEM_COST)
                throw new APIException("Item cost exceeded.", new MaxItemCostException());
            if (MAX_ITEM_WEIGHT.compareTo(i.getWeight()) < 0)
                throw new APIException("Item weight exceeded.", new MaxItemWeightException());
        });
    }
}
