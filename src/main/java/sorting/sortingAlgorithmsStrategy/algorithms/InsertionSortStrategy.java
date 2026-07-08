package sorting.sortingAlgorithmsStrategy.algorithms;

import collection.CarList;
import model.Car;
import sorting.sortingAlgorithmsStrategy.Strategy;
import sorting.sortingFieldsStrategy.FieldContext;

import java.util.ArrayList;
import java.util.List;

public class InsertionSortStrategy implements Strategy {

    @Override
    public List<Car> execute(CarList cars, FieldContext context) {
        CarList carsCopy = new CarList(cars);

        Car valueForInsert;
        int j;
        for (int i = 1; i < carsCopy.size(); i++) {
            valueForInsert = carsCopy.get(i);
            j = i - 1;
            for (; j >= 0; j--) {
                if (context.doStrategy(carsCopy.get(j), valueForInsert) > 0) {
                    carsCopy.set(j + 1,carsCopy.get(j));
                } else {
                    break;
                }
            }
            carsCopy.set(j + 1, valueForInsert);
        }
        return carsCopy;
    }

    @Override
    public List<Car> executeAdditionalSortByPower(CarList cars) {
        CarList carsCopy = new CarList(cars);

        ArrayList<Integer> indexes = new ArrayList<>();
        int i;
        int j = 0;
        int k;

        while(j < carsCopy.size() && carsCopy.get(j).getPower() % 2 != 0) {
            j++;
        }
        indexes.add(j);

        Car valueForInsert;
        for (i = j + 1; i < carsCopy.size(); i++) {
            if(carsCopy.get(i).getPower() % 2 != 0) {
                continue;
            }
            indexes.add(i);
            valueForInsert = carsCopy.get(i);
            for (k = indexes.size() - 2; k >= 0; k--) {
                if (carsCopy.get(indexes.get(k)).compareByPower(valueForInsert) > 0) {
                    carsCopy.set(indexes.get(k + 1),carsCopy.get(indexes.get(k)));
                } else {
                    break;
                }
            }
            carsCopy.set(indexes.get(k + 1), valueForInsert);
        }
        return carsCopy;
    }
}
