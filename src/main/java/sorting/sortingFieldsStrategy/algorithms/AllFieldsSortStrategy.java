package sorting.sortingFieldsStrategy.algorithms;

import model.Car;
import sorting.sortingFieldsStrategy.FieldStrategy;

public class AllFieldsSortStrategy implements FieldStrategy {

    @Override
    public int execute(Car currentCar, Car otherCar) {
        return currentCar.compareTo(otherCar);
    }
}
