package phoenix.partyquest.domain.party.study.map;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.category.MiddleCate;
import phoenix.partyquest.domain.party.study.Study;

@Entity
@NoArgsConstructor
public class MiddleStudyMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "middle_cate_id")
    private MiddleCate middleCate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public void allocateStudy(Study study) {
        this.study = study;
    }
}
