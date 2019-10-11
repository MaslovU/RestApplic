package maslov.aptitos.services;

import maslov.aptitos.repo.MessageRepo;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;

public class MessageService {
    private static SimpleJpaRepository messageRepo;

    public MessageService(MessageRepo messageRepo) {
        MessageService.messageRepo = (SimpleJpaRepository) messageRepo;
    }

    public static Optional<? extends Object> getMessage(Long id) {
        return messageRepo.findById(id);
    }

    public static List<? extends Object> getAllMessages() {
        return messageRepo.findAll();
    }

    public static void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }
}
