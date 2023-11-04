package phoenix.partyquest.domain.member;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.profile.MemberProfile;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;
    // 유저 필수 정보(email,password,name,phone)
    private String email; // 로그인 아이디로 사용한다.
    private String password;
    private String name;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    @JoinColumn(name = "member_profile_id")
    private MemberProfile profile;

    public void allocateProfile(MemberProfile profile) {
        this.profile = profile;
        profile.allocateMember(this);
    }

    @Builder
    public Member(String email, String password, String name, String phone, MemberRole role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }
}
