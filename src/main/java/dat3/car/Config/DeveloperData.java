package dat3.car.Config;

import dat3.car.Entity.Car;
import dat3.car.Entity.Member;
import dat3.car.Repository.CarRepository;
import dat3.car.Repository.MemberRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {
    CarRepository carRepository;
    MemberRepository memberRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository){
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<Car> cars = new ArrayList<>();

        // Generate cars
        cars.add(new Car("Toyota", "SUV", 400, 25));
        cars.add(new Car("Honda", "Sedan", 550, 15));
        cars.add(new Car("Ford", "Truck", 650, 35));
        cars.add(new Car("Chevrolet", "Convertible", 300, 10));
        cars.add(new Car("Nissan", "Hatchback", 250, 5));
        cars.add(new Car("BMW", "Coupe", 750, 20));
        cars.add(new Car("Mercedes", "SUV", 700, 30));
        cars.add(new Car("Audi", "Convertible", 800, 45));
        cars.add(new Car("Volkswagen", "Sedan", 350, 2));
        cars.add(new Car("Tesla", "Electric", 900, 40));
        cars.add(new Car("Toyota", "Truck", 600, 25));
        cars.add(new Car("Honda", "SUV", 450, 15));
        cars.add(new Car("Ford", "Hatchback", 250, 5));
        cars.add(new Car("Chevrolet", "Convertible", 700, 10));
        cars.add(new Car("Nissan", "Sedan", 350, 30));
        cars.add(new Car("BMW", "Coupe", 800, 45));
        cars.add(new Car("Mercedes", "SUV", 500, 20));
        cars.add(new Car("Audi", "Convertible", 400, 2));
        cars.add(new Car("Volkswagen", "Hatchback", 600, 40));
        cars.add(new Car("Tesla", "Electric", 950, 5));
        cars.add(new Car("Toyota", "Sedan", 450, 25));
        cars.add(new Car("Honda", "SUV", 600, 15));
        cars.add(new Car("Ford", "Truck", 650, 10));
        cars.add(new Car("Chevrolet", "Convertible", 350, 20));
        cars.add(new Car("Nissan", "Hatchback", 300, 35));
        cars.add(new Car("BMW", "Coupe", 750, 45));
        cars.add(new Car("Mercedes", "SUV", 700, 2));
        cars.add(new Car("Audi", "Convertible", 800, 5));
        cars.add(new Car("Volkswagen", "Sedan", 400, 30));
        cars.add(new Car("Tesla", "Electric", 900, 40));
        cars.add(new Car("Toyota", "Truck", 550, 25));
        cars.add(new Car("Honda", "SUV", 400, 15));
        cars.add(new Car("Ford", "Hatchback", 250, 35));
        cars.add(new Car("Chevrolet", "Convertible", 700, 10));
        cars.add(new Car("Nissan", "Sedan", 350, 5));
        cars.add(new Car("BMW", "Coupe", 800, 20));
        cars.add(new Car("Mercedes", "SUV", 600, 30));
        cars.add(new Car("Audi", "Convertible", 850, 45));
        cars.add(new Car("Volkswagen", "Hatchback", 400, 2));
        cars.add(new Car("Tesla", "Electric", 950, 40));
        cars.add(new Car("Toyota", "Sedan", 700, 25));
        cars.add(new Car("Honda", "SUV", 400, 15));
        cars.add(new Car("Ford", "Truck", 650, 10));
        cars.add(new Car("Chevrolet", "Convertible", 350, 20));
        cars.add(new Car("Nissan", "Hatchback", 300, 35));
        cars.add(new Car("BMW", "Coupe", 750, 45));
        cars.add(new Car("Mercedes", "SUV", 700, 2));
        cars.add(new Car("Audi", "Convertible", 800, 5));
        cars.add(new Car("Volkswagen", "Sedan", 400, 30));
        cars.add(new Car("Tesla", "Electric", 900, 40));

        carRepository.saveAll(cars);

        List<Member> members = new ArrayList<>(Arrays.asList(
                new Member("user1", "user1@example.com", "password1", "John",
                        "Doe", "123 Main St", "Cityville", "12345"),
                new Member("user2", "user2@example.com", "password2", "Jane",
                        "Smith", "456 Elm St", "Townsburg", "54321"),
                new Member("user3", "user3@example.com", "password3", "Michael",
                        "Johnson", "789 Oak Ave", "Metropolis", "67890"),
                new Member("user4", "user4@example.com", "password4", "Emily",
                        "Brown", "101 Pine Rd", "Villageville", "13579"),
                new Member("user5", "user5@example.com", "password5", "David",
                        "Williams", "222 Cedar Ln", "Townsville", "24680"),
                new Member("user6", "user6@example.com", "password6", "Sarah",
                        "Jones", "333 Maple Dr", "Suburbia", "98765"),
                new Member("user7", "user7@example.com", "password7", "Christopher",
                        "Wilson", "444 Birch Pl", "Citytown", "87654"),
                new Member("user8", "user8@example.com", "password8", "Olivia",
                        "Miller", "555 Oak St", "Villageton", "43210"),
                new Member("user9", "user9@example.com", "password9", "James",
                        "Davis", "666 Elm Ave", "Uptown", "54321"),
                new Member("user10", "user10@example.com", "password10", "Emma",
                        "Moore", "777 Pine Rd", "Downtown", "12345")
        ));
        memberRepository.saveAll(members);
    }
}
