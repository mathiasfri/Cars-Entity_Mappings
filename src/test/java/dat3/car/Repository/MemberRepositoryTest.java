package dat3.car.Repository;

import dat3.car.Entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    boolean isInitialized = false;

    @BeforeEach
    void setUp() {
        if (!isInitialized){
            memberRepository.deleteAll();
            memberRepository.save(new Member("user1", "user1@example.com", "password1", "John", "Doe", "123 Main St", "Cityville", 12345, true, 4));
            memberRepository.save(new Member("user2", "user2@example.com", "password2", "Jane", "Smith", "456 Elm St", "Townsburg", 54321, false, 2));
            isInitialized = true;
        }
    }

    @Test
    public void testMembers(){
        Long count = memberRepository.count();
        assertEquals(2, count);
    }

    @Test
    public void deleteMembers(){
        memberRepository.deleteAll();
        assertEquals(0, memberRepository.count());
    }
}