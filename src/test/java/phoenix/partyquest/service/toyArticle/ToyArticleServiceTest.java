package phoenix.partyquest.service.toyArticle;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyArticle.ToyArticle;
import phoenix.partyquest.domain.toyArticle.ToyMember;
import phoenix.partyquest.repository.toyArticle.ToyArticleRepository;
import phoenix.partyquest.repository.toyArticle.ToyMemberRepository;
import phoenix.partyquest.request.toyArticle.ToyArticleRequest;

import static org.assertj.core.api.Assertions.*;

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
    void save() {
        // given

        String testName = "test";
        ToyMember testMember = ToyMember.builder()
                .name(testName)
                .build();
        ToyMember savedMember = toyMemberRepository.save(testMember);
        // 새로 멤버가 잘 생성되는지 이름으로 검증하자.
        assertThat(savedMember.getName()).isEqualTo(testName);

        Long testAuthorId = savedMember.getId();
        String testTitle = "testTitle 귀여워";
        String testContent = "test Content 쩡이";
        // when
        ToyArticleRequest testRequest = ToyArticleRequest.builder()
                .title(testTitle)
                .content(testContent)
                .authorId(testAuthorId)
                .build();

        ToyArticle newArticle = toyArticleService.insertArticle(testRequest);
        // then
        assertThat(newArticle.getTitle()).isEqualTo(testTitle);
        assertThat(newArticle.getContent()).isEqualTo(testContent);
    }
}