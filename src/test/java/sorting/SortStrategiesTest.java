package sorting;

import builder.CarBuilder;
import collection.CarList;
import model.Car;
import sorting.exception.ElementsNotCorrespondException;
import sorting.exception.SizeNotCorrespondException;
import sorting.sortingAlgorithmsStrategy.Context;
import sorting.sortingAlgorithmsStrategy.Strategy;
import sorting.sortingAlgorithmsStrategy.algorithms.BubbleSortStrategyTest;
import sorting.sortingAlgorithmsStrategy.algorithms.InsertionSortStrategyTest;
import sorting.sortingAlgorithmsStrategy.algorithms.QuickSortStrategyTest;
import sorting.sortingFieldsStrategy.FieldContext;
import sorting.sortingFieldsStrategy.FieldStrategy;

import java.util.ArrayList;
import java.util.List;

public class SortStrategiesTest {

    private static final CarBuilder carBuilder = new CarBuilder();

    public static void main(String... args) {
        SortStrategiesTest.doSortStrategyTest(new BubbleSortStrategyTest());
        SortStrategiesTest.doSortStrategyTest(new InsertionSortStrategyTest());
        SortStrategiesTest.doSortStrategyTest(new QuickSortStrategyTest());
    }


    public static CarList getCars() {
        CarList cars = new CarList(11);
        cars.add(carBuilder.setPower(52).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(5).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(3).setModel("Toyota").setYear(2001).build());
        cars.add(carBuilder.setPower(36).setModel("Nisan").setYear(2015).build());
        cars.add(carBuilder.setPower(36).setModel("Mazda").setYear(2017).build());
        cars.add(carBuilder.setPower(4).setModel("BMW").setYear(2007).build());
        cars.add(carBuilder.setPower(6).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(61).setModel("Geely").setYear(2005).build());
        cars.add(carBuilder.setPower(2).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(17).setModel("Nisan").setYear(2010).build());
        cars.add(carBuilder.setPower(26).setModel("Ford").setYear(2014).build());
        return cars;
    }

    public static CarList getExpectedForPowerStrategy() {
        CarList cars = new CarList(11);
        cars.add(carBuilder.setPower(2).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(3).setModel("Toyota").setYear(2001).build());
        cars.add(carBuilder.setPower(4).setModel("BMW").setYear(2007).build());
        cars.add(carBuilder.setPower(5).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(6).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(17).setModel("Nisan").setYear(2010).build());
        cars.add(carBuilder.setPower(26).setModel("Ford").setYear(2014).build());
        cars.add(carBuilder.setPower(36).setModel("Nisan").setYear(2015).build());
        cars.add(carBuilder.setPower(36).setModel("Mazda").setYear(2017).build());
        cars.add(carBuilder.setPower(52).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(61).setModel("Geely").setYear(2005).build());
        return cars;
    }

    public static CarList getExpectedForYearStrategy() {
        CarList cars = new CarList(11);
        cars.add(carBuilder.setPower(3).setModel("Toyota").setYear(2001).build());
        cars.add(carBuilder.setPower(52).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(5).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(6).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(61).setModel("Geely").setYear(2005).build());
        cars.add(carBuilder.setPower(2).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(4).setModel("BMW").setYear(2007).build());
        cars.add(carBuilder.setPower(17).setModel("Nisan").setYear(2010).build());
        cars.add(carBuilder.setPower(26).setModel("Ford").setYear(2014).build());
        cars.add(carBuilder.setPower(36).setModel("Nisan").setYear(2015).build());
        cars.add(carBuilder.setPower(36).setModel("Mazda").setYear(2017).build());
        return cars;
    }

    public static CarList getExpectedForModelStrategy() {
        CarList cars = new CarList(11);
        cars.add(carBuilder.setPower(4).setModel("BMW").setYear(2007).build());
        cars.add(carBuilder.setPower(6).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(2).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(26).setModel("Ford").setYear(2014).build());
        cars.add(carBuilder.setPower(61).setModel("Geely").setYear(2005).build());
        cars.add(carBuilder.setPower(52).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(5).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(36).setModel("Mazda").setYear(2017).build());
        cars.add(carBuilder.setPower(36).setModel("Nisan").setYear(2015).build());
        cars.add(carBuilder.setPower(17).setModel("Nisan").setYear(2010).build());
        cars.add(carBuilder.setPower(3).setModel("Toyota").setYear(2001).build());
        return cars;
    }

    public static CarList getExpectedForAllFieldsStrategy() {
        CarList cars = new CarList(11);
        cars.add(carBuilder.setPower(2).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(6).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(4).setModel("BMW").setYear(2007).build());
        cars.add(carBuilder.setPower(26).setModel("Ford").setYear(2014).build());
        cars.add(carBuilder.setPower(61).setModel("Geely").setYear(2005).build());
        cars.add(carBuilder.setPower(5).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(52).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(36).setModel("Mazda").setYear(2017).build());
        cars.add(carBuilder.setPower(17).setModel("Nisan").setYear(2010).build());
        cars.add(carBuilder.setPower(36).setModel("Nisan").setYear(2015).build());
        cars.add(carBuilder.setPower(3).setModel("Toyota").setYear(2001).build());
        return cars;
    }

    public static CarList getExpectedForAdditionalPowerStrategy() {
        CarList cars = new CarList(11);
        cars.add(carBuilder.setPower(2).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(5).setModel("Kia").setYear(2004).build());
        cars.add(carBuilder.setPower(3).setModel("Toyota").setYear(2001).build());
        cars.add(carBuilder.setPower(4).setModel("BMW").setYear(2007).build());
        cars.add(carBuilder.setPower(6).setModel("BMW").setYear(2005).build());
        cars.add(carBuilder.setPower(26).setModel("Ford").setYear(2014).build());
        cars.add(carBuilder.setPower(36).setModel("Nisan").setYear(2015).build());
        cars.add(carBuilder.setPower(61).setModel("Geely").setYear(2005).build());
        cars.add(carBuilder.setPower(36).setModel("Mazda").setYear(2017).build());
        cars.add(carBuilder.setPower(17).setModel("Nisan").setYear(2010).build());
        cars.add(carBuilder.setPower(52).setModel("Kia").setYear(2004).build());
        return cars;
    }

    private static boolean assertEquals(List<Car> cars, List<Car> expected) {
        if(cars.size() != expected.size()) {
            throw new SizeNotCorrespondException("SortTest is failed");
        } else {
            for (int i = 0; i < cars.size(); i++) {
                if (!cars.get(i).equals(expected.get(i))) {
                    throw new ElementsNotCorrespondException("SortTest is failed");
                }
            }
        }
        return true;
    }

    private static boolean assertTrue(CarList cars, FieldContext context) {
        for(int i = 1; i < cars.size(); i++) {
            if(context.doStrategy(cars.get(i - 1), cars.get(i)) > 0) {
                throw new ElementsNotCorrespondException("SortTest is failed");
            }
        }
        return true;
    }

    private static boolean assertTrueForAdditional(CarList cars) {
        ArrayList<Integer> indexesForCheck = new ArrayList<>();
        for(int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getPower() % 2 != 0) {
                if(cars.get(i).compareTo(SortStrategiesTest.getCars().get(i)) != 0) {
                    throw new ElementsNotCorrespondException("SortTest is failed");
                }
            } else {
                indexesForCheck.add(i);
            }
        }
        for (int i = 1; i < indexesForCheck.size(); i++) {
            if(cars.get(indexesForCheck.get(i - 1)).getPower() > cars.get(indexesForCheck.get(i)).getPower()) {
                throw new ElementsNotCorrespondException("SortTest is failed");
            }
        }
        return true;
    }

    public static boolean assertStrategiesEqual(Strategy strategy, FieldStrategy fieldStrategy, List<Car> expected) {

        Context context = new Context();
        FieldContext fieldContext = new FieldContext();
        fieldContext.setStrategy(fieldStrategy);

        context.setStrategy(strategy);
        List<Car> testArray = context.doStrategy(SortStrategiesTest.getCars(), fieldContext);

        return SortStrategiesTest.assertEquals(testArray, expected);
    }

    public static boolean assertStrategiesTrue(Strategy strategy, FieldStrategy fieldStrategy) {

        Context context = new Context();
        FieldContext fieldContext = new FieldContext();
        fieldContext.setStrategy(fieldStrategy);

        context.setStrategy(strategy);
        List<Car> testArray = context.doStrategy(SortStrategiesTest.getCars(), fieldContext);

        return SortStrategiesTest.assertTrue((CarList)testArray, fieldContext);
    }

    public static boolean assertAdditionalStrategiesEqual(Strategy strategy, List<Car> expected) {

        Context context = new Context();
        context.setStrategy(strategy);

        List<Car> testArray = context.doAdditionalStrategy(SortStrategiesTest.getCars());

        return SortStrategiesTest.assertEquals(testArray, expected);
    }

    public static boolean assertAdditionalStrategiesTrue(Strategy strategy) {

        Context context = new Context();
        context.setStrategy(strategy);

        List<Car> testArray = context.doAdditionalStrategy(SortStrategiesTest.getCars());

        return SortStrategiesTest.assertTrueForAdditional((CarList) testArray);
    }

    public static void doSortStrategyTest(SortStrategyTestInterface strategyTest) {
        strategyTest.sortByAllFieldsTest();
        strategyTest.sortByModelTest();
        strategyTest.sortByPowerTest();
        strategyTest.sortByYearTest();
        strategyTest.sortAdditionallyByPower();
    }
}
