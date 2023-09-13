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
        m1 = memberRepository.save(new Member("user1", "email1", "pw1", "fn1", "ln1",  "street1", "city1", "zip1"));
        m2 = memberRepository.save(new Member("user2", "email2", "pw2", "fn2", "ln2", "street2", "city2", "zip2"));
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
    }

    @Test
    void testFindByIdNotFound() {
        //This should test that a ResponseStatus exception is thrown with status= 404 (NOT_FOUND)

        ResponseStatusException ex =
                assertThrows(ResponseStatusException.class, () -> memberService.findById("???"));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
        /* Remember MemberRequest comes from the API layer, and MemberResponse is returned to the API layer
         * Internally addMember saves a Member entity to the database*/
    void testAddMember_UserDoesNotExist() {
        //Add @AllArgsConstructor to MemberRequest and @Builder to MemberRequest for this to work
        MemberRequest m3 = new MemberRequest("user3", "email1", "pw1", "fn1",
                "ln1",  "street1", "city1", "zip1");
        memberService.addMember(m3);

        assertEquals(3, memberRepository.count());
    }

    @Test
    void testAddMember_UserDoesExistThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 409 (BAD_REQUEST)
        //TODO
        MemberRequest m3 = new MemberRequest("user1", "email1", "pw1", "fn1",
                "ln1",  "street1", "city1", "zip1");
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> memberService.addMember(m3));
        assertEquals(HttpStatusCode.valueOf(400), ex.getStatusCode());
    }

    @Test
    void testEditMemberWithExistingUsername() {
        MemberRequest body = new MemberRequest("user1", "email1", "pw1", "changedName",
                "ln1",  "street1", "city1", "zip1");
        memberService.editMember(body, m1.getUsername());

        assertEquals("changedName", m1.getFirstName());
    }

    @Test
    void testEditMemberNON_ExistingUsernameThrows() {
        //This should test that a ResponseStatus exception is thrown with status= 400 (BAD_REQUEST)
        MemberRequest body = new MemberRequest("user1", "email1", "pw1", "fn1",
                "ln1",  "street1", "city1", "zip1");

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () ->
                memberService.editMember(body, "?????"));
        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testEditMemberChangePrimaryKeyThrows() {
        //Create a MemberRequest from an existing member we can edit
        MemberRequest request = new MemberRequest(m1);
        request.setUsername("changedPrimaryKey");

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, ()
        -> memberService.editMember(request, m1.getUsername()));

        assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
    }

    @Test
    void testSetRankingForUser() {
        memberService.setRankingForUser(m1.getUsername(), 5);
        assertEquals(5, m1.getRanking());
    }

    @Test
    void testSetRankingForNoExistingUser() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, ()
                -> memberService.setRankingForUser("????", 9));
        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    void testDeleteMemberByUsername() {
        //TODO
        memberService.deleteMemberByUsername(m1.getUsername());
        assertEquals(1, memberRepository.count());
    }

    @Test
    void testDeleteMember_ThatDontExist() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, ()
        -> memberService.deleteMemberByUsername("???"));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }
}
