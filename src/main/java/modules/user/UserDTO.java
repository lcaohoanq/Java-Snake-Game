package modules.user;

public record UserDTO(String email, String phoneNumber, String firstName, String lastName, String password, String confirmPassword, int score) {

    public UserDTO(String email, String password){
        this(email, "", "", "", password, "", 0);
    }

    public UserDTO(String email,String firstName, String lastName, String password, String confirmPassword) {
        this(email, "",  firstName, lastName, password, confirmPassword, 0);
    }

    public UserDTO(String email, int score) {
        this(email,"","", "", "", "", score);
    }

}
