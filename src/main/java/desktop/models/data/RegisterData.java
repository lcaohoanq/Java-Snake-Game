package desktop.models.data;

import desktop.controllers.RegisterController;
import desktop.views.RegisterView;

public interface RegisterData extends LoginData {
    boolean isEmpty();

    boolean isDuplicateUsername();

    void handleDuplicateUsername();
}
