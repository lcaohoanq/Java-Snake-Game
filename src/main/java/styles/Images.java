package styles;

import constants.Paths;
import utils.MyFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Images {
    public static final URL iconURL = MyFrame.class.getResource(Paths.URL_KEY_ICON);
    public static final Image icon = Toolkit.getDefaultToolkit().createImage(iconURL);

    public static final URL snakeURL = MyFrame.class.getResource(Paths.URL_SNAKE_LOGO);
    public static final Image logo = Toolkit.getDefaultToolkit().getImage(snakeURL);
    public static final ImageIcon logoIcon = new ImageIcon(logo);


}
