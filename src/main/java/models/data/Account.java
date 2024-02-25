package models.data;

public record Account(String username, String password, String confirmPassword, int score) {

    public Account(String username, String password) {
        this(username, password, "", 0);
    }

    // use at Register view to create new account and
    // check if password and confirmPassword are the same or not at RegisterController
    public Account(String username, String password, String confirmPassword) {
        this(username, password, confirmPassword, 0);
    }

    public Account(String username, int score) {
        this(username, "", "", score);
    }
}
