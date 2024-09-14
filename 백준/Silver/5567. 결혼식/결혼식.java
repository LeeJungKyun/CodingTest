import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] board;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());  // 동기 수
        m = Integer.parseInt(br.readLine());  // 친구 관계 수

        board = new int[n + 1][n + 1];  // 인접 행렬
        visited = new boolean[n + 1];   // 방문 여부 배열

        // 친구 관계 입력
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = board[b][a] = 1;
        }

        result = bfs(1);  // BFS로 1번 사람부터 탐색하여 친구 수 계산

        // 결과 출력
        System.out.println(result);
    }

    // BFS 탐색
    public static int bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        int depth = 0;  // 탐색 깊이를 추적
        int count = 0;  // 초대할 친구 수

        while (!queue.isEmpty()) {
            int size = queue.size();  // 현재 레벨에 있는 노드 개수
            depth++;

            // 현재 레벨에 있는 모든 노드를 처리
            for (int i = 0; i < size; i++) {
                int v = queue.poll();

                // 친구 탐색
                for (int j = 1; j <= n; j++) {
                    if (!visited[j] && board[v][j] == 1) {
                        visited[j] = true;
                        queue.add(j);
                        count++;  // 새로운 친구 발견 시 초대 수 증가
                    }
                }
            }

            // 친구의 친구까지만 탐색 (깊이 2까지만)
            if (depth == 2) {
                break;
            }
        }

        return count;  // 초대할 친구 수 반환
    }
}