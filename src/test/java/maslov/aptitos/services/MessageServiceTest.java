package maslov.aptitos.services;

import maslov.aptitos.domain.Message;
import maslov.aptitos.repo.MessageRepo;
import org.hamcrest.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageService.class)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepo messageRepo;

    @Test
    void createMessage() {
        Message message = new Message();

        messageService.createMessage(message);
        LocalDateTime date = message.getCreationDate();

        Assert.assertNotNull(date);
        Mockito.verify(messageRepo, Mockito.times(1)).save(ArgumentMatchers.any(Message.class));
    }

    @Test
    void allMessage() {
        Message message = new Message();
        Message message2 = new Message();
        List<Message> list = new ArrayList<>();
        list.add(message);
        list.add(message2);

        Mockito.when(messageRepo.findAll())
                .thenReturn(list);
        List<Message> expectedList = messageService.allMessage();

        Assertions.assertEquals(expectedList, list);
        Mockito.verify(messageRepo, Mockito.times(1)).findAll();
    }

    @Test
    void getMessage() {
        Message message = new Message();
        message.setId((long) 1);
        message.setText("Hello, tests!");

        Mockito.when(messageRepo.findById(message.getId()))
                .thenReturn(Optional.of(message));
        Message message2 = messageService.getMessage(message.getId());
        String text = message2.getText();

        Assertions.assertEquals(message.getText(), text);
    }

    @Test
    void updateMessage() {
        Message message = new Message();
        message.setText("message");
        Message messageFromDB = new Message();
        messageFromDB.setText("messageFromDB");

        messageService.updateMessage(message, messageFromDB);

        Assertions.assertEquals("message", messageFromDB.getText());
        Mockito.verify(messageRepo, Mockito.times(1)).save(ArgumentMatchers.any(Message.class));
    }

    @Test
    void delMessage() {
        Message message = new Message();
        message.setId((long) 1);

        boolean res = messageService.delMessage(message.getId());

        org.junit.Assert.assertTrue(res);
        Mockito.verify(messageRepo, Mockito.times(1)).deleteById(message.getId());
    }
}