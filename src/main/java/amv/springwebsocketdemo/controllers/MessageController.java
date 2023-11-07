package amv.springwebsocketdemo.controllers;

import amv.springwebsocketdemo.models.Message;
import amv.springwebsocketdemo.services.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @MessageMapping("/send")
//    @SendTo("/topic/messages")
    public Message send(Message message, @Header("simpSessionId") String sessionId) {
        log.info(sessionId);
        messageService.processingAndSend(message);
        return null;
    }

    @EventListener
    public void onConnectEvent(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        log.info("Client with session id {} connected", accessor.getSessionId());
    }

    @EventListener
    public void onDisconnectEvent(SessionDisconnectEvent event) {
        log.info("Client with session id {} disconnected", event.getSessionId());
        String sessionId = event.getSessionId();
        //Do something
    }
}
