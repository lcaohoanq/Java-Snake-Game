package views.game.factory;

import models.UserScore;
import views.base.Board;
import views.game.Tunnel;

/**
 * Concrete factory implementation for creating Tunnel boards.
 */
public class TunnelFactory implements BoardFactory {
    @Override
    public Board createBoard(UserScore user) {
        return new Tunnel(user);
    }
}