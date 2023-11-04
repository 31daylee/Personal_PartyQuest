package phoenix.partyquest.domain.party.study;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import phoenix.partyquest.domain.member.Member;
import phoenix.partyquest.domain.party.Party;
import phoenix.partyquest.domain.party.PartyStatus;
import phoenix.partyquest.domain.party.study.map.MiddleStudyMap;
import phoenix.partyquest.domain.party.study.map.SmallStudyMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("study")
public class Study extends Party {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<MiddleStudyMap> middleCates = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<SmallStudyMap> smallCates = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "study",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<StudyMember> studyMembers = new ArrayList<>();
    private Integer curMembersSize; // TODO: '왜 wrapper로 할까? primitive
    private Integer memberUpperLimit;

    @Builder
    public Study(Member leader, String name, String desc, LocalDateTime recruitStartAt, LocalDateTime recruitEndAt, List<MiddleStudyMap> middleCates, List<SmallStudyMap> smallCates, Integer curMembersSize, Integer memberUpperLimit) {
        super(leader, name, desc, recruitStartAt, recruitEndAt);
        this.middleCates = middleCates;
        this.smallCates = smallCates;
        this.curMembersSize = curMembersSize;
        this.memberUpperLimit = memberUpperLimit;
    }

    public void addMiddleCate(MiddleStudyMap middleCate) {
        middleCates.add(middleCate);
        middleCate.allocateStudy(this);
    }

    public void addSmallCate(SmallStudyMap smallCate) {
        smallCates.add(smallCate);
        smallCate.allocateStudy(this);
    }

    //동시성은 pessimistic lock으로 처리한다.
    public void addMember(StudyMember newMember) {
        //TODO: 에러처리 해줄것
        if (this.getStatus().equals(PartyStatus.CLOSED) || Objects.equals(this.curMembersSize, memberUpperLimit)) {
            return;
        }
        studyMembers.add(newMember);
        newMember.allocateStudy(this);
        this.curMembersSize += 1;
        if (this.getCurMembersSize() == memberUpperLimit) {
            //다 모집 했으면 모집 상태 종료로 바꿔주자
            this.changeStatus(PartyStatus.CLOSED);
        }
    }

    public void removeMember(StudyMember member) {
        if (this.curMembersSize < 1) {
            return;
        }
        studyMembers.remove(member);
        this.curMembersSize -= 1;
    }
}
