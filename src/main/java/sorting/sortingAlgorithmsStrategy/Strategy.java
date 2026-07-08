package sorting.sortingAlgorithmsStrategy;

import collection.CarList;
import model.Car;
import sorting.sortingFieldsStrategy.FieldContext;

import java.util.List;

public interface Strategy {

    List<Car> execute(CarList cars, FieldContext context);
    List<Car> executeAdditionalSortByPower(CarList cars);

    public static CarList getArrayCopy(CarList cars) {
        CarList carsCopy = new CarList();
        for(int i = 0; i < cars.size(); i++) {
            carsCopy.add(i, cars.get(i));
        }
        return carsCopy;
    }
}
