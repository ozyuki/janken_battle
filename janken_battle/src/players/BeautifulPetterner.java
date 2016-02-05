package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * チョキ→パー→グー→パー→チョキを繰り返します。
 */
public class BeautifulPetterner implements JankenPlayer {

    @Override
    public String getName() {
        return "BeautifulPetterner";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        switch (myHands.size() % 5) {
            case 0:
                return Hand.C;
            case 1:
                return Hand.P;
            case 2:
                return Hand.G;
            case 3:
                return Hand.P;
            case 4:
            default:
                return Hand.C;
        }
    }
}
