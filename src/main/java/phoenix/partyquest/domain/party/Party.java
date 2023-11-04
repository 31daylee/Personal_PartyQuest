package phoenix.partyquest.domain.party;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.base.BaseLocation;
import phoenix.partyquest.domain.member.Member;

import java.time.LocalDateTime;

/**
 * 모임의 공통 필드를 가지는 엔티티
 */
@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="party_type")
@NoArgsConstructor
public class Party extends BaseLocation {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "party_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leader_id")
    private Member leader;

    @Column(name = "party_name")
    private String name;

    // description 어떻게 파일로 가져 올지 알아보기. LOB 조사 해보자 네이버 smart Editor 2.0
    @Lob
    private String desc;
    @Enumerated(EnumType.STRING)
    private PartyStatus status;
    private LocalDateTime recruitStartAt;
    private LocalDateTime recruitEndAt;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    public void changeStatus(PartyStatus status) {
        this.status = status;
    }

    public Party(Member leader, String name, String desc, LocalDateTime recruitStartAt, LocalDateTime recruitEndAt) {
        this.leader = leader;
        this.name = name;
        this.desc = desc;
        this.status = PartyStatus.OPENED;
        this.recruitStartAt = recruitStartAt;
        this.recruitEndAt = recruitEndAt;
        this.createdAt = LocalDateTime.now();
    }
}

