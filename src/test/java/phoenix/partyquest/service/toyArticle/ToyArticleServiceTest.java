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
        String name1 = "bella";
        ToyMember testMember = ToyMember.builder()
                                        .name(name1)
                                        .build();
        ToyMember savedMember = toyMemberRepository.save(testMember);
        // 새로 멤버가 저장되는지 확인
        Assertions.assertThat(savedMember.getName()).isEqualTo(name1);

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
}