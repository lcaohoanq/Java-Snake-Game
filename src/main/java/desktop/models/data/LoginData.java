package desktop.models.data;

import desktop.controllers.LoginController;
import desktop.views.LoginView;

public interface LoginData {
    void handleEmpty();

    void handleWrong();

    void handleSuccess();
}
