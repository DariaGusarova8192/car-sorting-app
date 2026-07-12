package output;

import model.Car;
import service.SortResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileOutput {
    private final boolean isAppendMode;

    public FileOutput(boolean isAppendMode) {
        this.isAppendMode = isAppendMode;
    }
    private List<String> compileSortingResult(SortResult result) {
        List<String> stringResult = new ArrayList<>();
        stringResult.add("===========Обычная сортировка===========");
        for(Car car : result.getSortedCars()) {
            stringResult.add(car.toString());
        }
        stringResult.add("");
        stringResult.add("===========Сортировка четных===========");
        for(Car car : result.getAdditionalSortedCars()) {
            stringResult.add(car.toString());
        }
        return stringResult;
    }

    public void sortResultOutput(SortResult result) {
        Path path = Paths.get("sorting_result.txt");
        try {
            if(isAppendMode) {
                Files.write(path, compileSortingResult(result), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                Files.write(path, compileSortingResult(result), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void searchResultOutput(Car car, int result, int countThreads) {
        Path path = Paths.get("searching_result.txt");
        List<String> stringResult = new ArrayList<>();
        stringResult.add("===========Результат поиска===========");
        stringResult.add("Искомый автомобиль:");
        stringResult.add(car.toString());
        stringResult.add("===========Количество потоков===========");
        stringResult.add(String.valueOf(countThreads));
        stringResult.add("===========Количество вхождений===========");
        stringResult.add(String.valueOf(result));

        try {
            if(isAppendMode) {
                Files.write(path, stringResult, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                Files.write(path, stringResult, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
