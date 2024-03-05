package runtime;

import java.awt.EventQueue;

import services.AppServer;
import views.LoginView;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            LoginView ex = new LoginView();
            ex.setVisible(true);
        });
        //Start the server
        SwingUtilities.invokeLater(AppServer::new);
    }
}
