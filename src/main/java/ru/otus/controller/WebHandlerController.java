package ru.otus.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.frontend.ToTelegram;
import ru.otus.services.CommandService;
import ru.otus.telegram.models.input.Update;

@RestController
public class WebHandlerController {

    private final CommandService commandService;

    public WebHandlerController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping("/hook-input")
    @ResponseBody
    public String hookInput(@RequestBody Update update) {
        System.out.println("hook-input post");
        System.out.println(update);
        if (update.getMessage().getText().startsWith("/")) {
            commandService.handleCommand(update);
        }
        return "OK";
    }

    @PostMapping("/sendMessage")
    public void sendMessageTest(@RequestParam("token") String token,
                                @RequestBody ToTelegram body) {
        System.out.println(String.format("sendMessage recieved: %s - %s - %s", token, body.getChat_id(), body.getText()));
        if (body.getReply_markup() != null) {
            System.out.println("Buttons: " + body.getReply_markup());
        }
    }
}