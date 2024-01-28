package utils;

public interface HoverHandler {
    void setHoverUsername(boolean isInside, String mode);

    void setHoverPassword(boolean isInside, String mode);

    void setHoverConfirmPassword(boolean isInside, String mode);

    void setHoverButton(boolean isInside, String mode);

    void setHoverOther(boolean isInside);
}
