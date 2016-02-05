package janken_battle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    /**
     * じゃんけんの手を示します。
     */
    public enum Hand {
        /** グー */
        G,
        /** チョキ */
        C,
        /** パー */
        P
    }

    /**
     * じゃんけんプレイヤーであることを示すインターフェースです。
     */
    public interface JankenPlayer {

        /**
         * プレイヤーの名前を教えてください。半角英数字でお願いします。
         */
        String getName();

        /**
         * 一回のじゃんけんごとに呼ばれますので、次に自分が出す手を返すように実装してください。<br>
         * nullは返さずに、必ずHand.G、Hand.C、Hand.Pのどれかを返すようにしてください。
         */
        Hand getHand(List<Hand> myHands, List<Hand> enemyHands ,int myPoint ,int enemyPoint ,int gameNumber);

    }

    /**
     * じゃんけんの戦況を示すクラスです。
     */
    public static class JankenStatus {

        private int myPoint = 0;
        private int enemyPoint = 0;
        private List<Hand> myHands = new ArrayList<Hand>();
        private List<Hand> enemyHands = new ArrayList<Hand>();

        List<Hand> getMyHands() {
            return Collections.unmodifiableList(myHands);
        }

        List<Hand> getEnemyHands() {
            return Collections.unmodifiableList(enemyHands);
        }

        int getMyPoint() {
            return myPoint;
        }

        int getEnemyPoint() {
            return enemyPoint;
        }

        void myPointAdd(int addPoint) {
            myPoint = myPoint
                    + addPoint;
        }

        void enemyPointAdd(int addPoint) {
            enemyPoint = enemyPoint
                    + addPoint;
        }

        void myHandsAdd(Hand myHand) {
            myHands.add(myHand);
        }

        void enemyHandsAdd(Hand enemyHand) {
            enemyHands.add(enemyHand);
        }

    }

    private static class JankenBattleField {

        private final JankenPlayer me;
        private final JankenPlayer enemy;
        private final JankenStatus status;

        JankenBattleField(JankenPlayer me, JankenPlayer enemy) {
            this.me = me;
            this.enemy = enemy;
            this.status = new JankenStatus();
        }

        void battle() {
            for (int i = 0; i < 100; i++) {
                oneBout(i+1);
            }
        }

        /**
         * 1回じゃんけんします。
         */
        private void oneBout(int gameNumber) {
            Hand myHand = me.getHand(status.getMyHands(), status.getEnemyHands(), status.getMyPoint(), status.getEnemyPoint(),gameNumber);
            Hand enemyHand = enemy.getHand(status.getEnemyHands(), status.getMyHands(), status.getEnemyPoint(), status.getMyPoint(),gameNumber);
            status.myHandsAdd(myHand);
            status.enemyHandsAdd(enemyHand);
            if (myHand == Hand.G) {
                if (enemyHand == Hand.C) {
                    status.myPointAdd(3);
                } else if (enemyHand == Hand.P) {
                    status.enemyPointAdd(6);
                }
            } else if (myHand == Hand.C) {
                if (enemyHand == Hand.G) {
                    status.enemyPointAdd(3);
                } else if (enemyHand == Hand.P) {
                    status.myPointAdd(6);
                }
            } else if (myHand == Hand.P) {
                if (enemyHand == Hand.G) {
                    status.myPointAdd(6);
                } else if (enemyHand == Hand.C) {
                    status.enemyPointAdd(6);
                }
            }
        }

        /**
         * 結果表示用の文字列を取得します。
         */
        String getPrintString() {
            StringBuilder sb = new StringBuilder();
            sb.append(me.getName());
            sb.append(" VS ");
            sb.append(enemy.getName());
            sb.append(" : ");
            sb.append(status.getMyPoint());
            sb.append(" - ");
            sb.append(status.getEnemyPoint());
            sb.append(getWinner());
            sb.append("\r\n");
            sb.append(getHandsListString(me.getName(), status.getMyHands()));
            sb.append("\r\n");
            sb.append(getHandsListString(enemy.getName(), status.getEnemyHands()));
            sb.append("\r\n");
            return sb.toString();
        }

        private String getWinner(){
            String winner;

            if (status.getMyPoint() > status.getEnemyPoint()) {
                winner = " ★WINNER : "+me.getName()+"★";

            } else if(status.getEnemyPoint() > status.getMyPoint()) {
                winner = " ★WINNER : "+enemy.getName()+"★";

            } else {
                winner = "▲Draw...▲";
            }
            return winner;
        }


        private static String getHandsListString(String name, List<Hand> hands) {
            StringBuilder sb = new StringBuilder();
            sb.append((name + "                    ").substring(0, 20));
            sb.append(":");
            for (int i = 0; i < hands.size(); i++) {
                if (i % 10 == 0) {
                    sb.append(" ");
                }
                sb.append(hands.get(i));
            }
            return sb.toString();
        }

        /**
         * 勝負状況を返します。
         */
        JankenStatus getJankenStatus() {
            return status;
        }

    }

    /**
     * 実行すると、じゃんけん大会の結果が標準出力されます。
     *
     * @throws IOException
     */

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
    InstantiationException {
        printBattleStart();
        List<Class<JankenPlayer>> players = getPlayers();
        String[][] result = new String[players.size()][players.size()];
        int[] winnerPoint = new int[players.size()];
        int[] point = new int[players.size()];
        for (int i = 0; i < players.size(); i++) {
            for (int j = i; j < players.size(); j++) {
                if (j == i) {
                    result[i][j] = "＼";
                } else {
                    JankenStatus status = battle(players.get(i).newInstance(), players.get(j).newInstance());
                    if (status.getMyPoint() > status.getEnemyPoint()) {
                        result[i][j] = "○";
                        result[j][i] = "●";
                        winnerPoint[i] = winnerPoint[i] + 3;
                    } else if (status.getMyPoint() < status.getEnemyPoint()) {
                        result[i][j] = "●";
                        result[j][i] = "○";
                        winnerPoint[j] = winnerPoint[j] + 3;
                    } else {
                        result[i][j] = "△";
                        result[j][i] = "△";
                        winnerPoint[i] = winnerPoint[i] + 1;
                        winnerPoint[j] = winnerPoint[j] + 1;
                    }
                    point[i] = point[i]
                            + status.getMyPoint();
                    point[j] = point[j]
                            + status.getEnemyPoint();
                }
            }
        }
        printBattleResult(players, result, winnerPoint, point);
    }

    private static void printBattleStart() {
        System.out.println("battle start!");
        System.out.println("");
    }

    @SuppressWarnings("unchecked")
    private static List<Class<JankenPlayer>> getPlayers() throws ClassNotFoundException {
        List<Class<JankenPlayer>> players = new ArrayList<Class<JankenPlayer>>();
        File dir = new File(Thread.currentThread().getContextClassLoader().getResource(
                "janken_battle.players".replace('.', '/')).getFile());
        for (String path : dir.list()) {
            players.add((Class<JankenPlayer>) Thread.currentThread().getContextClassLoader().loadClass(
                    "janken_battle.players"
                            + "."
                            + path.substring(0, path.length()
                                    - ".class".length())));
        }
        return players;
    }

    private static JankenStatus battle(JankenPlayer me, JankenPlayer enemy) {
        JankenBattleField jankenBattleField = new JankenBattleField(me, enemy);
        jankenBattleField.battle();
        System.out.println(jankenBattleField.getPrintString());
        return jankenBattleField.getJankenStatus();
    }

    private static void printBattleResult(List<Class<JankenPlayer>> players, String[][] result, int[] winnerPoint,
            int[] point) throws InstantiationException, IllegalAccessException {
        StringBuilder sb = new StringBuilder("                       |");
        for (int i = 0; i < players.size(); i++) {
            sb.append(" ");
            sb.append(i + 1);
        }
        sb.append("|");
        sb.append("WINP");
        sb.append("|");
        sb.append("TOTALP");
        sb.append("|");
        sb.append("\r\n");
        sb.append("------------------------");
        for (int i = 0; i < players.size(); i++) {
            sb.append("--");
        }
        sb.append("-------------");
        sb.append("\r\n");
        for (int i = 0; i < players.size(); i++) {
            sb.append((String.valueOf(i + 1)
                    + "."
                    + players.get(i).newInstance().getName() + "                       ").substring(0, 23));
            sb.append("|");
            for (int j = 0; j < players.size(); j++) {
                sb.append(result[i][j]);
            }
            sb.append("|");
            sb.append(("    " + winnerPoint[i]).substring(String.valueOf(winnerPoint[i]).length()));
            sb.append("|");
            sb.append(("      " + point[i]).substring(String.valueOf(point[i]).length()));
            sb.append("|");
            sb.append("\r\n");
        }
        System.out.println(sb.toString());
    }
}
