package model;

import java.util.Objects;

/**
 * Класс описывает автомобиль.
 *
 * Это основной кастомный класс проекта.
 * Объекты этого класса используются:
 * - при ручном вводе;
 * - при чтении из файла;
 * - при случайной генерации;
 * - при сортировке;
 * - при поиске и подсчёте элементов.
 *
 * Поля сделаны final, чтобы объект автомобиля нельзя было изменить после создания.
 *
 * ВАЖНО:
 * В основном коде проекта объект Car рекомендуется создавать через CarBuilder.
 * Сам Builder вынесен в отдельный класс:
 * builder.CarBuilder
 */
public final class Car implements Comparable<Car>{
    private final int power;
    private final String model;
    private final int year;

    /**
     * Конструктор автомобиля.
     *
     * В обычном коде проекта лучше использовать не конструктор напрямую,
     * а CarBuilder, потому что в Builder выполняется проверка данных.
     */
    public Car(int power, String model, int year) {
        this.power = power;
        this.model = model == null ? null : model.trim();
        this.year = year;
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
     * Это пригодится:
     * - в тестах;
     * - при поиске элемента;
     * - при многопоточном подсчёте количества вхождений элемента.
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

    // для сортировки
    @Override
    public int compareTo(Car atherCar) {
        //model, year, power
        if(model.compareTo(atherCar.model) < 0) {
            return -1;
        }
        if(model.compareTo(atherCar.model) > 0) {
            return 1;
        }
        if(year < atherCar.year) {
            return -1;
        }
        if(year > atherCar.year) {
            return 1;
        }
        return Integer.compare(power, atherCar.getPower());
    }

    // для сортировки
    public int compareByPower(Car atherCar) {
        return Integer.compare(power, atherCar.getPower());
    }

    // для сортировки
    public int compareByModel(Car atherCar) {
        return model.compareTo(atherCar.getModel());
    }

    // для сортировки
    public int compareByYear(Car atherCar) {
        return Integer.compare(year, atherCar.getYear());
    }
}