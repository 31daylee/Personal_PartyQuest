package phoenix.partyquest.request.toyarticle;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ToyArticleDeleteRequest {
    private Long authorId; // 화면 단에서 받는다
    private Long articleId;


    @Builder
    public ToyArticleDeleteRequest(Long authorId, Long articleId) {
        this.authorId = authorId;
        this.articleId = articleId;

    }


}
