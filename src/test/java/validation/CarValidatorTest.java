package validation;

import builder.CarBuilder;
import model.Car;

/**
 * Ручные тесты для CarValidator.
 *
 * Этот класс можно запускать как обычную Java-программу.
 * JUnit не используется, потому что по заданию разрешены тесты,
 * реализованные вручную в отдельных классах.
 */
public class CarValidatorTest {

    public static void main(String[] args) {
        testValidFields();
        testInvalidPower();
        testInvalidModel();
        testInvalidYear();
        testValidCarObject();
        testNullCarObject();
        testValidationErrorText();

        System.out.println("CarValidatorTest: все тесты пройдены успешно.");
    }

    /**
     * Проверяем, что корректные поля проходят валидацию.
     */
    private static void testValidFields() {
        boolean result = CarValidator.isValid(150, "Toyota", 2020);
        assertTrue(result, "Корректные данные должны проходить валидацию");
    }

    /**
     * Проверяем, что некорректная мощность не проходит валидацию.
     */
    private static void testInvalidPower() {
        boolean result = CarValidator.isValid(0, "Toyota", 2020);
        assertFalse(result, "Мощность 0 не должна проходить валидацию");
    }

    /**
     * Проверяем, что пустая модель не проходит валидацию.
     */
    private static void testInvalidModel() {
        boolean result = CarValidator.isValid(150, "   ", 2020);
        assertFalse(result, "Пустая модель не должна проходить валидацию");
    }

    /**
     * Проверяем, что некорректный год не проходит валидацию.
     */
    private static void testInvalidYear() {
        boolean result = CarValidator.isValid(150, "Toyota", 3000);
        assertFalse(result, "Год 3000 не должен проходить валидацию");
    }

    /**
     * Проверяем валидацию уже созданного объекта Car.
     */
    private static void testValidCarObject() {
        Car car = new CarBuilder()
                .setPower(150)
                .setModel("Toyota")
                .setYear(2020)
                .build();

        boolean result = CarValidator.isValid(car);
        assertTrue(result, "Корректный объект Car должен проходить валидацию");
    }

    /**
     * Проверяем, что null вместо объекта Car не проходит валидацию.
     */
    private static void testNullCarObject() {
        boolean result = CarValidator.isValid(null);
        assertFalse(result, "null не должен проходить валидацию");
    }

    /**
     * Проверяем, что при ошибке валидации возвращается текст ошибки.
     */
    private static void testValidationErrorText() {
        String error = CarValidator.getValidationError(0, "Toyota", 2020);

        if (error == null || error.isEmpty()) {
            throw new AssertionError("При некорректных данных должен возвращаться текст ошибки");
        }
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message);
        }
    }
}