package dat3.car.Repository;

import dat3.car.Entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;
    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized){
            carRepository.deleteAll();
            carRepository.save(new Car("Honda", "Sedan", 550, 15));
            carRepository.save(new Car("Toyota", "SUV", 400, 25));
            isInitialized = true;
        }
    }

    @Test
    public void testAllCars(){
        Long count = carRepository.count();
        assertEquals(2, count);
    }

    @Test
    public void deleteAllCars(){
        carRepository.deleteAll();
        assertEquals(0, carRepository.count());
    }
}