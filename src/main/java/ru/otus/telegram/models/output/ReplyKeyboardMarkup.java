package ru.otus.telegram.models.output;

import java.util.List;

public class ReplyKeyboardMarkup extends KeyboardMarkup<ReplyButton> {
    private boolean resize_keyboard = true;

    public ReplyKeyboardMarkup() {
        super();
    }

    public ReplyKeyboardMarkup(List<ReplyButton> buttons) {
        this.buttons = buttons;
    }

    public List<ReplyButton> getButtons() {
        return buttons;
    }

    public void addButtons(List<ReplyButton> buttons) {
        this.buttons.addAll(buttons);
    }

    public void setButtons(List<ReplyButton> buttons) {
        this.buttons = buttons;
    }

    public boolean isResize_keyboard() {
        return resize_keyboard;
    }

    public void setResize_keyboard(boolean resize_keyboard) {
        this.resize_keyboard = resize_keyboard;
    }

    @Override
    public String toString() {
        return "ReplyKeyboardMarkup{" +
                "buttons=[" + buttons +
                "]}";
    }
}
