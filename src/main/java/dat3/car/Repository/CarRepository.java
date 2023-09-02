package dat3.car.Repository;

import dat3.car.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> getCarById(int id);
    List<Car> getCarByBrand(String brand);
}
