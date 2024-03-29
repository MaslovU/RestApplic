package maslov.aptitos.services;

import maslov.aptitos.domain.Message;
import maslov.aptitos.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Service
public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> allMessage() {
        return messageRepo.findAll();
    }

    public Message getMessage(Long id) {
        return Optional.of(messageRepo.findById(id)).get().orElse(new Message());
    }

    @Transactional
    public Message createMessage(Message message) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @Transactional
    public Message updateMessage(Message message, Message messageFromDB) {
        BeanUtils.copyProperties(message, messageFromDB, "id");
        return messageRepo.save(messageFromDB);
    }

    @Transactional
    public boolean delMessage(Long id) {
        messageRepo.deleteById(id);
        return true;
    }
}