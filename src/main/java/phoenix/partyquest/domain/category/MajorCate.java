package phoenix.partyquest.domain.category;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MajorCate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "major_cate_id")
    private Long id;
    private String name;

    @Builder
    public MajorCate(String name) {
        this.name = name;
    }
}
//TODO: sequence 1-100 => sequence를 왜 쓸까?
