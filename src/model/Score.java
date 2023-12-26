package model;

public class Score {
    private String username;
    private int score;

    public Score() {
        this.username = "";
        this.score = 0;
    }

    public Score(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", score='" + getScore() + "'" +
                "}";
    }

}
