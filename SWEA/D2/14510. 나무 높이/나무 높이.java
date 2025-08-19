import java.io.*;
import java.util.*;

public class Solution {
    static int T, N;
    static int[] trees;
    static int maxHeight;
    static Queue<Integer> doubleQueue, singleQueue; // 2씩 자라는 작업(doubleQueue), 1씩 자라는 작업(singleQueue)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            trees = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            maxHeight = 0;
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
                if (trees[i] > maxHeight)
                	maxHeight = trees[i];
            }

            doubleQueue = new ArrayDeque<>();
            singleQueue = new ArrayDeque<>();

            // 작업 큐에 넣기
            for (int i = 0; i < N; i++) {
                int diff = maxHeight - trees[i];
                // 2씩 자라야 하는 작업 수
                for (int j = 0; j < diff / 2; j++) {
                    doubleQueue.offer(2);
                }
                // 1씩 자라야 하는 작업 수
                if (diff % 2 == 1) {
                    singleQueue.offer(1);
                }
            }

            int ans = 0;

            // 2씩 자라야하는 작업이 많을 때
            if (doubleQueue.size() >= singleQueue.size()) {
                while (true) {
                    // 2씩 작업 개수가 1씩 작업 개수보다 작거나 같아지면 종료
                    if (doubleQueue.size() <= singleQueue.size()) {
                        ans = doubleQueue.size() + singleQueue.size();
                        break;
                    }
                    // 2씩 작업 하나를 빼고, 1씩 작업 2개를 넣는다
                    doubleQueue.poll();
                    singleQueue.offer(1);
                    singleQueue.offer(1);
                }
            } else {
                // 1씩 작업이 더 많을 때
                ans += doubleQueue.size() * 2;
                int gap = singleQueue.size() - doubleQueue.size();
                // 남은 1씩 작업 개수에 대해 계산
                ans += gap + (gap - 1);
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.print(sb);
    }
}
