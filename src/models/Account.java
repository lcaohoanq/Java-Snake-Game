package models;

public class Account {
    private String username;
    private String password;
    private int score;

    public Account() {
        this.username = "";
        this.password = "";
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, int score) {
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public Account(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }
}
