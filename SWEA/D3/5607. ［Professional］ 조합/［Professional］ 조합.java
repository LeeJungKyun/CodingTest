import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 이항계수는 DP로 구할 없어서
 * 페르마의 소정리를 이용해서 팩토리얼로 계산
 * 
 */

import java.io.*;
import java.util.*;

public class Solution {
    // 상수 및 변수 선언
    static final int MOD = 1234567891;
    static long[] factorial;

    // 거듭제곱(분할 정복) 함수
    public static long power(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return result;
    }

    // 메인 함수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());

        // 팩토리얼 미리 계산
        factorial = new long[1000001];
        factorial[0] = 1;
        for (int i = 1; i <= 1000000; i++) {
            factorial[i] = (factorial[i - 1] * i) % MOD;
        }

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            
            // 페르마의 소정리 적용
            long numerator = factorial[n];
            long denominator = (factorial[r] * factorial[n - r]) % MOD;
            
            long result = (numerator * power(denominator, MOD - 2)) % MOD;
            
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb);
    }
}