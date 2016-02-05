package janken_battle.players;

import static janken_battle.Main.Hand.*;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

public class KeisukeSoma implements JankenPlayer {


    private Hand hand = G;

    @Override
    public String getName() {
        return "KEISUKE SOMA";
    }

    /**
     * じゃんけん毎に呼ばれるメソッド
     */
    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {


        hand = G;

        return hand;
    }

    //前回相手が出して手を取得
    private Hand getPreHand(List<Hand> enemyHands){

        if (!enemyHands.isEmpty()) {
            hand = enemyHands.get(enemyHands.size()-1);
        }

        return hand;
    }

    private void check(int gameNumber) {



    }

}
