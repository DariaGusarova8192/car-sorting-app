package sorting.sortingFieldsStrategy.algorithms;

import model.Car;
import sorting.sortingFieldsStrategy.FieldStrategy;

public class YearSortStrategy implements FieldStrategy {

    @Override
    public int execute(Car currentCar, Car otherCar) {
        return currentCar.compareByYear(otherCar);
    }
}
