package desktop.utils;

import desktop.views.LoginView;
import desktop.views.MenuView;
import desktop.views.MyFrame;
import desktop.views.RegisterView;

import javax.swing.*;

sealed public interface HoverHandler permits LoginView, MenuView, MenuView.MenuModern, MyFrame, RegisterView {
    void setHoverUsername(boolean isInside, String mode);

    void setHoverPassword(boolean isInside, String mode);

    void setHoverConfirmPassword(boolean isInside, String mode);

    void setHoverButton(boolean isInside, String mode);

    void setHoverButton(boolean isInside, String mode, JButton button);

    void setHoverOther(boolean isInside);
}
