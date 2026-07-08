package sorting.sortingFieldsStrategy.algorithms;

import model.Car;
import sorting.sortingFieldsStrategy.FieldStrategy;

public class PowerSortStrategy implements FieldStrategy {

    @Override
    public int execute(Car currentCar, Car atherCar) {
        return currentCar.compareByPower(atherCar);
    }
}
