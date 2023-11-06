package phoenix.partyquest.repository.toyarticle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.partyquest.domain.toyarticle.ToyArticle;

public interface ToyArticleRepository extends JpaRepository<ToyArticle,Long> {

    @Query("select a from ToyArticle a left join a.author aa where a.id = :id")
    public ToyArticle getAritlceWithAuthorById(@Param("id") Long articleId);
}



