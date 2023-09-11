package dat3.car.Entity;

import dat3.car.API.ReservationController;
import dat3.car.DTO.ReservationResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE")
public class Member extends dat3.security.entity.UserWithRoles {
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "zip")
    private String zip;
    @Column(name = "approved")
    private boolean approved;
    @Column(name = "ranking")
    private int ranking;

    @OneToMany(mappedBy = "member")
    List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation){
        if (reservation == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }

    public Member(String username, String email, String password, String firstName, String lastName,
                  String street, String city, String zip) {
        super(username, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }
}
