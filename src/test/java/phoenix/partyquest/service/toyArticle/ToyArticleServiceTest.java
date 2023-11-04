package phoenix.partyquest.service.toyArticle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import phoenix.partyquest.domain.toyArticle.ToyMember;
import phoenix.partyquest.repository.toyArticle.ToyArticleRepository;

@Transactional
@SpringBootTest
class ToyArticleServiceTest {
    @Autowired
    ToyArticleRepository toyArticleRepository;

    @Test
    void save() {

        ToyMember toyMember = new ToyMember();
        toyMember.setName("bella");
/*
        ToyArticleRequest toyArticleRequest = new ToyArticleRequest();
        toyArticleRequest.setAuthor(toyMember.getName());*/

        Assertions.assertThat()

    }
}