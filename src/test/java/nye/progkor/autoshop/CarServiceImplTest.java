package nye.progkor.autoshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import nye.progkor.autoshop.model.Car;
import nye.progkor.autoshop.model.exceptcion.NotFoundException;
import nye.progkor.autoshop.service.CarService;
import nye.progkor.autoshop.service.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CarServiceImplTest {

    private static final Car AVENTADOR = new Car(1L, "Lamborghini", "Aventador", 825000);
    private static final Car R34 = new Car(2L, "Nissan", "R34", 70000);
    private static final Car SUPRA = new Car(3L, "Toyota", "Supra", 49000);
    private static final List<Car> CARS = List.of(AVENTADOR, R34, SUPRA);

    public static final long UNKNOWN_CAR_ID = -1L;
    public static final String CAR_BRAND_NAME = "VolksWagen";

    private CarService underTest;

    @BeforeEach
    void setUp() {
        underTest = new CarServiceImpl(CARS);
    }

    @Test
    void getAllCarsShouldReturnAllCars(){
        //When
    final List<Car> actual = underTest.getAllCars();
        //Then
        assertThat(actual).isEqualTo(CARS);

    }
    @Test
    void getCarsShouldReturnCarWhenGivenIdOfAnExistingCar(){
        // When
    final Car actual = underTest.getCars(AVENTADOR.getId());
        //Then
    assertThat(actual).isEqualTo(AVENTADOR);
    }

    @Test
    void getCarsShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingCar() {
        // When Then
        assertThrows(NotFoundException.class, () -> underTest.getCars(UNKNOWN_CAR_ID));
    }

    @Test
    void createCarsShouldReturnCarWhenItIsCreatedSuccessfully() {
        // Given
        final Car golfCar = new Car(null, CAR_BRAND_NAME,"Golf VII", 24000);
        final Car expectedCar = new Car(4L, CAR_BRAND_NAME, "Golf VII", 24000);
        // When
        final Car actual = underTest.createCars(golfCar);
        // Then
        assertThat(actual).isEqualTo(expectedCar);
    }
    @Test
    void updateCarsShouldReturnUpdatedCarWhenGivenIdOfExistingCar() {
        // given
        final Car golfCar = new Car(null, CAR_BRAND_NAME,"Golf VII", 24500);
        final Car expectedCar = new Car(AVENTADOR.getId(), CAR_BRAND_NAME, "Golf VII",24500);
        // when
        final Car actual = underTest.updateCars(AVENTADOR.getId(), golfCar);
        // then
        assertThat(actual).isEqualTo(expectedCar);
    }
    @Test
    void updateCarsShouldThrowNotFoundExceptionWhenGivenIdOfNotExistingCar() {
        // Given
        final Car golfCar = new Car(null, CAR_BRAND_NAME, "Golf VII", 24500);
        // When Then
        assertThrows(NotFoundException.class, () -> underTest.updateCars(UNKNOWN_CAR_ID,golfCar ));
    }

    @Test
    void deleteCarsShouldDeleteCarWhenGivenIdOfCar(){
        //Given
    final List<Car> expectedCars = List.of(AVENTADOR,SUPRA);
        //When
      underTest.deleteCars(R34.getId());
      final List<Car> actual = underTest.getAllCars();
        //Then
        assertThat(actual).isEqualTo(expectedCars);
    }
}