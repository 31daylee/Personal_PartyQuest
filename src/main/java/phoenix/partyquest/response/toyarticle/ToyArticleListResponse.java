package phoenix.partyquest.response.toyarticle;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.toyarticle.ToyArticle;

@Getter
@NoArgsConstructor
public class ToyArticleListResponse {

    private String authorName; // 화면 단에서 받는다
    private String title;

    @Builder
    public ToyArticleListResponse(ToyArticle article) {
        this.authorName = article.getAuthor().getName();
        this.title = article.getTitle();
    }
}
