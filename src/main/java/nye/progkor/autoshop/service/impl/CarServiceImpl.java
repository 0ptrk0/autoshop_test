package nye.progkor.autoshop.service.impl;

import nye.progkor.autoshop.model.Car;
import nye.progkor.autoshop.model.exceptcion.NotFoundException;
import nye.progkor.autoshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

  private final List<Car> dataBase = new ArrayList<>();

  @Autowired
  public CarServiceImpl() {
    dataBase.add(new Car(1L, "Lamborghini", "Aventador", 826000));
    dataBase.add(new Car(2L, "Nissan", "R34", 70000));
    dataBase.add(new Car(3L, "Toyota", "Supra", 59000));
  }

  public CarServiceImpl(final List<Car> cars) {
    dataBase.addAll(cars);

  }

  @Override
  public List<Car> getAllCars() {
    return Collections.unmodifiableList(dataBase);
  }

  @Override
  public Car getCars(final Long id) {
    return dataBase.stream()
            .filter(car -> car.getId().equals(id))
            .findFirst()
            .orElseThrow(NotFoundException::new);
  }

  @Override
  public Car createCars(final Car car) {
    car.setId(getNextId());
    dataBase.add(car);
    return car;
  }

  @Override
  public Car updateCars(final Long id, final Car carChange) {
    final Car car = getCars(id);
    car.setBrand(carChange.getBrand());
    car.setModel(carChange.getModel());
    car.setPrice(carChange.getPrice());
    return car;
  }

  @Override
  public void deleteCars(final Long id) {
    final Car car = getCars(id);
    dataBase.remove(car);

  }

  private long getNextId() {
    return getLastId() + 1L;
  }

  private long getLastId() {
    return dataBase.stream()
            .mapToLong(Car::getId)
            .max()
            .orElse(0);
  }
}
