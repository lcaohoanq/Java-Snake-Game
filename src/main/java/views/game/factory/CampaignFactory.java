package views.game.factory;

import models.UserScore;
import views.base.Board;
import views.game.Campaign;

/**
 * Concrete factory implementation for creating Campaign boards.
 */
public class CampaignFactory implements BoardFactory {
    @Override
    public Board createBoard(UserScore user) {
        return new Campaign(user);
    }
}