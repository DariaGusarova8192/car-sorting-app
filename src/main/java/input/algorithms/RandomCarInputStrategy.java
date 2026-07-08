package input.algorithms;

import builder.CarBuilder;
import collection.CarList;
import input.strategy.CarInputStrategy;
import model.Car;
import validation.CarValidator;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * Стратегия случайной генерации автомобилей.
 *
 * Этот класс отвечает за заполнение кастомной коллекции CarList
 * случайно созданными объектами Car.
 *
 * Используется паттерн Strategy:
 * данный класс является одной из возможных стратегий ввода данных.
 *
 * Также здесь используется Stream API для заполнения коллекции,
 * что закрывает дополнительное требование задания.
 */
public class RandomCarInputStrategy implements CarInputStrategy {
    private static final String[] MODELS = {
            "Toyota",
            "BMW",
            "Audi",
            "Lada",
            "Kia",
            "Hyundai",
            "Mercedes",
            "Volkswagen",
            "Skoda",
            "Ford"
    };

    private final int count;
    private final Random random;

    /**
     * @param count количество автомобилей, которое нужно создать
     */
    public RandomCarInputStrategy(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Количество автомобилей не может быть отрицательным");
        }

        this.count = count;
        this.random = new Random();
    }

    /**
     * Создаёт коллекцию автомобилей и заполняет её случайными данными.
     *
     * Важно:
     * используется кастомная коллекция CarList,
     * а заполнение выполняется через Stream API.
     */
    @Override
    public CarList inputCars() {
        CarList cars = new CarList(count);

        IntStream.range(0, count)
                .mapToObj(index -> createRandomCar())
                .forEach(cars::add);

        return cars;
    }

    /**
     * Возвращает название стратегии.
     *
     * Может использоваться в меню или при выводе информации пользователю.
     */
    @Override
    public String getStrategyName() {
        return "Случайная генерация";
    }

    /**
     * Создаёт один случайный автомобиль.
     *
     * Все значения берутся в допустимых диапазонах,
     * которые заданы в CarValidator.
     */
    private Car createRandomCar() {
        int power = randomInt(CarValidator.MIN_POWER, CarValidator.MAX_POWER);
        String model = MODELS[random.nextInt(MODELS.length)];
        int year = randomInt(CarValidator.MIN_YEAR, CarValidator.MAX_YEAR);

        return new CarBuilder()
                .setPower(power)
                .setModel(model)
                .setYear(year)
                .build();
    }

    /**
     * Возвращает случайное число в диапазоне от min до max включительно.
     */
    private int randomInt(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}