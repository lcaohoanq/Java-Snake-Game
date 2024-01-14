package styles;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class BorderHandler {
    // Borders for containers
    public static final EmptyBorder CONTAINER = new EmptyBorder(0, 30, 0, 30);
    public static final EmptyBorder CONTAINER_SELECT_FORM = new EmptyBorder(0, 30, 0, 30);

    // Borders for logos
    public static final EmptyBorder LOGO = new EmptyBorder(50, 0, 0, 0);
    public static final EmptyBorder LOGO_SELECT_FORM = new EmptyBorder(50, 0, 0, 0);

    // Borders for username panels
    public static final EmptyBorder USERNAME_PANEL = new EmptyBorder(40, 0, 40, 0);
    public static final EmptyBorder USERNAME_INPUT = new EmptyBorder(0, 10, 0, 10);

    // Borders for password panels
    public static final EmptyBorder PASSWORD_PANEL = new EmptyBorder(40, 0, 40, 0);
    public static final EmptyBorder PASSWORD_INPUT = new EmptyBorder(0, 10, 0, 10);

    // Borders for labels
    public static final EmptyBorder JLABEL = new EmptyBorder(0, 0, 10, 0);

    // Borders for buttons
    public static final EmptyBorder JBUTTON = new EmptyBorder(30, 0, 20, 0);

    // Borders for other options
    public static final EmptyBorder OTHERS = new EmptyBorder(0, 0, 15, 0);

    // Borders for line inputs
    public static final Border LINE_BORDER_INPUT_FIELD = BorderFactory.createLineBorder(Color.BLACK, 1);
    public static final Border LINE_BORDER_MENU_BAR = BorderFactory.createLineBorder(Color.WHITE, 1);
    public static final Border LINE_BORDER_TEST = BorderFactory.createLineBorder(Color.RED, 1);

    // Borders for select forms
    public static final EmptyBorder TITLE_SELECT_FORM = new EmptyBorder(20, 0, 30, 0);
    public static final EmptyBorder MIDDLE_ZONE_SELECT_FORM = new EmptyBorder(10, 0, 10, 0);

    // My frame
    public static final EmptyBorder TITLE = new EmptyBorder(20, 0, 0, 0);
    public static final EmptyBorder MIDDLE = new EmptyBorder(10, 0, 10, 0);
    public static final EmptyBorder MID_LABEL = new EmptyBorder(0, 0, 10, 0);
    public static final EmptyBorder BUTTON = new EmptyBorder(30, 0, 20, 0);
    public static final EmptyBorder MID_FIELD = new EmptyBorder(0, 20, 0, 20);

}