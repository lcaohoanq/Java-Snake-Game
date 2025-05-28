package views.game;

import models.UserScore;
import views.base.Board;

public class Campaign extends Board {

    public Campaign(UserScore user) {
        super(user); // Pass user to parent class
    }

    @Override
    protected void checkCollision() {

    }

    @Override
    protected void locateApple() {

    }

    @Override
    protected void locateBigApple() {

    }
}
