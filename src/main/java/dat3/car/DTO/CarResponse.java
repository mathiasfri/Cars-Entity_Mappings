package dat3.car.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.Entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor //A must for @Builder
@Builder  //I will demo it's purpose in the class
//It's really IMPORTANT that you understand the purpose of this annotation
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    int id;
    String brand;
    String model;
    double pricePrDay;
    int bestDiscount;

    public CarResponse(Car c, boolean includeAll){
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        if (includeAll){
            this.bestDiscount = c.getBestDiscount();
        }
    }
}
