import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] weight;
    static int[][] dp;
    static int result;
    static final int INF = 100_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = null;

        weight = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[n][(1 << n) - 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(result = tsp(0, 1));
    }

    public static int tsp(int cur, int visit) {
        //모든 도시를 방문한 경우
        if (visit == (1 << n) - 1) {
            //현재도시 cur -> 출발도시 0 으로 가는 경로가 없다면 INF 리턴
            if(weight[cur][0] == 0)
                return INF;
            return weight[cur][0];
        }

        //이미 방문한 경로라면 해당 비용 리턴
        if(dp[cur][visit] != -1)
            return dp[cur][visit];

        dp[cur][visit] = INF;

        for(int i = 0; i < n; i++){
            //visit & (1 << i) == 0 이라면 i번째 도시를 방문하지 않았다는 뜻
            if((visit & (1 << i)) == 0 && weight[cur][i] != 0){
                //재귀함수로 i에서 시작하는 방향을 탐색, visit에 (1 << i)를 OR 연산을 해주면서 해당 위치를 방문했다는 표시
                dp[cur][visit] = Math.min(tsp(i, visit | (1 << i)) + weight[cur][i], dp[cur][visit]);
            }
        }
        return dp[cur][visit];
    }
}
