package phoenix.partyquest.service.toyarticle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyarticle.ToyArticle;
import phoenix.partyquest.domain.toyarticle.ToyMember;
import phoenix.partyquest.repository.toyarticle.ToyArticleRepository;
import phoenix.partyquest.repository.toyarticle.ToyMemberRepository;
import phoenix.partyquest.request.toyarticle.ToyArticleDeleteRequest;
import phoenix.partyquest.request.toyarticle.ToyArticleRequest;
import phoenix.partyquest.request.toyarticle.ToyArticleUpdateRequest;

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




    /*동한 ) 도메인에서 update 처리함
     * 현정 ) 서비스 단에서 update 처리함
     * 차이 ) 서비스는 도메인에게 요청을 하는 클래스이지 서비스에서 도메인의 내용을 변경하는 것은 아니다. 서비스는 단순 메시지 송신. 그리고 도메인에서 update를 관리 및 메시지 수신
     * */
    @Transactional
    public ToyArticle updateArticle(ToyArticleUpdateRequest toyArticleUpdateRequest){

        ToyArticle findArticle = toyArticleRepository.findToyArticleById(toyArticleUpdateRequest.getArticleId()).orElseThrow();

        if(findArticle.getAuthor().getId() != toyArticleUpdateRequest.getAuthorId()){
            log.info("접근 허용 되지 않은 사용자 입니다.");
            throw new RuntimeException();
        }
        findArticle.updateArticle(toyArticleUpdateRequest.toToyArticle());
        return findArticle;
    }

    /* Delete 메서드를 실행한 후 해당 Long articleId를 반환 해준다 */
    public Long deleteArticle(ToyArticleDeleteRequest toyArticleDeleteRequest){

        ToyArticle findArticle = toyArticleRepository.findToyArticleById(toyArticleDeleteRequest.getArticleId()).orElseThrow();

        if(findArticle.getAuthor().getId() != toyArticleDeleteRequest.getAuthorId()){
            log.info("접근 허용 되지 않은 사용자 입니다.");
            throw new RuntimeException();
        }
        toyArticleRepository.delete(findArticle);
        return toyArticleDeleteRequest.getArticleId();
    }





}
