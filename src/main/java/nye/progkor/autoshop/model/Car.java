package nye.progkor.autoshop.model;

import java.util.Objects;

public class Car {
  private Long id;
  private String brand;
  private String model;
  private Integer price;

  public Car() {
  }

  public Car(Long id, String brand, String model, Integer price) {
    this.id = id;
    this.brand = brand;
    this.model = model;
    this.price = price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Car)) {
      return false;
    }
    Car car = (Car) o;
    return Objects.equals(id, car.id) && Objects.equals(brand, car.brand)
            && Objects.equals(model, car.model) && Objects.equals(price, car.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, brand, model, price);
  }

  @Override
  public String toString() {
    return "Cars{"
            +
            "id="
            + id
            +
            ", brand='"
            + brand
            + '\''
            +
            ", model='"
            + model
            + '\''
            +
            ", price="
            + price
            +
            '}';
  }

}
