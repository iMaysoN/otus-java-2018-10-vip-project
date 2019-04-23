package ru.otus.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.services.CommandService;
import ru.otus.services.FrontendService;
import ru.otus.telegram.ToTelegram;
import ru.otus.telegram.Update;

@RestController
public class WebHandlerController {

    private final CommandService commandService;
    private final FrontendService frontendService;

    public WebHandlerController(CommandService commandService,
                                FrontendService frontendService) {
        this.commandService = commandService;
        this.frontendService = frontendService;
    }

    @PostMapping("/hook-input")
    @ResponseBody
    public String hookInput(@RequestBody Update update) {
        System.out.println("hook-input post");
        System.out.println(update);
        if (update.getMessage().getText().startsWith("/")) {
            final ToTelegram toTelegram = commandService.handleCommand(update);
            frontendService.sendResponse(toTelegram);
        }
        return "test";
    }

    @GetMapping("/sendMessage")
    public void sendMessageTest(@RequestParam("token") String token,
                                @RequestParam("chat_id") String chat_id,
                                @RequestParam("text") String text) {
        System.out.println(String.format("sendMessage recieved: %s - %s - %s", token, chat_id, text));
    }
}