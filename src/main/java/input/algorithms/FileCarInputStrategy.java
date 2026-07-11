package input.algorithms;

import builder.CarBuilder;
import collection.CarList;
import input.strategy.CarInputStrategy;
import model.Car;
import validation.CarValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Стратегия чтения автомобилей из файла.
 *
 * Ожидаемый формат строки в файле:
 * power;model;year
 *
 * Пример:
 * 150;Toyota;2020
 * 90;Lada;2015
 * 220;BMW;2021
 *
 * Файл читается целиком.
 * Некорректные строки не добавляются в коллекцию.
 * Для таких строк выводится сообщение в консоль.
 *
 * Заполнение коллекции выполняется через Stream API,
 * что закрывает дополнительное требование задания.
 */
public class FileCarInputStrategy implements CarInputStrategy {
    private static final String DELIMITER = ";";

    private final String filePath;

    /**
     * @param filePath путь к файлу с автомобилями
     */
    public FileCarInputStrategy(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Путь к файлу не должен быть пустым");
        }

        this.filePath = filePath;
    }

    /**
     * Читает файл целиком, преобразует корректные строки в объекты Car
     * и добавляет их в кастомную коллекцию CarList.
     */
    @Override
    public CarList inputCars() {
        CarList cars = new CarList();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.map(this::parseLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(cars::add);
        } catch (IOException exception) {
            System.out.println("Ошибка чтения файла: " + exception.getMessage());
        }

        return cars;
    }

    /**
     * Возвращает название стратегии.
     */
    @Override
    public String getStrategyName() {
        return "Чтение из файла";
    }

    /**
     * Преобразует одну строку файла в объект Car.
     *
     * Если строка пустая, имеет неверный формат
     * или содержит некорректные данные, возвращается Optional.empty().
     */
    private Optional<Car> parseLine(String line) {
        if (line == null || line.trim().isEmpty()) {
            System.out.println("Пропущена пустая строка.");
            return Optional.empty();
        }

        String[] parts = line.split(DELIMITER);

        if (parts.length != 3) {
            System.out.println("Пропущена строка с неверным форматом: " + line);
            return Optional.empty();
        }

        try {
            int power = Integer.parseInt(parts[0].trim());
            String model = parts[1].trim();
            int year = Integer.parseInt(parts[2].trim());

            String validationError = CarValidator.getValidationError(power, model, year);

            if (validationError != null) {
                System.out.println("Пропущена строка: " + line);
                System.out.println("Причина: " + validationError);
                return Optional.empty();
            }

            Car car = new CarBuilder()
                    .setPower(power)
                    .setModel(model)
                    .setYear(year)
                    .build();

            return Optional.of(car);
        } catch (NumberFormatException exception) {
            System.out.println("Пропущена строка с неверными числами: " + line);
            return Optional.empty();
        }
    }
}