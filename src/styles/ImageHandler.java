package styles;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;

import views.LoginFormView;
import views.SelectFormView;

public class ImageHandler {
    public static final URL iconURL = LoginFormView.class.getResource("/resources/key.png");
    public static final Image img = Toolkit.getDefaultToolkit().createImage(iconURL);

    public static final URL snakeURL = SelectFormView.class.getResource("/resources/250-250.png");
    public static final Image logo = Toolkit.getDefaultToolkit().getImage(snakeURL);
    public static final ImageIcon logoIcon = new ImageIcon(logo);
}
