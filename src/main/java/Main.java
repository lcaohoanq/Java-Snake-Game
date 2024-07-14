import java.awt.EventQueue;

import utils.LogsUtils;
import views.LoginView;

public class Main {

    public static void main(String[] args) {

        //create logs folder if it does not exist
        LogsUtils.ensureLogsFolderExists();

        EventQueue.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}
