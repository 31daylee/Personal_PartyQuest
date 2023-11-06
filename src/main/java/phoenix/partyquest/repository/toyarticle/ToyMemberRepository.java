package phoenix.partyquest.repository.toyarticle;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.partyquest.domain.toyarticle.ToyMember;

import java.util.Optional;

public interface ToyMemberRepository extends JpaRepository<ToyMember,Long> {

    Optional<ToyMember> findByEmail(String username);
}
