package nye.progkor.autoshop.service;

import nye.progkor.autoshop.model.Car;

import java.util.List;

public interface CarService {

  List<Car> getAllCars();

  Car getCars(Long id);

  Car createCars(Car car);

  Car updateCars(Long id, Car carChange);

  void deleteCars(Long id);
}
