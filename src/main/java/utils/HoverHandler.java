package utils;

import views.LoginView;
import views.MenuView;
import views.MyFrame;
import views.RegisterView;

import javax.swing.*;

sealed public interface HoverHandler permits LoginView, MenuView, MenuView.MenuModern, MyFrame, RegisterView {
    void setHoverUsername(boolean isInside, String mode);

    void setHoverPassword(boolean isInside, String mode);

    void setHoverConfirmPassword(boolean isInside, String mode);

    void setHoverButton(boolean isInside, String mode);

    void setHoverButton(boolean isInside, String mode, JButton button);

    void setHoverOther(boolean isInside);
}
