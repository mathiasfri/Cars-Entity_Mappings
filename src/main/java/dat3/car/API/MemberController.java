package dat3.car.API;

import dat3.car.DTO.MemberRequest;
import dat3.car.DTO.MemberResponse;
import dat3.car.Entity.Member;
import dat3.car.Repository.MemberRepository;
import dat3.car.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/members")
class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Security --> Admin only
    @GetMapping
    List<MemberResponse> getMembers(){
        return memberService.getMembers(false);
    }

    //Security --> Admin only
    @GetMapping(path = "/{username}")
    MemberResponse getMemberById(@PathVariable String username) throws Exception {
        return memberService.findById(username);
    }

    //Security --> Anonymous
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    //Security --> Admin
    @PutMapping("/{username}")
    ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
        return memberService.editMember(body, username);
    }

    //Security --> Admin only
    @PatchMapping("/ranking/{username}/{value}")
    ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {
        return null;
    }

    // Security --> Anonymous
    @DeleteMapping("/{username}")
    void deleteMemberByUsername(@PathVariable String username) {

    }
}

