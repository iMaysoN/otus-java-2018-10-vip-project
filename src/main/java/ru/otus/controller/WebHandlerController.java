package ru.otus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.otus.frontend.ToTelegram;
import ru.otus.services.CommandService;
import ru.otus.telegram.models.input.Update;

@RestController
public class WebHandlerController {
    private final static Logger logger = LoggerFactory.getLogger(WebHandlerController.class);

    private final CommandService commandService;

    public WebHandlerController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping("/hook-input")
    @ResponseBody
    public String hookInput(@RequestBody Update update) {
        logger.info("DEMO: Receive /hook-input");
        logger.info(update.toString());
        if (update.getMessage().getText().startsWith("/")) {
            commandService.handleCommand(update);
        }
        return "OK";
    }

    @PostMapping("/sendMessage")
    public void sendMessageTest(@RequestParam("token") String token,
                                @RequestBody ToTelegram body) {
        logger.info(String.format("DEMO: Receive /sendMessage: %s - %s - %s", token, body.getChat_id(), body.getText()));
        if (body.getReply_markup() != null) {
            logger.info("DEMO: With buttons: " + body.getReply_markup());
        }
    }
}