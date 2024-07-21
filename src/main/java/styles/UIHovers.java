package styles;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import views.AbstractView;

public record UIHovers<T extends AbstractView>(T frame) {

    private void applyHoverEffect(JComponent component, boolean isInside, String mode) {
        Color normalColor;
        Color hoverColor;
        Font normalFont = UIFonts.OTHERS;
        Font hoverFont = UIFonts.OTHERS_HOVER;

        if (component instanceof JTextField) {
            normalColor =
                mode.equals("light") ? UIColors.SECONDARY_COLOR_L : UIColors.SECONDARY_COLOR_D;
            hoverColor = mode.equals("light") ? UIColors.SECONDARY_COLOR_L_HOVER
                : UIColors.SECONDARY_COLOR_D_HOVER;
            component.setBackground(isInside ? hoverColor : normalColor);
        } else if (component instanceof JButton) {
            //button contain text only, border is null
            if (((JButton) component).getText().equals(UILabels.SIGN_IN_HERE)
                || ((JButton) component).getText().equals(UILabels.SIGN_UP_HERE)
                || ((JButton) component).getText().equals(UILabels.FORGOT_PASSWORD)) {
                component.setFont(isInside ? hoverFont : normalFont);
            } else {
                normalColor = mode.equals("light") ? UIColors.TEXT_COLOR_L : UIColors.TEXT_COLOR_D;
                hoverColor = mode.equals("light") ? UIColors.TEXT_COLOR_L_HOVER : UIColors.TEXT_COLOR_D_HOVER;
                component.setFont(isInside ? hoverFont : normalFont);
                component.setBackground(isInside ? hoverColor : normalColor);
            }
        }

    }

    public void setHoverEmail(boolean isInside, String mode) {
        applyHoverEffect(frame.getJTextField_Right_Middle_Email(), isInside, mode);
    }

    public void setHoverFirstName(boolean isInside, String mode) {
        applyHoverEffect(frame.getJTextField_Right_Middle_FirstName(), isInside, mode);
    }

    public void setHoverLastName(boolean isInside, String mode) {
        applyHoverEffect(frame.getJTextField_Right_Middle_LastName(), isInside, mode);
    }

    public void setHoverPassword(boolean isInside, String mode) {
        applyHoverEffect(frame.getJPasswordField_Right_Middle_Password(), isInside, mode);
    }

    public void setHoverConfirmPassword(boolean isInside, String mode) {
        applyHoverEffect(frame.getJPasswordField_Right_Middle_Confirm_Password(), isInside, mode);
    }

    public void setHoverButton(boolean isInside, String mode) {
        applyHoverEffect(frame.getJButton_Right_Bottom_Submit(), isInside, mode);
    }

    public void setHoverOther(boolean isInside) {
        applyHoverEffect(frame.getJButton_Right_Bottom_Others(), isInside, "light");
    }

    public void setHoverForgotPassword(boolean isInside) {
        applyHoverEffect(frame.getJButton_Right_Bottom_Forgot_Password(), isInside, "light");
    }

}