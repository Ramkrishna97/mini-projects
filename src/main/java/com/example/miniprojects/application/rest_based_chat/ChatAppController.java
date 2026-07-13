package com.example.miniprojects.application.rest_based_chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/chat")
public class ChatAppController {

    private final ChatBody chat = new ChatBody("User1", "");

    @GetMapping()
    public String home(Model model) {
        model.addAttribute("chat", chat);
        return "chat";
    }

    @PostMapping("/message")
    @ResponseBody
    public String message(@RequestBody ChatRequest request) {

        chat.setMessage(
                chat.getMessage()
                        + "\n"
                        + chat.getUser()
                        + " : "
                        + request.getMessage()
        );

        System.out.println(chat.getMessage());

        chat.switchUser();

        return "OK";
    }

    @GetMapping("/conversation")
    @ResponseBody
    public String conversation() {
        return chat.getMessage();
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ChatRequest {

    private String message;

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ChatBody {
    private String user;
    private String message;

    public void switchUser() {
        if ("User1".equals(user)) {
            user = "User2";
        } else {
            user = "User1";
        }
    }

}