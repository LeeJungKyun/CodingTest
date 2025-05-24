import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static Set<Integer> truth;
    static ArrayList<Integer>[] party;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        party = new ArrayList[m];
        truth = new HashSet<>();

        for (int i = 0; i < m; i++) {
            party[i] = new ArrayList<>();
        }

        //진실을 아는 사람 저장
        st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        for (int i = 0; i < num; i++) {
            truth.add(Integer.parseInt(st.nextToken()));
        }

        //각 파티마다 오는 사람의 수와 번호
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            for(int j = 0; j < num; j++)
                party[i].add(Integer.parseInt(st.nextToken()));
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < m; i++) {
                boolean flag = false;
                for (int person : party[i]) {
                    if (truth.contains(person)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    for (int person : party[i]) {
                        if (!truth.contains(person)) {
                            truth.add(person);
                            changed = true;
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < m; i++) {
            boolean safe = true;
            for (int person : party[i]) {
                if (truth.contains(person)) {
                    safe = false;
                    break;
                }
            }
            if (safe) answer++;
        }

        System.out.println(answer);
    }
}
