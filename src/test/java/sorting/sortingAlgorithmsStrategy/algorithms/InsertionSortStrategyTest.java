package sorting.sortingAlgorithmsStrategy.algorithms;

import sorting.SortStrategiesTest;
import sorting.SortStrategyTestInterface;
import sorting.sortingAlgorithmsStrategy.Strategy;
import sorting.sortingFieldsStrategy.algorithms.AllFieldsSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.ModelSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.PowerSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.YearSortStrategy;

public class InsertionSortStrategyTest implements SortStrategyTestInterface {

    private final Strategy strategy = new InsertionSortStrategy();

    @Override
    public void sortByPowerTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new PowerSortStrategy(), SortStrategiesTest.getExpectedForPowerStrategy()))
        {
            System.out.println("InsertionSortStrategyTest by power field passed successfully");
        }
    }

    @Override
    public void sortByYearTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new YearSortStrategy(), SortStrategiesTest.getExpectedForYearStrategy())) {
            System.out.println("InsertionSortStrategyTest by year field passed successfully");
        }
    }

    @Override
    public void sortByModelTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new ModelSortStrategy(), SortStrategiesTest.getExpectedForModelStrategy())) {
            System.out.println("InsertionSortStrategyTest by model field passed successfully");
        }
    }

    @Override
    public void sortByAllFieldsTest() {
        if(SortStrategiesTest.assertStrategiesEqual(strategy, new AllFieldsSortStrategy(), SortStrategiesTest.getExpectedForAllFieldsStrategy())) {
            System.out.println("InsertionSortStrategyTest by all fields passed successfully");
        }
    }

    @Override
    public void sortAdditionallyByPower() {
        if(SortStrategiesTest.assertAdditionalStrategiesEqual(strategy, SortStrategiesTest.getExpectedForAdditionalPowerStrategy()))
        {
            System.out.println("Additional InsertionSortStrategyTest by power field passed successfully");
        }
    }
}
