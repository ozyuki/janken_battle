package janken_battle.players;

import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * あなたです。
 * TODO アルゴリズムの説明や工夫ポイントがあればここに記入してください。
 *
 */
public class YourCode implements JankenPlayer {

    @Override
    public String getName() {
        // TODO
        // プレイヤーの名前を半角英数で入力してください。
        return "YourCode";
    }

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        // TODO
        // 今は例として、最初の1回はグー、1つ前に相手が出した手がチョキならパー、
        // それ以外ならチョキを出すように実装されています。
        // このメソッドの実装を変更して勝利を目指してください。
        if (myHands.isEmpty()) {
            return Hand.G;
        } else {
            Hand beforeEnemyHand = enemyHands.get(enemyHands.size() - 1);
            if (beforeEnemyHand == Hand.C) {
                return Hand.P;
            } else {
                return Hand.C;
            }
        }
    }
}
