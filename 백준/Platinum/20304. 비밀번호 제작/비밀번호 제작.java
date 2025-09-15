import java.io.*;
import java.util.*;

public class Main {
    static int n, tryCount;
    static int[] triedPasswordArray;
    static int[] safety;
    static Queue<Integer> queue = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        tryCount = Integer.parseInt(br.readLine());

        safety = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            safety[i] = -1; // 안전도를 -1로 초기화 (방문하지 않았음을 의미)
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < tryCount; i++) {
            int password = Integer.parseInt(st.nextToken());
            queue.add(password);
            safety[password] = 0; // 시도된 비밀번호의 안전도는 0
        }

        int maxSafety = 0;
        int maxBitCount = 0;

        // N의 최대 비트 수를 계산하여 탐색 범위를 한정시키기
        if (n > 0) {
            maxBitCount = (int) (Math.log(n) / Math.log(2)) + 1;
        }

        // BFS 탐색 시작
        while (!queue.isEmpty()) {
            int currentPassword = queue.poll();
            maxSafety = Math.max(maxSafety, safety[currentPassword]);
            
            // 현재 비밀번호와 XOR연산의 결과가 1인 모든 숫자를 탐색
            for (int i = 0; i < maxBitCount; i++) {
                int nextPassword = currentPassword ^ (1 << i);
                
                // 다음 비밀번호가 N 이하이고 아직 방문하지 않았다면
                if (nextPassword >= 0 && nextPassword <= n && safety[nextPassword] == -1) {
                    safety[nextPassword] = safety[currentPassword] + 1;
                    queue.add(nextPassword);
                }
            }
        }
        
        System.out.println(maxSafety);
    }
}