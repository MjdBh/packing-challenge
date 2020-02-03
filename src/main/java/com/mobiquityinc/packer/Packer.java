package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;

/**
 * Reads a few lines from the given filepath and tries to solve packing problems in each line.
 */
public class Packer {
    /**
     * Don't instantiate this class.
     */
    private Packer() {
    }

    /**
     * Reads each line from the given file, extracts the Knapsack problem inputs from them
     * and solves each problem.
     *
     * @param filePath The path to the file.
     * @return A comma-separate collection of answers in each line. The '-' means there are
     * no answers for this particular problem.
     * @throws APIException When the given input is invalid.
     */
    public static String pack(String filePath) throws APIException {
        PackerTemplate packerTemplate = new PackerTemplate(filePath);
        return packerTemplate.executePacker();
    }
}
