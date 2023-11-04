package phoenix.partyquest.request.toyArticle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.toyArticle.ToyArticle;
import phoenix.partyquest.domain.toyArticle.ToyMember;

@Getter
@NoArgsConstructor
public class ToyArticleRequest {

    // TODO-HJ: Entity에서는 JoinColumn을 표시하고 타입을 ToyMember라 해둠. 그렇다면 requestDTO에서도 필요할까?
    private ToyMember author;
    private String title;
    private String content;


    public ToyArticle toToyArticle(){
        return ToyArticle.builder()
                .author(this.author)
                .title(this.title)
                .content(this.content)
                .build();
    }

}
