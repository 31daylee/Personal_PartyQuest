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
    @JoinColumn(name = "author_id")
    private ToyMember author;
    private String title;
    private String content;

    @Builder
    public ToyArticle(ToyMember author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }
}