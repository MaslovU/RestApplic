package maslov.aptitos.controller;

import maslov.aptitos.domain.SuperUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping
    public String main(Model model, @AuthenticationPrincipal SuperUser superUser){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", superUser);
        data.put("messages", null);

        model.addAttribute("frontendData", data);
        return "index";
    }
}
