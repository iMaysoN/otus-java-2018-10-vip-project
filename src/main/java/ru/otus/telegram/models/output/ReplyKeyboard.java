package ru.otus.telegram.models.output;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboard implements KeyboardMarkup<ReplyButton> {
    //Array of Array of KeyboardButton
    private List<List<ReplyButton>> keyboard;

    public ReplyKeyboard() {
        this.keyboard = new ArrayList<>();
    }

    @Override
    public void addButton(ReplyButton button) {
        this.keyboard.get(0).add(button);
    }

    private boolean resize_keyboard = true;

    public ReplyKeyboard(List<ReplyButton> buttons) {
        this.keyboard = new ArrayList<>() {{
            add(buttons);
        }};
    }

    public List<List<ReplyButton>> getButtons() {
        return keyboard;
    }

    public void addButtons(List<ReplyButton> buttons) {
        this.keyboard.get(0).addAll(buttons);
    }

    public void setButtons(List<ReplyButton> buttons) {
        this.keyboard = new ArrayList<>() {{
            add(buttons);
        }};
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
