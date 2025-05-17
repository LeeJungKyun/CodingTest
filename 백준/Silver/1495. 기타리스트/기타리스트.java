import java.io.*;
import java.util.*;

public class Main {
    static class Song{
        int num, volume;

        public Song(int num, int volume) {
            this.num = num;
            this.volume = volume;
        }
    }
    static int N, S, M;
    static int[] V;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리
        N = Integer.parseInt(st.nextToken()); // 곡 수
        S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        M = Integer.parseInt(st.nextToken()); // 최대 볼륨

        V = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            V[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N + 1][M + 1];
        Queue<Song> queue = new LinkedList<>();
        queue.add(new Song(0, S));
        visited[0][S] = true;

        while (!queue.isEmpty()) {
            Song current = queue.poll();
            int idx = current.num;
            int volume = current.volume;

            if (idx == N) continue;

            int up = volume + V[idx];
            int down = volume - V[idx];

            if (up <= M && !visited[idx + 1][up]) {
                visited[idx + 1][up] = true;
                queue.add(new Song(idx + 1, up));
            }

            if (down >= 0 && !visited[idx + 1][down]) {
                visited[idx + 1][down] = true;
                queue.add(new Song(idx + 1, down));
            }
        }

        int result = -1;
        for (int vol = 0; vol <= M; vol++) {
            if (visited[N][vol]) {
                result = Math.max(result, vol);
            }
        }

        System.out.println(result);
    }
}