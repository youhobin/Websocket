package websocket.websocket.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import websocket.websocket.dto.ChatMessage;
import websocket.websocket.dto.ChatRoom;
import websocket.websocket.service.ChatService;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSockChatHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //클라이언트로부터 받은 메시지를 채팅 객체로 변환
        //payload에는 roomId, sender message가잇음.
        String payload = message.getPayload();
        log.info("session={}", session);
        log.info("payload={}", payload);

        //server에서 보내는 메시지
//        TextMessage textMessage = new TextMessage("chatting server");
//        session.sendMessage(textMessage);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);
    }

}
