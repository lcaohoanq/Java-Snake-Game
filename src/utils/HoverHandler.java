package utils;

import javax.swing.*;

public interface HoverHandler {
    void setHoverUsername(boolean isInside, String mode);

    void setHoverPassword(boolean isInside, String mode);

    void setHoverConfirmPassword(boolean isInside, String mode);

    void setHoverButton(boolean isInside, String mode);

    void setHoverButton(boolean isInside, String mode, JButton button);

    void setHoverOther(boolean isInside);
}
