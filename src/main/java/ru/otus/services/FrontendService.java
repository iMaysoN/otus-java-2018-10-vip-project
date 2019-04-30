package ru.otus.services;

public interface FrontendService {
    void sendResponse(String chatId, String text);

    void sendResponse(String chatId, String text, String keyboard);
}
