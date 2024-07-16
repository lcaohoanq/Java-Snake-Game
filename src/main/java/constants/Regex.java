package constants;

public class Regex {
    public static final String NAME = "^[a-zA-Z]{1,20}$";
    public static final String PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*_=+-]).{8,20}$";
    public static final String HASHED_PASSWORD = "\\$31\\$(\\d\\d?)\\$(.{43})";
    public static final String EMAIL = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    public static final String PHONE_NUMBER = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";

    public static void main(String[] args) {
        System.out.println("hoangclw@gmail.com".matches(Regex.EMAIL));
        System.out.println("0934162561".matches(Regex.PHONE_NUMBER));
    }
}
