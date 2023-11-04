package phoenix.partyquest.domain.party.study;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.Member;

@Entity
@NoArgsConstructor
public class StudyMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_id")
    private Study study;

    public void allocateStudy(Study study) {
        this.study = study;
    }
}
