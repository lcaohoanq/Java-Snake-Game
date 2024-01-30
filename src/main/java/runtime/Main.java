package runtime;

import java.awt.EventQueue;

import views.LoginView;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView ex = new LoginView();
            ex.setVisible(true);
        });
    }
}
