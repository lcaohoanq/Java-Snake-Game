package runtime;

import utils.LoginView;

import java.awt.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView ex = new LoginView();
            ex.setVisible(true);
        });
    }
}
