package service;

import collection.CarList;

public class SortResult {
    private final CarList sortedCars;
    private final CarList additionalSortedCars;
    public SortResult(CarList sortedCars, CarList additionalSortedCars) {
        this.sortedCars = sortedCars;
        this.additionalSortedCars = additionalSortedCars;
    }
    public CarList getSortedCars() {
        return sortedCars;
    }
    public CarList getAdditionalSortedCars() {
        return additionalSortedCars;
    }
}