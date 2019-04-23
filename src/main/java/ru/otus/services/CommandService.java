package ru.otus.services;

import ru.otus.telegram.ToTelegram;
import ru.otus.telegram.Update;

public interface CommandService {
    ToTelegram handleCommand(Update update);
}
