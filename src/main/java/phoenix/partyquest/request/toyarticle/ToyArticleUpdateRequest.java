package phoenix.partyquest.request.toyarticle;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.toyarticle.ToyArticle;

@Getter @Setter
@NoArgsConstructor
public class ToyArticleUpdateRequest {
    private Long authorId; // 화면 단에서 받는다
    private Long articleId;
    private String title;
    private String content;

    // 빌더 패턴의 생성자 주입 _DTO에서는 @setter 방식 지양


    @Builder
    public ToyArticleUpdateRequest(Long authorId, Long articleId, String title, String content) {
        this.authorId = authorId;
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    // dto -> domain 과정
    // ToyMember타입의 author는 멤버의 모든 정보를 가져온다
    public ToyArticle toToyArticle(){
            return ToyArticle.builder()
                    .title(this.title)
                    .content(this.content)
                    .build();
    }
}
