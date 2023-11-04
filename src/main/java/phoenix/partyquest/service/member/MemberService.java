package phoenix.partyquest.service.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.repository.member.MemberRepository;
import phoenix.partyquest.request.member.MemberSignUpRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member signUp(MemberSignUpRequest request) {
        return memberRepository.save(request.toMember());
    }
}
