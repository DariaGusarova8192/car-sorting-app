package service;

import collection.CarList;
import model.Car;

/**
 * Сервис для многопоточного подсчёта количества вхождений автомобиля в коллекцию.
 *
 * Закрывает дополнительное задание 4:
 * "реализовать многопоточный метод, подсчитывающий количество вхождений элемента N
 * в коллекцию и выводящий результат в консоль".
 *
 * В нашем проекте:
 * - коллекция — это CarList;
 * - элемент N — это объект Car;
 * - сравнение автомобилей выполняется через equals().
 *
 * Коллекция делится на несколько частей.
 * Каждая часть обрабатывается отдельным потоком.
 */
public class MultiThreadCounter {

    /**
     * Считает, сколько раз targetCar встречается в коллекции cars.
     *
     * Метод также выводит результат в консоль,
     * как требуется в задании.
     *
     * @param cars коллекция автомобилей
     * @param targetCar автомобиль, количество вхождений которого нужно найти
     * @param threadCount количество потоков
     * @return количество найденных вхождений
     */
    public int countOccurrences(CarList cars, Car targetCar, int threadCount) {
        validateInput(cars, targetCar, threadCount);

        if (cars.size() == 0) {
            System.out.println("Коллекция пустая.");
            System.out.println("Количество вхождений: 0");
            return 0;
        }

        int actualThreadCount = Math.min(threadCount, cars.size());

        Thread[] threads = new Thread[actualThreadCount];
        int[] partialResults = new int[actualThreadCount];

        int basePartSize = cars.size() / actualThreadCount;
        int remainder = cars.size() % actualThreadCount;

        int startIndex = 0;

        for (int i = 0; i < actualThreadCount; i++) {
            int currentPartSize = basePartSize;

            if (i < remainder) {
                currentPartSize++;
            }

            int fromIndex = startIndex;
            int toIndex = startIndex + currentPartSize;
            int resultIndex = i;

            threads[i] = new Thread(() -> {
                partialResults[resultIndex] = countInRange(cars, targetCar, fromIndex, toIndex);
            });

            threads[i].start();

            startIndex = toIndex;
        }

        waitForThreads(threads);

        int totalCount = sumResults(partialResults);

        System.out.println("Искомый автомобиль: " + targetCar);
        System.out.println("Количество потоков: " + actualThreadCount);
        System.out.println("Количество вхождений: " + totalCount);

        return totalCount;
    }

    /**
     * Считает количество вхождений targetCar на участке коллекции.
     *
     * fromIndex включительно,
     * toIndex не включительно.
     */
    private int countInRange(CarList cars, Car targetCar, int fromIndex, int toIndex) {
        int count = 0;

        for (int i = fromIndex; i < toIndex; i++) {
            Car currentCar = cars.get(i);

            if (targetCar.equals(currentCar)) {
                count++;
            }
        }

        return count;
    }

    /**
     * Ожидает завершения всех потоков.
     */
    private void waitForThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Поток был прерван во время подсчёта", exception);
            }
        }
    }

    /**
     * Складывает результаты, полученные от всех потоков.
     */
    private int sumResults(int[] partialResults) {
        int total = 0;

        for (int result : partialResults) {
            total += result;
        }

        return total;
    }

    /**
     * Проверяет входные данные перед запуском многопоточного подсчёта.
     */
    private void validateInput(CarList cars, Car targetCar, int threadCount) {
        if (cars == null) {
            throw new IllegalArgumentException("Коллекция автомобилей не должна быть null");
        }

        if (targetCar == null) {
            throw new IllegalArgumentException("Искомый автомобиль не должен быть null");
        }

        if (threadCount <= 0) {
            throw new IllegalArgumentException("Количество потоков должно быть больше 0");
        }
    }
}