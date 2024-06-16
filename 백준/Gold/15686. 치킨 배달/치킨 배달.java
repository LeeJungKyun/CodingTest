import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 15686 치킨배달
 */
public class Main {
    static class Pair{
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    //집개수 NXN
    static int n;
    //남는 치킨 집 개수 M
    static int m;
    //도시 생긴거
    static int[][] city;
    //집 좌표 저장 큐
    static ArrayList<Pair> house = new ArrayList<>();
    //치킨집 좌표 저장 큐
    static ArrayList<Pair> chicken = new ArrayList<>();
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        city = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 1)
                    house.add(new Pair(i,j));
                else if(city[i][j] == 2)
                    chicken.add(new Pair(i, j));
            }
        }
        visited = new boolean[chicken.size()];
        backtracking(0, 0);
        System.out.println(min);
    }

    public static int calculate_distance(Pair house, Pair chicken){
        return (Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y));
    }

    public static void backtracking(int count, int idx) {
        if (count == m) {
            int total = 0;
            for (int i = 0; i < house.size(); i++) {
                int sum = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if(visited[j]){
                        int dist = calculate_distance(house.get(i), chicken.get(j));
                        sum = Math.min(sum,dist);
                    }
                }
                total += sum;
            }
            min = Math.min(total, min);
            return;
        }
        for (int i = idx; i < chicken.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                backtracking(count + 1, i + 1);
                visited[i] = false;
            }
        }
    }
}
