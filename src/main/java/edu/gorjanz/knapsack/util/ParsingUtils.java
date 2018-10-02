package edu.gorjanz.knapsack.util;


import edu.gorjanz.knapsack.domain.PackingCombination;
import edu.gorjanz.knapsack.domain.PackingItem;
import edu.gorjanz.knapsack.exception.APIException;

import java.util.ArrayList;
import java.util.List;

public class ParsingUtils {

    public static final String NO_WEIGHT_LIMIT_SUPPLIED = "No weight limit supplied for the test case.";
    public static final String INCORRECT_PACKING_ITEM_SUPPLIED = "One or more of the packing items are not correctly supplied.";

    /**
     * Creates a {@link PackingCombination} instance to be supplied to the Solver.
     *
     * @param line describing the test case parameters in the following form:
     *             "<weight-limit> : (<index>, <weight>, €<value>) (<index>, <weight>, €<value>) ..."
     * @return a PackingCombination instance ready to be processed.
     * @throws APIException in the case when the input is not according to the contract.
     */
    public static PackingCombination parseCombination(String line) throws APIException {
        int colonIndex = line.indexOf(":");

        if (colonIndex == -1) {

            throw new APIException(NO_WEIGHT_LIMIT_SUPPLIED);
        }

        int weightLimit;
        try {
            weightLimit = Integer.valueOf(line.substring(0, colonIndex).trim());
        } catch (NumberFormatException e) {
            throw new APIException(NO_WEIGHT_LIMIT_SUPPLIED);
        }

        String itemsDefinitions = line.substring(colonIndex);
        List<PackingItem> packingItems = new ArrayList<>();
        int length = itemsDefinitions.length();
        for (int i = 0; i < length; i++) {

            if (itemsDefinitions.charAt(i) == '(') {
                int enclosing = itemsDefinitions.indexOf(")", i);
                int firstComma = itemsDefinitions.indexOf(",", i);
                int secondComma = itemsDefinitions.indexOf(",", firstComma + 1);

                if (enclosing == -1 || firstComma == -1 || secondComma == -1) {
                    throw new APIException(INCORRECT_PACKING_ITEM_SUPPLIED);
                }

                try {
                    int index = Integer.valueOf(itemsDefinitions.substring(i + 1, firstComma));
                    int weight = Integer.valueOf(itemsDefinitions.substring(firstComma + 1, secondComma));
                    int value = Integer.valueOf(itemsDefinitions.substring(secondComma + 1, enclosing).replace("€", ""));

                    packingItems.add(new PackingItem(index, weight, value));
                } catch (NumberFormatException e) {
                    throw new APIException(INCORRECT_PACKING_ITEM_SUPPLIED);
                }

                i = enclosing;
            }
        }

        return new PackingCombination(weightLimit, packingItems);
    }
}
