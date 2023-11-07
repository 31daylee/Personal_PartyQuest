package phoenix.partyquest.repository.toyarticle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.partyquest.domain.toyarticle.ToyArticle;

import java.util.List;
import java.util.Optional;

public interface ToyArticleRepository extends JpaRepository<ToyArticle,Long> {


    public Optional<ToyArticle> findToyArticleById(@Param("id") Long id);

    @Query("SELECT a FROM ToyArticle a JOIN a.author b WHERE b.id = :id")
    public List<ToyArticle> findAllByAuthorId();
}





