package ru.otus.telegram.models.output;

import java.util.ArrayList;
import java.util.List;

abstract public class KeyboardMarkup<T> {
    List<T> buttons;

    public KeyboardMarkup() {
        this.buttons = new ArrayList<>();
    }

    public void addButton(T button) {
        this.buttons.add(button);
    }
}
