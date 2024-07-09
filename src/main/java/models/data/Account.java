package models.data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Account(String email, String phoneNumber, String firstName, String lastName, String password, String confirmPassword, int score) {

    public Account(String email, String password){
        this(email, "", "", "", password, "", 0);
    }

    public Account(String email,String firstName, String lastName, String password, String confirmPassword) {
        this(email, "",  firstName, lastName, password, confirmPassword, 0);
    }

    public Account(String email, int score) {
        this(email,"","", "", "", "", score);
    }

}
