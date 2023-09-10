package dat3.car.Repository;

import dat3.car.Entity.Member;
import dat3.car.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByMember(Member member);
}
