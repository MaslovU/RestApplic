/*
Created by Maslov
 */
package maslov.aptitos.controller;

import com.fasterxml.jackson.annotation.JsonView;
import maslov.aptitos.domain.Message;
import maslov.aptitos.domain.Views;
import maslov.aptitos.services.MessageService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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