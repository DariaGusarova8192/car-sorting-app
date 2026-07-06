package collection;

import model.Car;

import java.util.AbstractList;

public class CarList extends AbstractList<Car> {

    private Car[] carArray;
    private int size;
    public CarList() {
        this.carArray = new Car[10];
        this.size = 0;
    }
    public CarList(int capacity) {
        if(capacity > 0) {
            this.carArray = new Car[capacity];
            this.size = 0;
        } else if (capacity == 0) {
            this.carArray = new Car[10];
            this.size = 0;
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }
    @Override
    public Car get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return carArray[index];
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void add(int index, Car element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == carArray.length) {
            Car[] newCarArray = new Car[carArray.length * 2 + 1];
            for(int i = 0; i < size; i ++) {
                newCarArray[i] = carArray[i];
            }
            carArray = newCarArray;
        }
        for(int i = size; i > index; i--) {
            carArray[i] = carArray[i-1];
        }
        carArray[index] = element;
        size++;
    }
    @Override
    public boolean add(Car element) {
        add(size, element);
        return true;
    }
    @Override
    public Car remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Car oldValue = carArray[index];

        for (int i = index; i < size - 1; i++) {
            carArray[i] = carArray[i + 1];
        }

        carArray[size - 1] = null;
        size--;

        return oldValue;
    }
    @Override
    public Car set(int index, Car element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Car oldValue = carArray[index];
        carArray[index] = element;
        return oldValue;
    }
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            carArray[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(carArray[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
