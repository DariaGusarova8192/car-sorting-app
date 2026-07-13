package service;

import model.Car;

/**
 * Результат многопоточного подсчёта количества вхождений автомобиля.
 *
 * Нужен, чтобы вернуть наружу:
 * - искомый автомобиль;
 * - количество найденных вхождений;
 * - количество потоков, которое ввёл пользователь;
 * - фактическое количество потоков, которое реально использовалось.
 */
public class CountResult {
    private final Car targetCar;
    private final int occurrencesCount;
    private final int requestedThreadCount;
    private final int actualThreadCount;

    public CountResult(Car targetCar, int occurrencesCount, int requestedThreadCount, int actualThreadCount) {
        this.targetCar = targetCar;
        this.occurrencesCount = occurrencesCount;
        this.requestedThreadCount = requestedThreadCount;
        this.actualThreadCount = actualThreadCount;
    }

    public Car getTargetCar() {
        return targetCar;
    }

    public int getOccurrencesCount() {
        return occurrencesCount;
    }

    public int getRequestedThreadCount() {
        return requestedThreadCount;
    }

    public int getActualThreadCount() {
        return actualThreadCount;
    }
}