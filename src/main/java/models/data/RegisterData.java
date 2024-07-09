package models.data;

import views.RegisterView;

public sealed interface RegisterData extends LoginData permits RegisterView {
    boolean isEmpty();

    boolean isDuplicateEmail();

    void handleDuplicateEmail();
}
