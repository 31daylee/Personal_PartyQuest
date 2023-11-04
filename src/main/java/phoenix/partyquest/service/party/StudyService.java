package phoenix.partyquest.service.party;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import phoenix.partyquest.domain.party.study.Study;
import phoenix.partyquest.repository.party.study.StudyRepository;
import phoenix.partyquest.request.party.study.StudyCreateRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;
    public Study createStudy(StudyCreateRequest dto) {
        return null;
    }
}
