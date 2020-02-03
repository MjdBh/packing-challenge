package com.mobiquityinc.packer.parser;

import com.mobiquityinc.packer.model.Pack;

import java.util.List;

/*
 * Simple contract for every parser that read packs from every source such as file or network.
 */
@FunctionalInterface
public interface PackParserStrategy {

    /**
     * Load method used for loading packs form any source that this strategy implemented.
     * @return Collection of packs that exist in container.
     */
    List<Pack> load();
}
