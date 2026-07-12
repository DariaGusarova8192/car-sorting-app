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
    private List<String> compileTextResult(SortResult result) {
        List<String> stringResult = new ArrayList<String>();
        stringResult.add("===========Обычная сортировка===========");
        for(Car car : result.getSortedCars()) {
            stringResult.add(car.toString());
        }
        stringResult.add("===========Сортировка четных===========");
        for(Car car : result.getAdditionalSortedCars()) {
            stringResult.add(car.toString());
        }
        return stringResult;
    }

    public void output(SortResult result) {
        Path path = Paths.get("sorting_result.txt");
        try {
            if(isAppendMode == true) {
                Files.write(path, compileTextResult(result), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } else {
                Files.write(path, compileTextResult(result), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
