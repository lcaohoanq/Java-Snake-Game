package views;

public sealed interface RegisterData extends LoginData permits RegisterView {
    boolean isEmpty();

    boolean isDuplicateEmail();

}
