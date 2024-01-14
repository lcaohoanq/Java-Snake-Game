package styles;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

import views.MyFrame;

public class Images {
    public static final URL iconURL = MyFrame.class.getResource("/resources/key.png");
    public static final Image icon = Toolkit.getDefaultToolkit().createImage(iconURL);

    public static final URL snakeURL = MyFrame.class.getResource("/resources/snake.png");
    public static final Image logo = Toolkit.getDefaultToolkit().getImage(snakeURL);
    public static final ImageIcon logoIcon = new ImageIcon(logo);
}
