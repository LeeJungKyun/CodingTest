import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static boolean[] visit;
    static int minValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combi(0, 0);
        System.out.println(minValue);
    }

    static void combi(int idx, int count) {
        if (count == n / 2) {
            diff();
            return;
        }

        for (int i = idx; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                combi(i + 1, count + 1);
                visit[i] = false;
            }
        }
    }

    static void diff() {
        int team_start = 0;
        int team_link = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (visit[i] && visit[j]) {
                    team_start += arr[i][j];
                    team_start += arr[j][i];
                } else if (!visit[i] && !visit[j]) {
                    team_link += arr[i][j];
                    team_link += arr[j][i];
                }
            }
        }
        int val = Math.abs(team_start - team_link);

        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }

        minValue = Math.min(val, minValue);
    }
}
