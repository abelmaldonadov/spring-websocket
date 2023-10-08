package amv.springwebsocketdemo.services;

import amv.springwebsocketdemo.models.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void processingAndSend(Message message) {
        simpMessagingTemplate.convertAndSend("/topic/messages/" + message.room(), message);
    }
}
