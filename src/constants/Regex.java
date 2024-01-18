package constants;

public class Regex {
    public static final String USERNAME = "^[a-zA-Z0-9]{1,20}$";
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}$";
}
