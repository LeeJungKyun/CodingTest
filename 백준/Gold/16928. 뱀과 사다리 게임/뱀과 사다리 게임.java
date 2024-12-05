import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[101];

        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }

        //사다리
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x] = y;
        }

        //뱀
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x] = y;
        }

        int result = bfs(1);
        System.out.println(result);
    }

    private static int bfs(int startNode) {
        int[] check = new int[101];
        Queue<Integer> q = new LinkedList<>();
        q.offer(startNode);
        check[startNode] = 0;

        while (true) {
            int visitedNum = q.poll();
            for (int i = 1; i < 7; i++) {
                int newNode = visitedNum + i;

                if (newNode > 100) {
                    continue;
                }

                if (check[arr[newNode]] == 0) {
                    q.offer(arr[newNode]);
                    check[arr[newNode]] = check[visitedNum] + 1;
                }
                if (arr[newNode] == 100) {
                    return check[100];
                }
            }
        }
    }
}