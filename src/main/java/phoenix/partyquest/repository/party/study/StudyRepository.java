package phoenix.partyquest.repository.party.study;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import phoenix.partyquest.domain.party.study.Study;

public interface StudyRepository extends JpaRepository<Study, Long> {
    /**
     * 동시성 이슈를 해결하기 위해서 조회시
     * pessimistic lock을 건채로 조회 한다.(선착순으로 스터디 멤버를 모집할때 사용)
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from Study s where s.id = :id")
    Study findByIdWithPessimisticLock(@Param("id") Long id);
}
