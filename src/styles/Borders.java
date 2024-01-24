package styles;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Borders {
    // Borders for containers
    public static final EmptyBorder CONTAINER = new EmptyBorder(0, 30, 0, 30);

    // Borders for line inputs
    public static final Border LINE_MENU_BAR = BorderFactory.createLineBorder(Color.WHITE, 1);

    // My frame
    public static final EmptyBorder TITLE = new EmptyBorder(20, 0, 0, 0);
    public static final EmptyBorder MIDDLE = new EmptyBorder(10, 0, 10, 0);
    public static final EmptyBorder MID_LABEL = new EmptyBorder(0, 0, 10, 0);
    public static final EmptyBorder BUTTON = new EmptyBorder(30, 0, 20, 0);
    public static final EmptyBorder MID_FIELD = new EmptyBorder(0, 20, 0, 20);
    public static final EmptyBorder BOTTOM_SCORE_PROGRESS_BAR = new EmptyBorder(0, 10, 20, 10);
}