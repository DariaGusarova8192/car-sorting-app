package sorting.sortingAlgorithmsStrategy.algorithms;


import collection.CarList;
import model.Car;
import sorting.sortingAlgorithmsStrategy.Strategy;
import sorting.sortingFieldsStrategy.FieldContext;

import java.util.List;

public class BubbleSortStrategy implements Strategy {

    @Override
    public List<Car> execute(CarList cars, FieldContext context) {
        CarList carsCopy = Strategy.getArrayCopy(cars);

        Car bubble;
        boolean needIteration = true;

        while (needIteration){
            needIteration = false;

            for (int i = 0; i < carsCopy.size() - 1; i++) {
                if (context.doStrategy(carsCopy.get(i), carsCopy.get(i + 1)) > 0) {
                    bubble = carsCopy.get(i);
                    carsCopy.set(i, carsCopy.get(i + 1));
                    carsCopy.set(i + 1, bubble);
                    needIteration = true;
                }
            }
        }
        return carsCopy;
    }

    @Override
    public List<Car> executeAdditionalSortByPower(CarList cars) {
        CarList carsCopy = Strategy.getArrayCopy(cars);

        Car bubble;
        boolean needIteration = true;

        while (needIteration){
            needIteration = false;

            int i = 0;
            while (i < carsCopy.size() - 1 && carsCopy.get(i).getPower() % 2 != 0) {
                i++;
            }
            int j = i + 1;
            while (j < carsCopy.size()) {
                while (carsCopy.get(j).getPower() % 2 != 0) {
                    j++;
                }
                if (carsCopy.get(i).compareByPower(carsCopy.get(j)) > 0) {
                    bubble = carsCopy.get(i);
                    carsCopy.set(i, carsCopy.get(j));
                    carsCopy.set(j, bubble);
                    needIteration = true;
                }
                i = j;
                j++;
            }
        }
        return carsCopy;
    }
}
