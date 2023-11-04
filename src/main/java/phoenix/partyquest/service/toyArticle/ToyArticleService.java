package phoenix.partyquest.service.toyArticle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyArticle.ToyArticle;
import phoenix.partyquest.domain.toyArticle.ToyMember;
import phoenix.partyquest.repository.toyArticle.ToyArticleRepository;
import phoenix.partyquest.repository.toyArticle.ToyMemberRepository;
import phoenix.partyquest.request.toyArticle.ToyArticleRequest;

@Slf4j
@Service
@RequiredArgsConstructor // 생성자 주입을 자동으로 생성
public class ToyArticleService {

    // service 에서는 필드 주입
    private final ToyArticleRepository toyArticleRepository;
    private final ToyMemberRepository toyMemberRepository;

    // 일련의 과정으로 만들기 위한 선언 (=트랜잭션)
    @Transactional
    public ToyArticle insertArticle(ToyArticleRequest toyArticleRequest){
        // DB에서 정보(author) 찾아오기
        ToyMember findMember = toyMemberRepository.findById(toyArticleRequest.getAuthorId()).orElseThrow();
        // 존재 하는 멤버가 있을 시, 글 등록하기
        return toyArticleRepository.save(toyArticleRequest.toToyArticle(findMember));
    }



}
