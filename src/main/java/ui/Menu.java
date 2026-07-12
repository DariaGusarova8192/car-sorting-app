package ui;

import service.CarService;

import java.util.Scanner;

import static validation.CarValidator.*;

public class Menu {
    private static boolean isRunning = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static final CarService service = new CarService();

    public static void menu() {
        listenInputMethod();
    }
    private static int listener() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (Exception e) {
                System.out.println("Вы ввели некорректное число:");
                scanner.nextLine();
            }
        }
    }
    private static void listenInputMethod() {
        while (isRunning) {
            System.out.println("===========Выберите способ ввода автомобилей===========");
            System.out.println("Ввести автомобили из файла - Введите 1");
            System.out.println("Ввести автомобили вручную - Введите 2");
            System.out.println("Сгенерировать случайные автомобили - Введите 3");
            System.out.println("Выход - Введите 4");
            int selectedMenuItem = listener();
            int amountOfData = 0;
            if(selectedMenuItem == 4) {
                isRunning = false;
            } else {
                if (selectedMenuItem == 2 || selectedMenuItem == 3) {
                    amountOfData = listenAmountOfData();
                }
                if(!service.selectInputMethod(selectedMenuItem, amountOfData)) {
                    continue;
                }
                listenActionWithCars();
            }
        }
    }
    private static void listenActionWithCars() {
        while (true) {
            System.out.println("===========Что следует сделать с введенными автомобилями?===========");
            System.out.println("Сортировать автомобили - Введите 1");
            System.out.println("Подсчитать число вхождений автомобиля N в коллекцию автомобилей - Введите 2");
            System.out.println("Вернуться к меню ввода автомобилей - Введите 3");
            int selectedStep = listener();
            if(selectedStep == 3) {
                break;
            }
            switch (selectedStep) {
                case 1:
                    listenSortingMethod();
                    break;
                case 2:
                    listenCarToSearch();
                    break;
                default:
                    System.out.println("Вы ввели некорректный пункт меню");
                    continue;
            }
        }
    }
    private static int listenAmountOfData() {
        int amountOfData = 0;
        while (true) {
            System.out.println("===========Введите количество автомобилей===========");
            amountOfData = listener();
            if(amountOfData >= 0) {
                break;
            } else {
                System.out.println("Вы ввели некорректное количество автомобилей");
            }
        }
        return amountOfData;
    }
    private static void listenSortingMethod() {
        while (true) {
            System.out.println("===========Выберите способ сортировки автомобилей===========");
            System.out.println("Пузырьковая сортировка - Введите 1");
            System.out.println("Быстрая сортировка - Введите 2");
            System.out.println("Сортировка вставками - Введите 3");
            System.out.println("Вернуться к меню выбора действия - Введите 4");
            int selectedSortingAlgorithm = listener();
            if (selectedSortingAlgorithm == 4) {
                break;
            }
            if(!service.selectSortingAlgorithm(selectedSortingAlgorithm)) {
                continue;
            }
            listenFieldForSorting();
        }
    }
    private static void listenFieldForSorting() {
        while (true) {
            System.out.println("============Выберите поле, по которому необходимо отсортировать автомобили===========");
            System.out.println("Сортировать по мощности - Введите 1");
            System.out.println("Сортировать по модели - Введите 2");
            System.out.println("Сортировать по году выпуска - Введите 3");
            System.out.println("Сортировать по всем полям - Введите 4");
            System.out.println("Вернуться к меню выбора сортировки - Введите 5");
            int fieldForSorting = listener();
            if (fieldForSorting == 5) {
                break;
            }
            if(!service.selectFieldForSorting(fieldForSorting)) {
                continue;
            }
            boolean isAppendMode = listenAppendMode()==1;
            service.sortingOutput(isAppendMode);
        }
    }
    private static void listenCarToSearch() {
        int threadCount;
        int power;
        String model;
        int year;
        while (true) {
            System.out.println("Введите число потоков");
            threadCount = listener();
            if(threadCount > 0) {
                break;
            }
            System.out.println("Вы ввели некорректное число потоков");
        }
        while (true) {
            System.out.println("Введите мощность автомобиля");
            power = listener();
            if(isPowerValid(power)) {
                break;
            }
            System.out.println("Вы ввели некорректную мощность автомобиля");
        }
        while (true) {
            System.out.println("Введите модель автомобиля");
            model = scanner.nextLine();
            if(isModelValid(model)) {
                break;
            }
            System.out.println("Вы ввели некорректную модель автомобиля");
        }
        while (true) {
            System.out.println("Введите год выпуска автомобиля");
            year = listener();
            if(isYearValid(year)) {
                break;
            }
            System.out.println("Вы ввели некорректный год выпуска автомобиля");
        }
        boolean isAppendMode = listenAppendMode()==1;
        service.searchingOutput(power, model, year, threadCount, isAppendMode);
    }
    private static int listenAppendMode() {
        while (true) {
            System.out.println("============Включить режим добавления данных?===========");
            System.out.println("Да - Введите 1");
            System.out.println("Нет - Введите 2");
            int choice = listener();
            if(choice==1||choice==2) {
                return choice;
            } else {
                System.out.println("Вы ввели некорректный пункт меню");
            }
        }
    }
}