package ru.otus.telegram.models.output;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboard implements KeyboardMarkup<InlineButton> {
    //Tg documentation: Array of Array of InlineKeyboardButton
    private List<List<InlineButton>> inline_keyboard;

    public InlineKeyboard() {

        this.inline_keyboard = new ArrayList<>() {{
            add(new ArrayList<>());
        }};
    }

    @Override
    public void addButton(InlineButton button) {
        this.inline_keyboard.get(0).add(button);
    }

    public List<List<InlineButton>> getInline_keyboard() {
        return inline_keyboard;
    }

    public void setInline_keyboard(List<InlineButton> inline_keyboard) {
        this.inline_keyboard = new ArrayList<>() {{
            add(inline_keyboard);
        }};
    }
}
