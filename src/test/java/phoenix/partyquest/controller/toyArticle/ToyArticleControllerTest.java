package phoenix.partyquest.controller.toyArticle;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import phoenix.partyquest.repository.toyArticle.ToyMemberRepository;
import phoenix.partyquest.request.toyArticle.ToyArticleRequest;
import phoenix.partyquest.service.toyArticle.ToyArticleService;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ToyArticleController.class)
class ToyArticleControllerTest {
    @InjectMocks
    ToyArticleController toyArticleController;

    @Mock
    ToyArticleService toyArticleService;

    @Mock
    ToyMemberRepository toyMemberRepository;


    @Autowired
    private MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(toyArticleController).build();
    }
    @Test
    @DisplayName("글작성")
    public void insertArticle() throws Exception{
        // given
        ToyArticleRequest articleRequest = new ToyArticleRequest(1234L,"타이틀","내용");

        // when, then
        mockMvc.perform(
                post("/toyArticle/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleRequest)))
                .andExpect(status().isCreated());

        verify(toyArticleService).insertArticle(articleRequest);

    }

}