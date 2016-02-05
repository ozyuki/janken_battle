package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * チョキをひたすら出し続けます。
 */
public class AlienBaltan implements JankenPlayer {

    @Override
    public String getName() {
        return "AlienBaltan";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        return Hand.C;
    }

}
