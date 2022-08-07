package maslov.aptitos.controller;

import com.fasterxml.jackson.annotation.JsonView;
import maslov.aptitos.domain.Message;
import maslov.aptitos.domain.Views;
import maslov.aptitos.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List list() {
        return messageService.allMessage();
    }

    @GetMapping("{id}")
    @JsonView(Views.IdName.class)
    public Message getOneMessage(@PathVariable Long id) {
        return messageService.getMessage(id);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDB,
            @RequestBody Message message) {
        return messageService.updateMessage(message, messageFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        messageService.delMessage(id);
    }
}