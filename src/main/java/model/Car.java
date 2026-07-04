package model;

import validation.CarValidator;

import java.util.Objects;

/**
 * Класс описывает автомобиль.
 *
 * Это основной кастомный класс проекта, который будет использоваться:
 * - при заполнении коллекции вручную;
 * - при чтении из файла;
 * - при случайной генерации;
 * - при сортировке;
 * - при поиске и подсчёте элементов.
 *
 * Поля сделаны final, чтобы объект автомобиля нельзя было изменить после создания.
 * Объект создаётся через паттерн Builder.
 */
public final class Car {
    private final int power;
    private final String model;
    private final int year;

    /**
     * Закрытый конструктор.
     *
     * Объект Car создаётся только через Builder.
     * Это сделано для выполнения требования задания по паттерну Builder.
     */
    private Car(Builder builder) {
        this.power = builder.power;
        this.model = builder.model.trim();
        this.year = builder.year;
    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    /**
     * Удобный способ создать Builder.
     *
     * Можно писать так:
     * Car car = Car.builder()
     *         .setPower(150)
     *         .setModel("Toyota")
     *         .setYear(2020)
     *         .build();
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder для создания объекта Car.
     *
     * Нужен, чтобы не создавать объект через длинный конструктор
     * и чтобы было понятно, какое значение в какое поле записывается.
     */
    public static class Builder {
        private int power;
        private String model;
        private int year;

        public Builder setPower(int power) {
            this.power = power;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        /**
         * Создаёт объект Car.
         *
         * Перед созданием вызывается валидация.
         * Если данные некорректные, будет выброшено исключение IllegalArgumentException.
         */
        public Car build() {
            CarValidator.validateOrThrow(power, model, year);
            return new Car(this);
        }
    }

    /**
     * Метод нужен для красивого вывода автомобиля в консоль.
     */
    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }

    /**
     * Метод нужен для сравнения автомобилей.
     *
     * Это пригодится в тестах и в многопоточном подсчёте количества вхождений элемента.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Car car = (Car) object;

        return power == car.power
                && year == car.year
                && Objects.equals(model, car.model);
    }

    /**
     * hashCode нужно переопределять вместе с equals.
     *
     * Это стандартное правило Java.
     */
    @Override
    public int hashCode() {
        return Objects.hash(power, model, year);
    }
}