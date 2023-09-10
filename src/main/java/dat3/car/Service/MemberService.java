package dat3.car.Service;

import dat3.car.DTO.MemberRequest;
import dat3.car.DTO.MemberResponse;
import dat3.car.Entity.Member;
import dat3.car.Repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(boolean includeAll) {
        List<Member> members = memberRepository.findAll();

/*        List<MemberResponse> response = new ArrayList<>();
        for (Member m : members) {
            MemberResponse mr = new MemberResponse(m, includeAll);
            response.add(mr);
        }*/

        List<MemberResponse> response = members.stream().map(member -> new MemberResponse(member, includeAll)).toList();
        return response;
    }

    public MemberResponse addMember(MemberRequest body) {
        if (memberRepository.existsById(body.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user already exists");
        }
        Member newMember = MemberRequest.getMemberEntity(body);

        newMember = memberRepository.save(newMember);
        return new MemberResponse(newMember, true);
    }

    public ResponseEntity<Boolean> editMember(MemberRequest body, String username) {
        Member member = memberRepository.findById(username).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member with this username does exist"));

        if (!body.getUsername().equals(username)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change username");
        }
        member.setPassword(body.getPassword());
        member.setEmail(body.getEmail());
        member.setFirstName(body.getFirstName());
        member.setLastName(body.getLastName());
        member.setStreet(body.getStreet());
        member.setCity(body.getCity());
        member.setZip(body.getZip());
        memberRepository.save(member);

        return ResponseEntity.ok(true);
    }

    public MemberResponse findById(String username) {
        Member member = memberRepository.findById(username).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member with this username does not exist"));
        return new MemberResponse(member, true);
    }

    public void setRankingForUser(String username, int value) {
        Member member = getMemberByUsername(username);
        member.setRanking(value);
        memberRepository.save(member);
    }

    public void deleteMemberByUsername(String username) {
        Member member = getMemberByUsername(username);
        memberRepository.delete(member);
    }

    private Member getMemberByUsername(String username){
        return memberRepository.findById(username).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Member with this username does not exist"));
    }

    // TODO
    public List<MemberResponse> findMembersWithReservation(boolean includeAll){
        List<Member> members = memberRepository.findMemberByReservationsIsTrue();

        List<MemberResponse> response = members.stream().map(member -> new MemberResponse(member, includeAll)).toList();
        return response;
    }
}