package ru.otus.services;

import ru.otus.telegram.models.input.Update;

public interface CommandService {
    void handleCommand(Update update);
}
