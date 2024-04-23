package desktop.runtime;

import java.awt.EventQueue;

import desktop.views.LoginView;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView ex = new LoginView();
            ex.setVisible(true);
        });
    }
}
