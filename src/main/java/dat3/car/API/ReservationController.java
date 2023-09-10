package dat3.car.API;

import dat3.car.DTO.ReservationRequest;
import dat3.car.DTO.ReservationResponse;
import dat3.car.Repository.ReservationRepository;
import dat3.car.Service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @PostMapping
    ReservationResponse makeReservation(@RequestBody ReservationRequest body){

        ReservationResponse response = reservationService.reserveCar(body);

        return response;
    }
}
