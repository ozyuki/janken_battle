package janken_battle.players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import janken_battle.Main.Hand;
import janken_battle.Main.JankenPlayer;

/**
 * 乱数表が生成した手を出します。
 */
public class RandomFighter implements JankenPlayer {

    @Override
    public String getName() {
        return "RandomFighter";
    }

    private static final List<Hand> hands = Collections.unmodifiableList(new ArrayList<Hand>(Arrays.asList(new Hand[] {
            Hand.G, Hand.P, Hand.G, Hand.P, Hand.C, Hand.P, Hand.G, Hand.G, Hand.P, Hand.P, Hand.G, Hand.P, Hand.G,
            Hand.P, Hand.C, Hand.P, Hand.G, Hand.C, Hand.P, Hand.C, Hand.C, Hand.G, Hand.P, Hand.C, Hand.P, Hand.P,
            Hand.G, Hand.P, Hand.P, Hand.C, Hand.C, Hand.P, Hand.P, Hand.P, Hand.G, Hand.P, Hand.C, Hand.P, Hand.P,
            Hand.C, Hand.P, Hand.C, Hand.C, Hand.G, Hand.C, Hand.C, Hand.P, Hand.P, Hand.C, Hand.C, Hand.P, Hand.P,
            Hand.P, Hand.P, Hand.G, Hand.C, Hand.G, Hand.P, Hand.G, Hand.G, Hand.P, Hand.P, Hand.G, Hand.P, Hand.G,
            Hand.G, Hand.C, Hand.C, Hand.C, Hand.G, Hand.C, Hand.G, Hand.P, Hand.P, Hand.P, Hand.C, Hand.G, Hand.P,
            Hand.G, Hand.G, Hand.G, Hand.C, Hand.P, Hand.C, Hand.P, Hand.C, Hand.C, Hand.P, Hand.G, Hand.P, Hand.G,
            Hand.G, Hand.G, Hand.P, Hand.C, Hand.C, Hand.C, Hand.P, Hand.C, Hand.G })));

    @Override
    public Hand getHand(List<Hand> myHands, List<Hand> enemyHands,int myPoint ,int enemyPoint ,int gameNumber) {
        return hands.get(myHands.size());
    }

}
