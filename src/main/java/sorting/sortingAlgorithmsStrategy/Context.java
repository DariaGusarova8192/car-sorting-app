package sorting.sortingAlgorithmsStrategy;

import collection.CarList;
import model.Car;
import sorting.sortingFieldsStrategy.FieldContext;

import java.util.List;

public class Context {

    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Car> doStrategy(CarList cars, FieldContext context) {
        return strategy.execute(cars, context);
    }
    public List<Car> doAdditionalStrategy(CarList cars) {
        return strategy.executeAdditionalSortByPower(cars);
    }
}
