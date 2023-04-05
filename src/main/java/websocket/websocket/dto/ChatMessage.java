package websocket.websocket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//채팅 메시지를 주고받기 위함.
public class ChatMessage {

    // 채팅방 입장과 채팅방에 메시지 보내기
    public enum MessageType {
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}
