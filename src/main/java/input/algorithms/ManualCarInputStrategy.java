package input.algorithms;

import builder.CarBuilder;
import collection.CarList;
import input.strategy.CarInputStrategy;
import model.Car;
import validation.CarValidator;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Стратегия ручного ввода автомобилей.
 *
 * Этот класс позволяет пользователю вручную заполнить коллекцию CarList.
 *
 * Пользователь вводит:
 * - мощность автомобиля;
 * - модель автомобиля;
 * - год производства.
 *
 * Перед созданием объекта Car данные проходят валидацию через CarValidator.
 *
 * Заполнение коллекции выполняется через Stream API,
 * что закрывает дополнительное требование задания.
 */
public class ManualCarInputStrategy implements CarInputStrategy {
    private final Scanner scanner;
    private final int count;

    /**
     * @param scanner объект Scanner для чтения данных из консоли
     * @param count количество автомобилей, которое нужно ввести
     */
    public ManualCarInputStrategy(Scanner scanner, int count) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner не должен быть null");
        }

        if (count < 0) {
            throw new IllegalArgumentException("Количество автомобилей не может быть отрицательным");
        }

        this.scanner = scanner;
        this.count = count;
    }

    /**
     * Заполняет кастомную коллекцию CarList автомобилями,
     * которые пользователь вводит вручную.
     */
    @Override
    public CarList inputCars() {
        CarList cars = new CarList(count);

        IntStream.range(0, count)
                .mapToObj(index -> inputOneCar(index + 1))
                .forEach(cars::add);

        return cars;
    }

    /**
     * Возвращает название стратегии.
     */
    @Override
    public String getStrategyName() {
        return "Ручной ввод";
    }

    /**
     * Вводит один автомобиль.
     *
     * Если пользователь ввёл некорректные данные,
     * метод выводит ошибку и просит повторить ввод.
     */
    private Car inputOneCar(int number) {
        while (true) {
            System.out.println();
            System.out.println("Введите данные автомобиля №" + number);

            int power = readInt("Мощность: ");
            String model = readString("Модель: ");
            int year = readInt("Год производства: ");

            String validationError = CarValidator.getValidationError(power, model, year);

            if (validationError == null) {
                return new CarBuilder()
                        .setPower(power)
                        .setModel(model)
                        .setYear(year)
                        .build();
            }

            System.out.println("Ошибка ввода: " + validationError);
            System.out.println("Повторите ввод автомобиля.");
        }
    }

    /**
     * Считывает целое число из консоли.
     *
     * Если пользователь ввёл не число,
     * метод не падает, а просит повторить ввод.
     */
    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException exception) {
                System.out.println("Ошибка: нужно ввести целое число.");
            }
        }
    }

    /**
     * Считывает строку из консоли.
     */
    private String readString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}