package models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserScore {

    private Integer userId;
    private String username;
    private String email;
    private String password;
    private int score;
    private LocalDateTime timestamp;

    public UserScore(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
