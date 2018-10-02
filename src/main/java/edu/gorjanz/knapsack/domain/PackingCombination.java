package edu.gorjanz.knapsack.domain;

import java.util.ArrayList;
import java.util.List;

public class PackingCombination {

    private List<PackingItem> packingItems;
    private int weightLimit;

    public PackingCombination() {}

    public PackingCombination(int weightLimit, List<PackingItem> packingItems) {
        this.packingItems = packingItems;
        this.weightLimit = weightLimit;
    }

    public List<PackingItem> getPackingItems() {
        return packingItems;
    }

    public void addPackingItem(PackingItem item) {
        if (packingItems == null) {
            packingItems = new ArrayList<>();
        }

        packingItems.add(item);
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(int weightLimit) {
        this.weightLimit = weightLimit;
    }

    @Override
    public String toString() {
        return "PackingCombination{" +
                "packingItems=" + packingItems.toString() +
                ", weightLimit=" + weightLimit +
                '}';
    }
}
