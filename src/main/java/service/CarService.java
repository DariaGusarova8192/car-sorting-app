package service;

import collection.CarList;
import input.CarInputService;
import input.algorithms.FileCarInputStrategy;
import input.algorithms.ManualCarInputStrategy;
import input.algorithms.RandomCarInputStrategy;
import model.Car;
import output.FileOutput;
import sorting.sortingAlgorithmsStrategy.Context;
import sorting.sortingAlgorithmsStrategy.algorithms.BubbleSortStrategy;
import sorting.sortingAlgorithmsStrategy.algorithms.InsertionSortStrategy;
import sorting.sortingAlgorithmsStrategy.algorithms.QuickSortStrategy;
import sorting.sortingFieldsStrategy.FieldContext;
import sorting.sortingFieldsStrategy.algorithms.AllFieldsSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.ModelSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.PowerSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.YearSortStrategy;

import java.util.Scanner;

public class CarService {
    private CarList carList;
    private final Context sortingContext = new Context();
    private final FieldContext fieldContext = new FieldContext();
    private final MultiThreadCounter counter = new MultiThreadCounter();
    public boolean selectInputMethod(int selection, int amountOfData) {
        switch (selection){
            case 1:
                CarInputService fileInputService = new CarInputService(new FileCarInputStrategy("cars.txt"));
                carList = fileInputService.inputCars();
                return true;
            case 2:
                CarInputService manualInputService = new CarInputService(new ManualCarInputStrategy( new Scanner(System.in), amountOfData));
                carList = manualInputService.inputCars();
                return true;
            case 3:
                CarInputService randomInputService = new CarInputService(new RandomCarInputStrategy(amountOfData));
                carList = randomInputService.inputCars();
                return true;
            default:
                System.out.println("Вы ввели некорректный пункт меню");
                return false;
        }
    }
    public boolean selectSortingAlgorithm(int selection) {
        switch (selection) {
            case 1:
                sortingContext.setStrategy(new BubbleSortStrategy());
                return true;
            case 2:
                sortingContext.setStrategy(new QuickSortStrategy());
                return true;
            case 3:
                sortingContext.setStrategy(new InsertionSortStrategy());
                return true;
            default:
                System.out.println("Вы ввели некорректный пункт меню");
                return false;
        }
    }
    public boolean selectFieldForSorting(int selection) {
        switch (selection) {
            case 1:
                fieldContext.setStrategy(new PowerSortStrategy());
                return true;
            case 2:
                fieldContext.setStrategy(new ModelSortStrategy());
                return true;
            case 3:
                fieldContext.setStrategy(new YearSortStrategy());
                return true;
            case 4:
                fieldContext.setStrategy(new AllFieldsSortStrategy());
                return true;
            default:
                System.out.println("Вы ввели некорректный пункт меню");
                return false;
        }
    }
    public SortResult doSort() {
        CarList sortedCars = (CarList) sortingContext.doStrategy(carList, fieldContext);
        CarList additionalSortedCars = (CarList) sortingContext.doAdditionalStrategy(carList);
        return new SortResult(sortedCars, additionalSortedCars);
    }
    public CountResult doCountOccurrences(Car car, int countThreads) {
        return counter.countOccurrences(carList, car, countThreads);
    }
    public void sortingOutput(boolean isAppendMode) {
        FileOutput fileOutput = new FileOutput(isAppendMode);
        SortResult result = doSort();
        fileOutput.sortResultOutput(result);
    }
    public void searchingOutput(int power, String model, int year, int countThreads, boolean isAppendMode) {
        FileOutput fileOutput = new FileOutput(isAppendMode);
        Car carForSearch = compileCar(power, model, year);
        CountResult result = doCountOccurrences(carForSearch, countThreads);
        fileOutput.searchResultOutput(result);
    }
    private Car compileCar(int power, String model, int year) {
        return new Car(power, model, year);
    }
}