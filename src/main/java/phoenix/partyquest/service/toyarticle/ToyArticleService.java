package phoenix.partyquest.service.toyarticle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyarticle.ToyArticle;
import phoenix.partyquest.domain.toyarticle.ToyMember;
import phoenix.partyquest.repository.toyarticle.ToyArticleRepository;
import phoenix.partyquest.repository.toyarticle.ToyMemberRepository;
import phoenix.partyquest.request.toyarticle.ToyArticleRequest;

import java.util.List;

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
        log.info("toyArticleRequest authorId : " + toyArticleRequest.getAuthorId());
        log.info("toyArticleRequest getContent : " + toyArticleRequest.getContent());
        log.info("toyArticleRequest getTitle : " + toyArticleRequest.getTitle());
        ToyMember findMember = toyMemberRepository.findById(toyArticleRequest.getAuthorId()).orElseThrow();
        // 존재 하는 멤버가 있을 시, 글 등록하기
        return toyArticleRepository.save(toyArticleRequest.toToyArticle(findMember));
    }

    public List<ToyArticle> selectArticles(){
        return toyArticleRepository.findAll();
    }

    @Transactional
    public void updateArticle(){

    }



}
