package phoenix.partyquest.domain.toyArticle;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ToyArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id") // fk 컬럼 이름
    private ToyMember author; // jpa가 로딩할때 객체로 불러오고 실제 db에서 참조 할때는 fk로 찾는다.
    private String title;
    private String content;

    @Builder
    public ToyArticle(ToyMember author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
