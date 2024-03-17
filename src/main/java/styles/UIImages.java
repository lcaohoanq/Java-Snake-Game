package styles;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

import constants.ResourcePaths;
import views.MyFrame;

public class UIImages {
    public static final URL iconURL = MyFrame.class.getResource(ResourcePaths.URL_KEY_ICON);
    public static final Image icon = Toolkit.getDefaultToolkit().createImage(iconURL);

    public static final URL snakeURL = MyFrame.class.getResource(ResourcePaths.URL_SNAKE_LOGO);
    public static final Image logo = Toolkit.getDefaultToolkit().getImage(snakeURL);
    public static final ImageIcon logoIcon = new ImageIcon(logo);


}
