package desktop.models.data;

import desktop.controllers.RegisterController;
import desktop.views.RegisterView;

public sealed interface RegisterData extends LoginData permits RegisterView {
    boolean isEmpty();

    boolean isDuplicateUsername();

    void handleDuplicateUsername();
}
