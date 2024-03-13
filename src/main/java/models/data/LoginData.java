package models.data;

import controllers.LoginController;
import views.LoginView;
import views.RegisterView;

sealed public interface LoginData permits RegisterData, LoginView {
    void handleEmpty();

    void handleWrong();

    void handleSuccess();
}
