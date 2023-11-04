package phoenix.partyquest.repository.toyArticle;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.partyquest.domain.toyArticle.ToyMember;

import java.util.Optional;

public interface ToyMemberRepository extends JpaRepository<ToyMember,Long> {

    Optional<ToyMember> findByEmail(String username);
}
