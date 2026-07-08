package sorting.sortingAlgorithmsStrategy.algorithms;

import sorting.SortStrategiesTest;
import sorting.SortStrategyTestInterface;
import sorting.sortingAlgorithmsStrategy.Strategy;
import sorting.sortingFieldsStrategy.algorithms.AllFieldsSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.ModelSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.PowerSortStrategy;
import sorting.sortingFieldsStrategy.algorithms.YearSortStrategy;

public class QuickSortStrategyTest implements SortStrategyTestInterface {

    private final Strategy strategy = new QuickSortStrategy();

    @Override
    public void sortByPowerTest() {
        if (SortStrategiesTest.assertStrategiesTrue(strategy, new PowerSortStrategy())) {
            System.out.println("QuickSortStrategyTest by power field passed successfully");
        }
    }

    @Override
    public void sortByYearTest() {
        if(SortStrategiesTest.assertStrategiesTrue(strategy, new YearSortStrategy())) {
            System.out.println("QuickSortStrategyTest by year field passed successfully");
        }
    }

    @Override
    public void sortByModelTest() {
        if (SortStrategiesTest.assertStrategiesTrue(strategy, new ModelSortStrategy())) {
            System.out.println("QuickSortStrategyTest by model field passed successfully");
        }
    }

    @Override
    public void sortByAllFieldsTest() {
        if(SortStrategiesTest.assertStrategiesTrue(strategy, new AllFieldsSortStrategy())) {
            System.out.println("QuickSortStrategyTest by all fields passed successfully");
        }
    }

    @Override
    public void sortAdditionallyByPower() {
        if(SortStrategiesTest.assertAdditionalStrategiesTrue(strategy)) {
            System.out.println("Additional QuickSortStrategyTest by power field passed successfully");
        }
    }
}
