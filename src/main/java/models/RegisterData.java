package models;

public interface RegisterData extends LoginData {
    boolean isEmpty(String username, String password, String confirmPassword);
    boolean isDuplicateUsername(String username);
    void handleDuplicateUsername();
}
