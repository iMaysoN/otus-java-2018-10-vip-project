package ru.otus.services;

import ru.otus.telegram.ToTelegram;

public interface FrontendService {
    void sendResponse(ToTelegram toTelegram);
}
