package views;

sealed public interface LoginData permits RegisterData, LoginView {

    void handleSuccess();
}
