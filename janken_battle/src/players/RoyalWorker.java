package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * 相手が一番多く出した手に勝つ手を出します。
 */
public class RoyalWorker implements JankenPlayer {

    @Override
    public String getName() {
        return "RoyalWorker";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        int enemyG = 0;
        int enemyC = 0;
        int enemyP = 0;
        for (Hand enemyHand : enemyHands) {
            if (enemyHand == Hand.G) {
                enemyG++;
            } else if (enemyHand == Hand.C) {
                enemyC++;
            } else if (enemyHand == Hand.P) {
                enemyP++;
            }
        }
        if (enemyG > enemyC) {
            if (enemyG > enemyP) {
                // 相手はグーが一番多い→自分はパー
                return Hand.P;
            } else {
                // 相手はパーが一番多い→自分はチョキ
                return Hand.C;
            }
        } else {
            if (enemyC > enemyP) {
                // 相手はチョキが一番多い→自分はグー
                return Hand.G;
            } else {
                // 相手はパーが一番多い→自分はチョキ
                return Hand.C;
            }
        }
    }
}
