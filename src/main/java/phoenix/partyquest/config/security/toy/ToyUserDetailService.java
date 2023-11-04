package phoenix.partyquest.config.security.toy;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import phoenix.partyquest.domain.toyArticle.ToyMember;
import phoenix.partyquest.repository.toyArticle.ToyMemberRepository;

@Service
@RequiredArgsConstructor
public class ToyUserDetailService implements UserDetailsService {
    private final ToyMemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ToyMember findMember = memberRepository.findByEmail(username).orElseThrow();
        ToyUserDetails userDetails = new ToyUserDetails(findMember);
        return userDetails;
    }
}
