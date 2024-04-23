package desktop.models.data;

import desktop.controllers.LoginController;
import desktop.views.LoginView;

sealed public interface LoginData permits RegisterData, LoginView {
    void handleEmpty();

    void handleWrong();

    void handleSuccess();
}
