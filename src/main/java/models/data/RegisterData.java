package models.data;

import controllers.RegisterController;
import views.RegisterView;

public sealed interface RegisterData extends LoginData permits RegisterView {
    boolean isEmpty();

    boolean isDuplicateUsername();

    void handleDuplicateUsername();
}
