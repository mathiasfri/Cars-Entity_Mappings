package dat3.car.API;

import dat3.car.DTO.CarRequest;
import dat3.car.DTO.CarResponse;
import dat3.car.Service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {
    CarService carService;

     public CarController(CarService carService){
         this.carService = carService;
     }

     //Security -> Admin
     @GetMapping
     List<CarResponse> getCars(){
         return carService.getCars(true);
     }

    //Security -> Admin
     @GetMapping(path = "/{id}")
     CarResponse getCarById(@PathVariable int id) throws Exception{
         return carService.findById(id);
     }

    //Security -> Admin
     @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
     CarResponse addCar(@RequestBody CarRequest body){
         return carService.addCar(body);
     }

    //Security -> Admin
     @PutMapping("/{id}")
     ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
         return carService.editCar(body, id);
     }

     //Security -> Admin
     @DeleteMapping("/{id}")
     void deleteCarById(@PathVariable int id){
         carService.deleteById(id);
     }
}
