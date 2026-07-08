package sorting.sortingAlgorithmsStrategy;

import collection.CarList;
import model.Car;
import sorting.sortingFieldsStrategy.FieldContext;

import java.util.List;

public interface Strategy {

    List<Car> execute(CarList cars, FieldContext context);
    List<Car> executeAdditionalSortByPower(CarList cars);
}
