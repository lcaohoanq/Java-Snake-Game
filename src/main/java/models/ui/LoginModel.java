package models.ui;

public record LoginModel(String username, String password) {
    public LoginModel() {
        this("", "");
    }
}
