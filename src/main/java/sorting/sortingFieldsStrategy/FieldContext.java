package sorting.sortingFieldsStrategy;

import model.Car;

public class FieldContext {

    private FieldStrategy fieldStrategy;

    public void setStrategy(FieldStrategy fieldStrategy) {
        this.fieldStrategy = fieldStrategy;
    }

    public int doStrategy(Car currentCar, Car otherCar) {
        return fieldStrategy.execute(currentCar, otherCar);
    }
}
