package models.data;

public record Account(String username, String password, String confirmPassword, int score) {

    public Account(String username, String password) {
        this(username, password, "", 0);
    }

    public Account(String username, String password, String confirmPassword) {
        this(username, password, confirmPassword, 0);
    }

    public Account(String username, int score) {
        this(username, "", "", score);
    }
}
