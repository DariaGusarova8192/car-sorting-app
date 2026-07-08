package sorting.sortingAlgorithmsStrategy.algorithms;

import sorting.SortStrategiesTest;
import sorting.SortStrategyTestInterface;
import sorting.sortingAlgorithmsStrategy.Strategy;
import sorting.sortingFieldsStrategy.algorithms.AllFieldsSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.ModelSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.PowerSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.YearSortStrategy;

public class BubbleSortStrategyTest implements SortStrategyTestInterface {

    private final Strategy strategy = new BubbleSortStrategy();

    @Override
    public void sortByPowerTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new PowerSortStrategy(), SortStrategiesTest.getExpectedForPowerStrategy())) {
            System.out.println("BubbleSortStrategyTest by power field passed successfully");
        }
    }

    @Override
    public void sortByYearTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new YearSortStrategy(), SortStrategiesTest.getExpectedForYearStrategy())) {
            System.out.println("BubbleSortStrategyTest by year field passed successfully");
        }
    }

    @Override
    public void sortByModelTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new ModelSortStrategy(), SortStrategiesTest.getExpectedForModelStrategy())) {
            System.out.println("BubbleSortStrategyTest by model field passed successfully");
        }
    }

    @Override
    public void sortByAllFieldsTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new AllFieldsSortStrategy(), SortStrategiesTest.getExpectedForAllFieldsStrategy())) {
            System.out.println("BubbleSortStrategyTest by all fields passed successfully");
        }
    }

    @Override
    public void sortAdditionallyByPower() {
        if(SortStrategiesTest.assertAdditionalStrategiesEqual(strategy, SortStrategiesTest.getExpectedForAdditionalPowerStrategy())) {
            System.out.println("Additional BubbleSortStrategyTest by power field passed successfully");
        }
    }
}
