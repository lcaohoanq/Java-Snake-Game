package views.game.factory;

import models.UserScore;
import views.base.Board;
import views.game.Apartment;

/**
 * Concrete factory implementation for creating Apartment boards.
 */
public class ApartmentFactory implements BoardFactory {
    @Override
    public Board createBoard(UserScore user) {
        return new Apartment(user);
    }
}