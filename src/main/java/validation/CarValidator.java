package validation;

import model.Car;

import java.time.Year;

/**
 * Класс отвечает за проверку данных автомобиля.
 *
 * Используется:
 * - перед созданием объекта Car через Builder;
 * - при ручном вводе;
 * - при чтении данных из файла;
 * - при случайной генерации данных;
 * - в тестах.
 *
 * Все методы сделаны static, потому что валидатор не хранит состояние.
 */
public final class CarValidator {
    public static final int MIN_POWER = 1;
    public static final int MAX_POWER = 2000;

    public static final int MIN_YEAR = 1886;
    public static final int MAX_YEAR = Year.now().getValue();

    public static final int MIN_MODEL_LENGTH = 1;
    public static final int MAX_MODEL_LENGTH = 50;

    private CarValidator() {
        // Закрытый конструктор запрещает создавать объект CarValidator.
        // Используем только static-методы.
    }

    /**
     * Проверяет уже созданный объект Car.
     */
    public static boolean isValid(Car car) {
        if (car == null) {
            return false;
        }

        return isValid(car.getPower(), car.getModel(), car.getYear());
    }

    /**
     * Проверяет отдельные поля автомобиля.
     *
     * Этот метод удобно использовать до создания объекта Car,
     * например при вводе данных пользователем или чтении строки из файла.
     */
    public static boolean isValid(int power, String model, int year) {
        return isPowerValid(power)
                && isModelValid(model)
                && isYearValid(year);
    }

    /**
     * Проверяет данные и выбрасывает исключение, если данные некорректные.
     *
     * Этот метод используется в Builder перед созданием объекта Car.
     */
    public static void validateOrThrow(int power, String model, int year) {
        String error = getValidationError(power, model, year);

        if (error != null) {
            throw new IllegalArgumentException(error);
        }
    }

    /**
     * Возвращает текст ошибки.
     *
     * Если данные корректные, возвращает null.
     * Этот метод будет удобен для UI/меню, чтобы показать пользователю причину ошибки.
     */
    public static String getValidationError(int power, String model, int year) {
        if (!isPowerValid(power)) {
            return "Мощность автомобиля должна быть от " + MIN_POWER + " до " + MAX_POWER;
        }

        if (!isModelValid(model)) {
            return "Модель автомобиля не должна быть пустой и должна содержать от "
                    + MIN_MODEL_LENGTH + " до " + MAX_MODEL_LENGTH + " символов";
        }

        if (!isYearValid(year)) {
            return "Год производства автомобиля должен быть от " + MIN_YEAR + " до " + MAX_YEAR;
        }

        return null;
    }

    /**
     * Проверяет мощность автомобиля.
     */
    public static boolean isPowerValid(int power) {
        return power >= MIN_POWER && power <= MAX_POWER;
    }

    /**
     * Проверяет модель автомобиля.
     */
    public static boolean isModelValid(String model) {
        if (model == null) {
            return false;
        }

        String trimmedModel = model.trim();

        return !trimmedModel.isEmpty()
                && trimmedModel.length() <= MAX_MODEL_LENGTH;
    }

    /**
     * Проверяет год производства автомобиля.
     */
    public static boolean isYearValid(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }
}