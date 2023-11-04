package phoenix.partyquest.service.toyArticle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyArticle.ToyArticle;
import phoenix.partyquest.domain.toyArticle.ToyMember;
import phoenix.partyquest.repository.toyArticle.ToyArticleRepository;
import phoenix.partyquest.repository.toyArticle.ToyMemberRepository;
import phoenix.partyquest.request.toyArticle.ToyArticleRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToyArticleService {

    private final ToyArticleRepository toyArticleRepository;
    private final ToyMemberRepository toyMemberRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public ToyArticle insertArticle(ToyArticleRequest toyArticleRequest){
        // 우선 작성자(author)찾아온다
        ToyMember findMember = toyMemberRepository.findById(toyArticleRequest.getAuthorId()).orElseThrow();

        return toyArticleRepository.save(toyArticleRequest.toToyArticle(findMember));
    }
}
