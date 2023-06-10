package maslov.aptitos.services;

import maslov.aptitos.domain.Message;
import maslov.aptitos.repo.MessageRepo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MessageService.class)
class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageRepo messageRepo;

    @Test
    void createMessage() {
        Message message = new Message();

        messageService.createMessage(message);
        LocalDateTime date = message.getCreationDate();

        assertNotNull(date);
        verify(messageRepo, Mockito.times(1)).save(ArgumentMatchers.any(Message.class));
    }

    @Test
    void allMessage() {
        Message message = new Message();
        Message message2 = new Message();
        List<Message> list = new ArrayList<>();
        list.add(message);
        list.add(message2);
        List<Message> expectedList = messageService.allMessage();

        when(messageRepo.findAll()).thenReturn(list);

        assertEquals(expectedList, list);
        verify(messageRepo, Mockito.times(1)).findAll();
    }

    @Test
    void getMessage() {
        Message message = new Message();
        message.setId((long) 1);
        message.setText("Hello, tests!");

        when(messageRepo.findById(message.getId()))
                .thenReturn(Optional.of(message));
        Message message2 = messageService.getMessage(message.getId());
        String text = message2.getText();

        assertEquals(message.getText(), text);
    }

    @Test
    void updateMessage() {
        Message message = new Message();
        message.setText("message");
        Message messageFromDB = new Message();
        messageFromDB.setText("messageFromDB");

        messageService.updateMessage(message, messageFromDB);

        assertEquals("message", messageFromDB.getText());
        verify(messageRepo, Mockito.times(1)).save(ArgumentMatchers.any(Message.class));
    }

    @Test
    void delMessage() {
        Message message = new Message();
        message.setId((long) 1);

        boolean res = messageService.delMessage(message.getId());

        assertTrue(res);
        verify(messageRepo, Mockito.times(1)).deleteById(message.getId());
    }
}