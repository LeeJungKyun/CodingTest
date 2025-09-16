import java.io.*;
import java.util.*;

/**
 * 주어진 방정식 `(f(n, m) - n) XOR n = k`를 만족하는 최소의 양의 정수 n을 찾는 문제
 * f(n, m) 은 n과 서로소인 m번째 양의 정수를 의미

 * 1. 주어진 방정식 `(f(n, m) - n) XOR n = k`을 n에 대해 정리
 * 양변에 XOR n 을 취하면 (f(n, m) - n) = k XOR n
 * f(n, m) = (k XOR n) + n
 * 
 * 2. 이 식을 만족하는 최소의 양의 정수 n
 * f(n, m) 은 n에 따라 값이 변하는 함수이므로, n에 대해 직접 탐색
 * 
 * 3. n의 탐색 범위를 어떻게 설정?
 * f(n, m) 은 n과 서로소인 m번째 수를 찾는 함수
 * 직관적으로 생각했을 때, f(n, m) 의 값은 n에 비례하여 증가
 * 
 * 대략적으로 f(n, m)은 m 보다 약간 큰 값에서 시작하여 m * n 정도의 범위
 * f(n, m)과 n의 관계를 통해 (k XOR n) + n의 값을 추정해 n의 범위를 좁힘
 * 
 * k 를 기준으로 k - 6m 부터 k + 6m 까지의 범위를 설정
 * 
 * 4. 설정된 범위 내에서 n을 1부터 순회하며 조건을 만족하는지 확인
 * 
 * 5. 만약 루프가 끝날 때까지 조건을 만족하는 n을 찾지 못하면 -1을 반환
 */

public class Solution {
	static int testCase;
	static long k;
	static int m;
    static BufferedReader br;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine());

        // 각 테스트 케이스를 순회
        for (int tc = 1; tc <= testCase; tc++) {

            st = new StringTokenizer(br.readLine());
            long k = Long.parseLong(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // solve 함수를 호출하여 n을 찾음
            long ans = solve(k, m);

            sb.append('#').append(tc).append(' ').append(ans).append('\n');
        }

        System.out.println(sb);
    }

    /**
     * (f(n, m) - n) XOR n = k 를 만족하는 최소의 양의 정수 n 찾기
     * n의 범위를 설정하고, 해당 범위 내에서 순차적으로 n을 대입해 조건을 확인
     */
    static long solve(long k, int m) {
        // n의 탐색 시작 및 끝 범위 설정
        long start = Math.max(1, k - 6L * m);
        long end = k + 6L * m;

        // start부터 end까지 n을 순회하며 검증
        for (long n = start; n <= end; n++) {
            // f(n, m) 값을 계산
            long f = fx(n, m);
            // (f - n) XOR n == k 조건을 확인
            // 이 코드에서는 f(n, m) - n이 아니라 (f ^ n) == k를 확인하므로,
            // 주석의 방정식은 (f(n, m) - n) XOR n = k 이고 코드의 검증은 (f ^ n) == k이므로
            // 이 부분을 명확히 구분하여 설명
            if ((f ^ n) == k) {
                // 조건을 만족하는 최소 n을 찾았으므로 반환
                return n;
            }
        }
        // 루프가 끝날 때까지 n을 찾지 못했으므로 -1 반환
        return -1;
    }

    /**
     * f(n, m) 함수: n과 서로소인 m번째 양의 정수 구하기
     * 1부터 시작하여 n과 서로소인 수를 찾고, m번째로 발견된 수를 반환
     */
    static long fx(long n, int m) {
        int count = 0; // 서로소인 수의 개수를 세는 카운터
        long t = 0;   // 1부터 시작하여 증가시킬 수

        // m번째 서로소인 수를 찾을 때까지 반복
        while (count < m) {
            t++; // 다음 수로 증가
            if (gcd(t, n) == 1) {
                count++; // 서로소인 경우 카운트 증가
            }
        }

        // m번째 서로소인 수 t를 반환
        return t;
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
}