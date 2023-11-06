package phoenix.partyquest.service.toyArticle;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyArticle.ToyArticle;
import phoenix.partyquest.domain.toyArticle.ToyMember;
import phoenix.partyquest.repository.toyArticle.ToyArticleRepository;
import phoenix.partyquest.repository.toyArticle.ToyMemberRepository;
import phoenix.partyquest.request.toyArticle.ToyArticleRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@Transactional
@SpringBootTest
class ToyArticleServiceTest {

    @InjectMocks
    ToyArticleService toyArticleService;

    @Mock
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
        String testTitle = "testTitle";
        String testContent = "testContent";

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

        given(toyArticleRepository.findAll()).willReturn(toyArticleList);

        // when
        List<ToyArticle> result = toyArticleService.selectArticles();

        // then
        Assertions.assertThat(result.size()).isEqualTo(2);


    }

}