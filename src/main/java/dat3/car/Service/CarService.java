package dat3.car.Service;

import dat3.car.DTO.CarRequest;
import dat3.car.DTO.CarResponse;
import dat3.car.Entity.Car;
import dat3.car.Repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<CarResponse> getCars(boolean includeAll){
        List<Car> cars = carRepository.findAll();

        List<CarResponse> response = cars.stream().map(car -> new CarResponse(car, includeAll)).toList();
        return response;
    }

    public CarResponse addCar(CarRequest body){
        if (carRepository.existsById(body.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This car already exist");
        }
        Car newCar = CarRequest.getCarEntity(body);
        carRepository.save(newCar);

        return new CarResponse(newCar, true);
    }

    public CarResponse findById(int id){
        Car car = carRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this id does not exist"));

        return new CarResponse(car, true);
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int id){
        Car car = carRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this id does not exist"));

        if (body.getId() != id){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Editing wrong car");
        }
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);

        return ResponseEntity.ok(true);
    }

    public Car getCarById(int id){
        return carRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Car with this id does not exist"));
    }

    public void deleteById(int id){
        Car car = getCarById(id);
        carRepository.delete(car);
    }

    // TODO
    public CarResponse findCarsWithBrand(){
        return null;
    }

    public CarResponse findCarsNotReserved(){
        return null;
    }

    public CarResponse findBestDiscount(){
        return null;
    }

    public int findAverageCarPricePerDay(){
        return 0;
    }
}
