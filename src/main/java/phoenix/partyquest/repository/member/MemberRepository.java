package phoenix.partyquest.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.partyquest.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
