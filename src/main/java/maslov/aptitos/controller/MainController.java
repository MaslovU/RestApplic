package maslov.aptitos.controller;

import maslov.aptitos.domain.SuperUser;
import maslov.aptitos.services.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageService messageService;

    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal SuperUser superUser){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", superUser);
        data.put("messages", messageService.allMessage());

        model.addAttribute("frontendData", data);

        return "index";
    }
}