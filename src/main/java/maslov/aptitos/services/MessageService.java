package maslov.aptitos.services;

import maslov.aptitos.repo.MessageRepo;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;

public class MessageService {

    private final MessageRepo messageRepo;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> allMessage() {
        return messageRepo.findAll();
    }
}
