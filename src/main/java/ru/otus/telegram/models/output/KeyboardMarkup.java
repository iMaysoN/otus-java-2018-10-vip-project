package ru.otus.telegram.models.output;

public interface KeyboardMarkup<T extends TelegramButton> {
    void addButton(T button);
}
