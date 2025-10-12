import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 계란
 * 내구도, 무게
 * 계란으로 계란을 치면 상대 계란의 무게만큼 내구도가 깎이고
 * 0 이하가 되면 계란이 깨짐
 *
 * - 가장 왼쪽의 계란을 든다.
 * - 손에 들고 있는 계란으로 깨지지 않은 다른 계란 중에서 하나를 친다.
 *  - 손에 든 계란이 깨졌거나 깨지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
 * - 가장 최근에 든 계란의 한 칸 오른쪽 계란을 손에 들고 2번 과정을 다시 진행
 *  - 단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 계란을 치는 과정을 종료
 */
public class Main {
    static class Egg {
        int durability, weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }

    static int n;
    static int maxBreakCount = Integer.MIN_VALUE;
    static ArrayList<Egg> eggList;
    static BufferedReader br;
    static StringTokenizer st;
    static boolean[] isBreak;
    public static void main(String[] args) throws IOException {
        //1. 계란 개수 입력
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        //2. 계란 정보 입력
        eggList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            eggList.add(new Egg(durability, weight));
        }

        //3. 백트래킹으로 계란 깨는거 시작
        isBreak = new boolean[n];
        breakEgg(0);

        System.out.println(maxBreakCount);
    }

    public static void breakEgg(int currentEggIndex) {
        //종료 조건
        //1. 가장 오른쪽 계란
        if (currentEggIndex == n) {
            int currentBreakCount = 0;
            for (Egg egg : eggList) {
                if (egg.durability <= 0) {
                    currentBreakCount++;
                }
            }
            maxBreakCount = Math.max(currentBreakCount, maxBreakCount);
            return;
        }

        Egg cur = eggList.get(currentEggIndex);

        if (cur.durability <= 0) {
            breakEgg(currentEggIndex + 1);
            return;
        }

        int unbreakCount = 0;
        for (int i = 0; i < n; i++) {
            if (i != currentEggIndex && eggList.get(i).durability > 0) {
                unbreakCount++;
            }
        }

        if (unbreakCount == 0) {
            breakEgg(currentEggIndex + 1);
            return;
        }

        for (int next = 0; next < n; next++) {
            if (next != currentEggIndex && eggList.get(next).durability > 0) {
                Egg nextEgg = eggList.get(next);
                cur.durability -= nextEgg.weight;
                nextEgg.durability -= cur.weight;

                breakEgg(currentEggIndex + 1);

                cur.durability += nextEgg.weight;
                nextEgg.durability += cur.weight;
            }
        }
    }
}
