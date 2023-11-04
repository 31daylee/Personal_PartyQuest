package phoenix.partyquest.request.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.member.MemberRole;

@Getter @Setter
@NoArgsConstructor
public class MemberSignUpRequest {
    private String email;
    private String password;
    private String name;
    private String phone;

    public Member toMember() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .phone(this.phone)
                .role(MemberRole.ROLE_USER) // 단순 회원일때 롤 바로 할당해서 만들기
                .build();
    }
}
