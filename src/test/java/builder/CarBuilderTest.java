package builder;

import model.Car;

/**
 * Ручные тесты для CarBuilder.
 *
 * Этот класс можно запускать как обычную Java-программу.
 * JUnit не используется, потому что по заданию разрешены тесты,
 * реализованные вручную в отдельных классах.
 */
public class CarBuilderTest {

    public static void main(String[] args) {
        testBuildValidCar();
        testBuildCarWithTrimmedModel();
        testInvalidPower();
        testInvalidModel();
        testInvalidYear();

        System.out.println("CarBuilderTest: все тесты пройдены успешно.");
    }

    /**
     * Проверяем, что Builder создаёт корректный объект Car.
     */
    private static void testBuildValidCar() {
        Car car = new CarBuilder()
                .setPower(150)
                .setModel("Toyota")
                .setYear(2020)
                .build();

        assertEquals(150, car.getPower(), "Ошибка в поле power");
        assertEquals("Toyota", car.getModel(), "Ошибка в поле model");
        assertEquals(2020, car.getYear(), "Ошибка в поле year");
    }

    /**
     * Проверяем, что пробелы в начале и конце модели убираются.
     */
    private static void testBuildCarWithTrimmedModel() {
        Car car = new CarBuilder()
                .setPower(150)
                .setModel("  Toyota  ")
                .setYear(2020)
                .build();

        assertEquals("Toyota", car.getModel(), "Модель должна быть без пробелов по краям");
    }

    /**
     * Проверяем, что Builder не создаёт автомобиль с некорректной мощностью.
     */
    private static void testInvalidPower() {
        assertThrows(() -> new CarBuilder()
                .setPower(0)
                .setModel("Toyota")
                .setYear(2020)
                .build(), "Builder должен выбросить ошибку при некорректной мощности");
    }

    /**
     * Проверяем, что Builder не создаёт автомобиль с пустой моделью.
     */
    private static void testInvalidModel() {
        assertThrows(() -> new CarBuilder()
                .setPower(150)
                .setModel("")
                .setYear(2020)
                .build(), "Builder должен выбросить ошибку при пустой модели");
    }

    /**
     * Проверяем, что Builder не создаёт автомобиль с некорректным годом.
     */
    private static void testInvalidYear() {
        assertThrows(() -> new CarBuilder()
                .setPower(150)
                .setModel("Toyota")
                .setYear(3000)
                .build(), "Builder должен выбросить ошибку при некорректном годе");
    }

    private static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + ". Ожидалось: " + expected + ", получено: " + actual);
        }
    }

    private static void assertEquals(String expected, String actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ". Ожидалось: " + expected + ", получено: " + actual);
        }
    }

    private static void assertThrows(TestAction action, String message) {
        try {
            action.execute();
            throw new AssertionError(message);
        } catch (IllegalArgumentException exception) {
            // Если получили IllegalArgumentException, значит тест прошёл успешно.
        }
    }

    /**
     * Простой функциональный интерфейс для проверки исключений.
     */
    private interface TestAction {
        void execute();
    }
}