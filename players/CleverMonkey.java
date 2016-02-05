package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * 1回前に勝っていたら前回自分が出した手と同じ手を出します。
 * 1回前に負けていたら前回自分が出さなかった手のうちどちらかを出します。
 * 1回前にあいこだったら3つの手のうちどれかを出します。
 */
public class CleverMonkey implements JankenPlayer {

    @Override
    public String getName() {
        return "CleverMonkey";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        if (myHands.isEmpty()) {
            // まだ一回も戦っていない→とりあえずグー
            return Hand.G;
        }
        Hand beforeMyHand = myHands.get(myHands.size() - 1);
        Hand beforeEnemyHand = enemyHands.get(enemyHands.size() - 1);
        if ((beforeMyHand == Hand.G && beforeEnemyHand == Hand.C)
                || (beforeMyHand == Hand.C && beforeEnemyHand == Hand.P)
                || (beforeMyHand == Hand.P && beforeEnemyHand == Hand.G)) {
            // 前回勝っている→前回と同じ手
            return beforeMyHand;
        }
        if ((beforeMyHand == Hand.G && beforeEnemyHand == Hand.G)
                || (beforeMyHand == Hand.C && beforeEnemyHand == Hand.C)
                || (beforeMyHand == Hand.P && beforeEnemyHand == Hand.P)) {
            // 前回あいこ→3つの手から選ぶ
            switch (myHands.size() % 3) {
                case 0:
                    return Hand.G;
                case 1:
                    return Hand.C;
                case 2:
                default:
                    return Hand.P;
            }
        }
        // 前回負けている→前回以外の2つの手から選ぶ
        if (beforeMyHand == Hand.G
                && beforeEnemyHand == Hand.P) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.C;
                case 1:
                default:
                    return Hand.P;
            }
        }
        if (beforeMyHand == Hand.C
                && beforeEnemyHand == Hand.G) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.G;
                case 1:
                default:
                    return Hand.P;
            }
        }
        if (beforeMyHand == Hand.P
                && beforeEnemyHand == Hand.C) {
            switch (myHands.size() % 2) {
                case 0:
                    return Hand.G;
                case 1:
                default:
                    return Hand.C;
            }
        }
        throw new RuntimeException("ここには来ない");
    }
}
