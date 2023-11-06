package phoenix.partyquest.controller.toyarticle;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import phoenix.partyquest.request.toyarticle.ToyArticleRequest;
import phoenix.partyquest.service.toyarticle.ToyArticleService;

@Log4j2
@RequiredArgsConstructor
@Controller
public class ToyArticleController {

    private final ToyArticleService toyArticleService;

    @GetMapping("/toyArticle/list")
    public String list(){
        return "/toyArticle/list";
    }
    @GetMapping("/toyArticle/view")
    public String view(){
        return "/toyArticle/view";
    }
    @GetMapping("/toyArticle/write")
    public String write(HttpServletRequest request, Model model){
        return "/toyArticle/write";
    }

    @PostMapping("/toyArticle/write")
    public String insertArticle(HttpServletRequest request, ToyArticleRequest toyArticleRequest){
        log.info("writeController authorId : " + toyArticleRequest.getAuthorId());
        log.info("writeController getContent : " + toyArticleRequest.getContent());
        log.info("writeController getTitle : " + toyArticleRequest.getTitle());
        toyArticleService.insertArticle(toyArticleRequest);
        return "redirect:/toyArticle/list";
    }

}
