package models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String username;
    private String password;
    private int score;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, int score) {
        this.username = username;
        this.score = score;
    }
}
