package dat3.car.Service;

import dat3.car.DTO.ReservationRequest;
import dat3.car.DTO.ReservationResponse;
import dat3.car.Entity.Car;
import dat3.car.Entity.Member;
import dat3.car.Entity.Reservation;
import dat3.car.Repository.CarRepository;
import dat3.car.Repository.MemberRepository;
import dat3.car.Repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;
    MemberService memberService;

    public ReservationService(CarRepository carRepository, MemberRepository memberRepository,
                              ReservationRepository reservationRepository, MemberService memberService) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
        this.memberService = memberService;
    }

    public ReservationResponse reserveCar(ReservationRequest body){
        if(body.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date in past not allowed");
        }
        Member member = memberRepository.findById(body.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this id found"));
        Car car = carRepository.findById(body.getCarId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No Car with this id found"));
        //What if already reserved  --> Tomorrow
        Reservation res = reservationRepository.save(new Reservation(body.getDate(), member, car));
        return  new ReservationResponse(res);
    }

    //TODO
    public List<ReservationResponse> findReservationsByMember(Member member){
        List<Reservation> reservationList = reservationRepository.findByMember(member);

        List<ReservationResponse> response = reservationList.stream().map(reservation ->
                new ReservationResponse(reservation)).toList();
        return response;
    }
}
