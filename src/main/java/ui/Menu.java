package ui;

import service.CarService;

import java.util.Scanner;

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
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Введите корректное число:");
                scanner.nextLine();
            }
        }
    }
    private static void listenInputMethod() {
        while (isRunning) {
            System.out.println("===========Выберите способ ввода данных===========");
            System.out.println("Ввести данные из файла - Нажмите 1");
            System.out.println("Ввести данные вручную - Нажмите 2");
            System.out.println("Сгенерировать случайные данные - Нажмите 3");
            System.out.println("Выход - Нажмите 4");
            int selectedMenuItem = listener();
            int amountOfData = 0;
            if(selectedMenuItem == 4) {
                isRunning = false;
            } else {
                if (selectedMenuItem == 2 || selectedMenuItem == 3) {
                    amountOfData = listenAmountOfData();
                }
                service.selectInputMethod(selectedMenuItem, amountOfData);
                listenSortingMethod();
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
            System.out.println("Пузырьковая сортировка - Нажмите 1");
            System.out.println("Быстрая сортировка - Нажмите 2");
            System.out.println("Сортировка вставками - Нажмите 3");
            System.out.println("Вернуться к меню ввода данных - Нажмите 4");
            int selectedSortingAlgorithm = listener();
            if (selectedSortingAlgorithm == 4) {
                break;
            }
            service.selectSortingAlgorithm(selectedSortingAlgorithm);
            listenFieldForSorting();
        }
    }
    private static void listenFieldForSorting() {
        while (true) {
            System.out.println("============Выберите поле, по которому необходимо отсортировать автомобили===========");
            System.out.println("Сортировать по мощности - Нажмите 1");
            System.out.println("Сортировать по модели - Нажмите 2");
            System.out.println("Сортировать по году выпуска - Нажмите 3");
            System.out.println("Сортировать по всем полям - Нажмите 4");
            System.out.println("Вернуться к меню выбора сортировки - Нажмите 5");
            int fieldForSorting = listener();
            if (fieldForSorting == 5) {
                break;
            }
            service.selectFieldForSorting(fieldForSorting);
        }
    }
}