package phoenix.partyquest.service.toyarticle;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyarticle.ToyArticle;
import phoenix.partyquest.domain.toyarticle.ToyMember;
import phoenix.partyquest.repository.toyarticle.ToyArticleRepository;
import phoenix.partyquest.repository.toyarticle.ToyMemberRepository;
import phoenix.partyquest.request.toyarticle.ToyArticleDeleteRequest;
import phoenix.partyquest.request.toyarticle.ToyArticleRequest;
import phoenix.partyquest.request.toyarticle.ToyArticleUpdateRequest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class ToyArticleServiceTest {

    @Autowired
    ToyArticleService toyArticleService;

    @Autowired
    ToyArticleRepository toyArticleRepository;

    @Autowired
    ToyMemberRepository toyMemberRepository;

    @Autowired
    EntityManager em;


    @Test
    @DisplayName("서비스가 과연 새로운 아티클을 만들까요?")
    void insertArticle() {
        // given
        String name1 = "bella";
        ToyMember testMember = ToyMember.builder()
                                        .name(name1)
                                        .build();
        ToyMember savedMember = toyMemberRepository.save(testMember);
        // 새로 멤버가 저장되는지 확인
        // 저장된 이름과 초기화 한 이름이 같은 지를 비교
        Assertions.assertThat(savedMember.getName()).isEqualTo(name1);


        // requestDTO 에서 필요한, authorId/title/content 정보 초기화
        Long testAuthorId = savedMember.getId();
        String testTitle = "testTitle2";
        String testContent = "testContent2";

        // when
        ToyArticleRequest testRequest = ToyArticleRequest.builder()
                                    .title(testTitle)
                                    .content(testContent)
                                    .authorId(testAuthorId)
                                    .build();
        ToyArticle newArticle = toyArticleService.insertArticle(testRequest);
        // then
        Assertions.assertThat(newArticle.getTitle()).isEqualTo(testTitle);
        Assertions.assertThat(newArticle.getContent()).isEqualTo(testContent);

    }

    // List
    @Test
    @DisplayName("리스트를 출력할 수 있을까요?")
    void selectArticles(){
        // given
        String name1 = "bella";
        ToyMember testMember = ToyMember.builder()
                .name(name1)
                .build();
        ToyMember savedMember1 = toyMemberRepository.save(testMember);

        String name2 = "BBB";
        ToyMember testMember2 = ToyMember.builder()
                .name(name2)
                .build();
        ToyMember savedMember2 = toyMemberRepository.save(testMember2);

        List<ToyArticle> toyArticleList = new ArrayList<>();
        ToyArticle article1 = new ToyArticle(savedMember1,"테스트1","컨텐츠1" );
        ToyArticle article2 = new ToyArticle(savedMember2,"테스트2","컨텐츠2" );

        toyArticleList.add(article1);
        toyArticleList.add(article2);

        // when
        toyArticleList = toyArticleService.selectArticles();

        // then
        Assertions.assertThat(toyArticleList.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("아티클을 업데이트 할 수 있을까요?")
    void updateArticle(){

        // given
        String name1 = "bella";
        ToyMember testMember = ToyMember.builder()
                .name(name1)
                .build();
        ToyMember savedMember1 = toyMemberRepository.save(testMember);

        String name2 = "BBB";
        ToyMember testMember2 = ToyMember.builder()
                .name(name2)
                .build();
        ToyMember savedMember2 = toyMemberRepository.save(testMember2);

        // when
        ToyArticleRequest testRequest = ToyArticleRequest.builder()
                                    .title("제목")
                                    .content("내용")
                                    .authorId(testMember.getId())
                                    .build();
        ToyArticle newArticle = toyArticleService.insertArticle(testRequest);

        ToyArticleUpdateRequest toyArticleUpdateRequest = new ToyArticleUpdateRequest();
        toyArticleUpdateRequest.setTitle("제목 수정");
        toyArticleUpdateRequest.setContent("내용 수정");
        // 새롭게 등록된 아티클 articleId 가져온 후 저장
        toyArticleUpdateRequest.setArticleId(newArticle.getId());
        toyArticleUpdateRequest.setAuthorId(savedMember2.getId());

        //권한없는 사람이 업데이트 접근시 에러를 던진다.
        assertThatThrownBy(() -> toyArticleService.updateArticle(toyArticleUpdateRequest)).isInstanceOf(RuntimeException.class);

        toyArticleUpdateRequest.setAuthorId(savedMember1.getId());
        ToyArticle toyArticle = toyArticleService.updateArticle(toyArticleUpdateRequest);
        assertThat(toyArticle.getTitle()).isEqualTo(toyArticleUpdateRequest.getTitle());
        assertThat(toyArticle.getContent()).isEqualTo(toyArticleUpdateRequest.getContent());



    }

    @Test
    @DisplayName("아티클을 삭제 할 수 있을까요?")
    void deleteArticle() {
        // given
        String name1 = "bella";
        ToyMember testMember = ToyMember.builder()
                .name(name1)
                .build();
        ToyMember savedMember1 = toyMemberRepository.save(testMember);

        String name2 = "BBB";
        ToyMember testMember2 = ToyMember.builder()
                .name(name2)
                .build();
        ToyMember savedMember2 = toyMemberRepository.save(testMember2);

        // when
        ToyArticleRequest testRequest = ToyArticleRequest.builder()
                .title("제목")
                .content("내용")
                .authorId(testMember.getId())
                .build();
        ToyArticle newArticle = toyArticleService.insertArticle(testRequest);


        ToyArticleDeleteRequest toyArticleDeleteRequest = new ToyArticleDeleteRequest();

        toyArticleDeleteRequest.setArticleId(newArticle.getId());
        toyArticleDeleteRequest.setAuthorId(testMember2.getId());

        //권한없는 사람이 업데이트 접근시 에러를 던진다.
        assertThatThrownBy(() -> toyArticleService.deleteArticle(toyArticleDeleteRequest)).isInstanceOf(RuntimeException.class);

        toyArticleDeleteRequest.setAuthorId(savedMember1.getId());
        Long articleId = toyArticleService.deleteArticle(toyArticleDeleteRequest);
        assertThat(articleId).isEqualTo(toyArticleDeleteRequest.getArticleId());

    }





}