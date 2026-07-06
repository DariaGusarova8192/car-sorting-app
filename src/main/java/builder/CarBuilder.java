package builder;

import model.Car;
import validation.CarValidator;

/**
 * Builder для создания объекта Car.
 *
 * Это отдельная реализация паттерна Builder.
 * Класс нужен, чтобы удобно и понятно создавать автомобиль,
 * не используя длинный конструктор.
 *
 * Пример использования:
 *
 * Car car = new CarBuilder()
 *         .setPower(150)
 *         .setModel("Toyota")
 *         .setYear(2020)
 *         .build();
 */
public class CarBuilder {
    private int power;
    private String model;
    private int year;

    public CarBuilder setPower(int power) {
        this.power = power;
        return this;
    }

    public CarBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public CarBuilder setYear(int year) {
        this.year = year;
        return this;
    }

    /**
     * Создаёт объект Car.
     *
     * Перед созданием выполняется валидация.
     * Если данные некорректные, будет выброшено исключение IllegalArgumentException.
     */
    public Car build() {
        CarValidator.validateOrThrow(power, model, year);
        return new Car(power, model, year);
    }
}