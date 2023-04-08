package websocket.websocket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import websocket.websocket.dto.ChatRoom;
import websocket.websocket.service.ChatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestParam String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> login(HttpServletRequest request, HttpServletResponse response) {
        return chatService.findAllRoom(request, response);
    }

    @GetMapping("/1")
    public List<ChatRoom> session(HttpServletRequest request, HttpServletResponse response) {
        return chatService.findSession(request, response);
    }
}
