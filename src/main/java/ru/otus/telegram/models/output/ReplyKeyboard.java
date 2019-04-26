package ru.otus.telegram.models.output;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboard implements KeyboardMarkup<ReplyButton> {
    private List<ReplyButton> keyboard;

    public ReplyKeyboard() {
        this.keyboard = new ArrayList<>();
    }

    @Override
    public void addButton(ReplyButton button) {
        this.keyboard.add(button);
    }
    private boolean resize_keyboard = true;

    public ReplyKeyboard(List<ReplyButton> buttons) {
        this.keyboard = buttons;
    }

    public List<ReplyButton> getButtons() {
        return keyboard;
    }

    public void addButtons(List<ReplyButton> buttons) {
        this.keyboard.addAll(buttons);
    }

    public void setButtons(List<ReplyButton> buttons) {
        this.keyboard = buttons;
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
                "buttons=[" + keyboard +
                "]}";
    }
}
