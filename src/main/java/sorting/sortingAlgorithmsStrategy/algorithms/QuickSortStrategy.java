package sorting.sortingAlgorithmsStrategy.algorithms;

import collection.CarList;
import model.Car;
import sorting.sortingAlgorithmsStrategy.Strategy;
import sorting.sortingFieldsStrategy.FieldContext;
import sorting.sortingFieldsStrategy.algorithms.PowerSortStrategy;

import java.util.ArrayList;
import java.util.List;

public class QuickSortStrategy implements Strategy {

    @Override
    public List<Car> execute(CarList cars, FieldContext context) {
        CarList carsCopy = new CarList(cars);

        quickSort(carsCopy, 0, carsCopy.size() - 1, context);
        return carsCopy;
    }

    private static int partition(CarList cars, int low, int high, FieldContext context) {
        Car pivot = cars.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (context.doStrategy(cars.get(j), pivot) <= 0) {
                i++;

                Car temp = cars.get(i);
                cars.set(i, cars.get(j));
                cars.set(j, temp);
            }
        }

        Car temp = cars.get(i + 1);
        cars.set(i + 1, cars.get(high));
        cars.set(high, temp);

        return i + 1;
    }

    public static void quickSort(CarList cars, int low, int high, FieldContext context) {
        if (low < high) {
            int partitionResult = partition(cars, low, high, context);

            quickSort(cars, low,  partitionResult - 1, context);
            quickSort(cars, partitionResult + 1, high, context);
        }
    }

    @Override
    public List<Car> executeAdditionalSortByPower(CarList cars) {
        CarList carsCopy = new CarList(cars);

        ArrayList<Integer> indexes = new ArrayList<>();
        CarList carsForSorting = new CarList();
        for (int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getPower() % 2 == 0) {
                carsForSorting.add(cars.get(i));
                indexes.add(i);
            }
        }

        FieldContext context = new FieldContext();
        context.setStrategy(new PowerSortStrategy());
        CarList sortedCars = (CarList) execute(carsForSorting, context);
        for (int i = 0; i < sortedCars.size(); i++) {
            carsCopy.set(indexes.get(i), sortedCars.get(i));
        }
        return carsCopy;
    }
}