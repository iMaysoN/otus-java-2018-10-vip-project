package ru.otus.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.frontend.FrontendService;
import ru.otus.telegram.CommandHandler;
import ru.otus.telegram.ToTelegram;
import ru.otus.telegram.Update;

@RestController
public class TelegramController {

    private final CommandHandler commandHandler;
    private final FrontendService frontendService;

    public TelegramController(CommandHandler commandHandler,
                              FrontendService frontendService) {
        this.commandHandler = commandHandler;
        this.frontendService = frontendService;
    }

    @PostMapping("/hook-input")
    @ResponseBody
    public String hookInput(@RequestBody Update update) {
        System.out.println(update);
        System.out.println("catched");
        if (update.getMessage().getText().startsWith("/")) {
            final ToTelegram toTelegram = commandHandler.handleCommand(update);
            frontendService.sendResponse(toTelegram);
        }
        return "test";
    }

    @GetMapping("/sendMessage")
    public void sendMessageTest(@RequestParam("token") String token,
                                @RequestParam("chat_id") String chat_id,
                                @RequestParam("text") String text) {
        System.out.println(String.format("Recieve: %s - %s - %s", token, chat_id, text));
    }
}