package models;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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

    // // prevent duplicate username score
    // @Override
    // public boolean equals(Object o) {
    // if (this == o)
    // return true;
    // if (o == null || getClass() != o.getClass())
    // return false;
    // Score otherScore = (Score) o;
    // return Objects.equals(username, otherScore.username) && score ==
    // otherScore.score;
    // }

    // @Override
    // public int hashCode() {
    // return Objects.hash(username, score);
    // }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                ", score='" + getScore() + "'" +
                "}";
    }

    public static void main(String[] args) {
        Map<String, Score> map = new java.util.HashMap<>();
        map.put("a", new Score("a", 1));
        map.put("b", new Score("b", 2));
        map.put("c", new Score("c", 3));
        map.put("a", new Score("a", 4));
        for (Map.Entry<String, Score> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
