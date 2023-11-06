package phoenix.partyquest.request.toyarticle;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import phoenix.partyquest.domain.toyarticle.ToyArticle;
import phoenix.partyquest.domain.toyarticle.ToyMember;

@Getter @Setter
@NoArgsConstructor
public class ToyArticleRequest {

    // TODO-HJ: Entity에서는 JoinColumn을 표시하고 타입을 ToyMember라 해둠. 그렇다면 requestDTO에서도 필요할까?
    private Long authorId; // 화면 단에서 받는다
    private String title;
    private String content;

    // 빌더 패턴의 생성자 주입 _DTO에서는 @setter 방식 지양
    @Builder
    public ToyArticleRequest(Long authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    // dto -> domain 과정
    // ToyMember타입의 author는 멤버의 모든 정보를 가져온다
    public ToyArticle toToyArticle(ToyMember author){
            return ToyArticle.builder()
                    .author(author)
                    .title(this.title)
                    .content(this.content)
                    .build();
    }
}
