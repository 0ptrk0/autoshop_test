package nye.progkor.autoshop.controller;

import nye.progkor.autoshop.model.Car;
import nye.progkor.autoshop.model.exceptcion.NotFoundException;

import java.util.List;

import nye.progkor.autoshop.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("auto-shop")
public class CarController {

  private static final String CAR_LIST_TEMPLATE_NAME = "cars/list";
  private static final String CAR_EDIT_TEMPLATE_NAME = "cars/edit";
  private static final String CAR_ATTRIBUTE_NAME = "car";

  private final CarService carService;

  public CarController(final CarService carService) {
    this.carService = carService;
  }

  @GetMapping
  public String getAllRolePlay(final Model model) {
    final List<Car> cars = carService.getAllCars();
    model.addAttribute("cars", cars);
    return CAR_LIST_TEMPLATE_NAME;
  }

  @GetMapping("/{id}")
  public String getCar(final Model model, final @PathVariable Long id) {
    final Car car = carService.getCars(id);
    model.addAttribute(CAR_ATTRIBUTE_NAME, car);
    return CAR_EDIT_TEMPLATE_NAME;
  }


  @PostMapping(value = "/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public String updateRolePlay(final Model model,
                               final @RequestParam(value = "id", required = false) Long id,
                               final Car carChanges) {
    final Car car = carService.updateCars(id, carChanges);
    model.addAttribute(CAR_ATTRIBUTE_NAME, car);
    return CAR_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/create")
  public String createCarForm(final Model model) {
    return "cars/create";
  }

  @PostMapping("/create")
  public String createCar(final Model model, final Car car) {
    final Car savedCar = carService.createCars(car);
    model.addAttribute(CAR_ATTRIBUTE_NAME, savedCar);
    return CAR_EDIT_TEMPLATE_NAME;
  }

  @GetMapping("/{id}/delete")
  public String deleteCar(final Model model, final @PathVariable("id") Long id) {
    try {
      carService.deleteCars(id);
    } catch (NotFoundException e) {
      // Ignored
    }
    final List<Car> cars = carService.getAllCars();
    model.addAttribute("cars", cars);
    return CAR_LIST_TEMPLATE_NAME;
  }
}
