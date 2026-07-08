package input;

import collection.CarList;
import input.strategy.CarInputStrategy;

/**
 * Сервис для запуска выбранной стратегии ввода автомобилей.
 *
 * Этот класс не знает, каким именно способом будут вводиться автомобили.
 * Он просто получает объект стратегии и запускает его.
 *
 * Это часть реализации паттерна Strategy:
 * поведение программы можно менять, подставляя другую стратегию ввода.
 */
public class CarInputService {
    private CarInputStrategy inputStrategy;

    public CarInputService(CarInputStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
    }

    /**
     * Позволяет заменить стратегию ввода во время работы программы.
     *
     * Например:
     * сначала пользователь выбрал случайное заполнение,
     * потом решил загрузить данные из файла.
     */
    public void setInputStrategy(CarInputStrategy inputStrategy) {
        this.inputStrategy = inputStrategy;
    }

    /**
     * Запускает текущую стратегию ввода и возвращает коллекцию автомобилей.
     */
    public CarList inputCars() {
        if (inputStrategy == null) {
            throw new IllegalStateException("Стратегия ввода автомобилей не выбрана");
        }

        System.out.println("Выбрана стратегия ввода: " + inputStrategy.getStrategyName());

        return inputStrategy.inputCars();
    }
}