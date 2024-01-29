package utils;

import views.Toggle;

public interface ToggleHandler {
    Toggle toggleButton = new Toggle();

    void changeColorBaseOnToggle();

    default boolean getStatusToggle() {
        return toggleButton.isSelected() ? true : false;
    }
}
