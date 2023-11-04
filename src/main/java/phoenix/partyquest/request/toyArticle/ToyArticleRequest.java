package phoenix.partyquest.request.toyArticle;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.toyArticle.ToyArticle;
import phoenix.partyquest.domain.toyArticle.ToyMember;

@Getter
@NoArgsConstructor
public class ToyArticleRequest {

    // TODO-HJ: Entity에서는 JoinColumn을 표시하고 타입을 ToyMember라 해둠. 그렇다면 requestDTO에서도 필요할까?
    private Long authorId; // 화면단에서 받아와야한다.
    private String title;
    private String content;

    @Builder
    public ToyArticleRequest(Long authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }
    public ToyArticle toToyArticle(ToyMember author){
        return ToyArticle.builder()
                .author(author)
                .title(this.title)
                .content(this.content)
                .build();
    }

}
