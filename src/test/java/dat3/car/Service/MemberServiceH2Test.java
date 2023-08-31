package dat3.car.Service;

import dat3.car.DTO.MemberRequest;
import dat3.car.DTO.MemberResponse;
import dat3.car.Entity.Member;
import dat3.car.Repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberServiceH2Test {

    @Autowired
    MemberRepository memberRepository;
    MemberService memberService;

    Member m1, m2;  //Talk about references in Java for why we don't add the "isInitialize flag"

    @BeforeEach
    void setUp() {
        m1 = memberRepository.save(new Member("user1", "pw1", "email1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "pw2", "email2", "fn2", "ln2", "street2", "city2", "zip2"));
        memberService = new MemberService(memberRepository); //Set up memberService with the mock (H2) database
    }

    @Test
    void testGetMembersAllDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(true);
        assertEquals(2, memberResponses.size());
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNotNull(time);
    }

    @Test
    void testGetMembersNoDetails() {
        List<MemberResponse> memberResponses = memberService.getMembers(false);
        assertEquals(2, memberResponses.size());
        LocalDateTime time = memberResponses.get(0).getCreated();
        assertNull(time);
    }

    @Test
    void testFindByIdFound() {
        MemberResponse response = memberService.findById(m1.getUsername());
        assertEquals("user1", response.getUsername());
        //TODO
        //assertEquals("email1", response.getEmail());
    }

    @Test
    void testFindByIdNotFound() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)

        ResponseStatusException ex =
                assertThrows(ResponseStatusException.class, () -> memberService.findById("asdg"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember saves a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work


    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        //TODO
        MemberRequest m3 = new MemberRequest("user1", "pw1", "email1", "fn1",
                "ln1",  "street1", "city1", "zip1");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.addMember(m3));
        assertEquals(HttpStatusCode.valueOf(400), ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        //TODO
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)
        //TODO
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        //TODO
    }

    @Test
    void testSetRankingForUser() {
        //TODO
    }

    @Test
    void testSetRankingForNoExistingUser() {
        //TODO
    }
    @Test
    void testDeleteMemberByUsername() {
        //TODO
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        //TODO
    }
}
