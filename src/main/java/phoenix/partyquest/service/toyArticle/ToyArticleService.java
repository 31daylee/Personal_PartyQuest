package phoenix.partyquest.service.toyArticle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import phoenix.partyquest.domain.toyArticle.ToyArticle;
import phoenix.partyquest.repository.toyArticle.ToyArticleRepository;
import phoenix.partyquest.request.toyArticle.ToyArticleRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToyArticleService {

    private final ToyArticleRepository toyArticleRepository;
    private final ModelMapper modelMapper;

    public ToyArticle save(ToyArticleRequest toyArticleRequest){
        return toyArticleRepository.save(toyArticleRequest.toToyArticle());
    }



}
