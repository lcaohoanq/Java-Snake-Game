package models.data;

import java.time.LocalDateTime;

public record Account(String username, String password, String confirmPassword, int score, LocalDateTime registerDate) {

    public Account(String username, String password) {
        this(username, password, "", 0, null);
    }

    public Account(String username, String password, String confirmPassword) {
        this(username, password, confirmPassword, 0, LocalDateTime.now());
    }

    public Account(String username, int score) {
        this(username, "", "", score, null);
    }
}
