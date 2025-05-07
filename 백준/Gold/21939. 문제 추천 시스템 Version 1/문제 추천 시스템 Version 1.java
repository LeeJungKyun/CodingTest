import java.io.*;
import java.util.*;

public class Main {
    static class Problem{
        int num;
        int level;

        public Problem(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }

    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        TreeSet<Problem> set = new TreeSet<>((p1, p2) -> {
            if(p1.level == p2.level){
                return p1.num - p2.num;
            } return p1.level - p2.level;
        });

        Map<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            set.add(new Problem(p, l));
            map.put(p, l);
        }

        m = Integer.parseInt(br.readLine());

        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            switch(cmd) {
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());
                    System.out.println(x == 1 ? set.last().num : set.first().num);
                    break;

                case "add":
                    int p = Integer.parseInt(st.nextToken());
                    int l = Integer.parseInt(st.nextToken());
                    set.add(new Problem(p, l));
                    map.put(p, l);
                    break;

                case "solved":
                    int solvedProblem = Integer.parseInt(st.nextToken());
                    int solvedLevel = map.get(solvedProblem);
                    set.remove(new Problem(solvedProblem, solvedLevel));
                    break;
            }
        }
    }
}
