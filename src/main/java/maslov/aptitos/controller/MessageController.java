package maslov.aptitos.controller;

import com.fasterxml.jackson.annotation.JsonView;
import maslov.aptitos.domain.Message;
import maslov.aptitos.domain.Views;
import maslov.aptitos.services.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("message")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageRepo messageRepo) {
        messageService = new MessageService(messageRepo);
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List list() {
        return MessageService.getAllMessages();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Optional getOneMessage (@PathVariable Long id){
        return MessageService.getMessage(id);
    }

//    @PostMapping
//    public Message create(@RequestBody Message message) {
//        message.setCreationDate(LocalDateTime.now());
//        return messageRepo.save(message);
//    }
//
//    @PutMapping("{id}")
//    public Message update(
//            @PathVariable("id") Message messageFromDB,
//            @RequestBody Message message
//    ) {
//        BeanUtils.copyProperties(message, messageFromDB, "id");
//        return messageRepo.save(messageFromDB);
//    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        MessageService.deleteMessage(id);
    }
}
