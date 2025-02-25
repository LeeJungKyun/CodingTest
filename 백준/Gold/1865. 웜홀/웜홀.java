import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Road {
    int end;
    int weight;

    public Road(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
}

public class Main {
    static int TC;
    static int n, m, w;
    static int s, e, t;
    static int[] dist;
    static final int INF = 12500000;
    static ArrayList<ArrayList<Road>> arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            dist = new int[n + 1];
            arr = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                arr.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());

                arr.get(s).add(new Road(e, t));
                arr.get(e).add(new Road(s, t));
            }

            for (int i = 0; i < w; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                t = Integer.parseInt(st.nextToken());

                arr.get(s).add(new Road(e, -t));
            }

            boolean isMinus = false;
            for (int i = 1; i <= n; i++) {
                if (bellmanFord(i)) {
                    isMinus = true;
                    sb.append("YES\n");
                    break;
                }
            }

            if (!isMinus) {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);

    }

    private static boolean bellmanFord(int start) {
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean update = false;

        for (int i = 1; i < n; i++) {
            update = false;

            for (int j = 1; j <= n; j++) {
                for (Road road : arr.get(j)) {
                    if (dist[j] != INF && dist[road.end] > dist[j] + road.weight) {
                        dist[road.end] = dist[j] + road.weight;
                        update = true;
                    }
                }
            }

            if (!update) {
                break;
            }
        }

        if (update) {
            for (int i = 1; i <= n; i++) {
                for (Road road : arr.get(i)) {
                    if (dist[i] != INF && dist[road.end] > dist[i] + road.weight) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
