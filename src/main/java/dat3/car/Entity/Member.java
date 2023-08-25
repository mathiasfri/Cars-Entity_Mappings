package dat3.car.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "zip")
    private int zip;
    @Column(name = "approved")
    private boolean approved;
    @Column(name = "ranking")
    private int ranking;

    @Column(name = "created")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "lastEdited")
    @UpdateTimestamp
    private LocalDateTime lastEdited;

    public Member(String username, String email, String password, String firstName, String lastName,
                  String street, String city, int zip, boolean approved, int ranking) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.approved = approved;
        this.ranking = ranking;
    }
}
