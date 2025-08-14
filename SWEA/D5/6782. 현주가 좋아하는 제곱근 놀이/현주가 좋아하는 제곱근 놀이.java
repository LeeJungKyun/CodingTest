/**
 * @author 이정균
 * 2이상의 정수 N
 * N -> N + 1
 * 루트 N이 정수라면 N을 루트 N으로 바꿀 수 있음
 * N을 2로 만들기 위해 조작해야 하는 횟수의 최솟값
 * 
 * 그리디
 */

import java.io.*;
import java.util.*;

public class Solution {
	static int testCase;
	static long N;
	static int minCount;
	static long limit;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			//2. 테케별 필요 변수 초기화
			//3. N입력
			N = Long.parseLong(br.readLine());
			
			long result = makeTwo(N);
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
	
	public static long makeTwo(long startNum) {
		long count = 0;
		 
        while (startNum != 2) {
            long sqrt = (long) Math.sqrt(startNum);
 
            if (sqrt * sqrt == startNum) {
                count++;
                startNum = sqrt;
            } else {
                long nextSquare = (sqrt + 1) * (sqrt + 1);
                count += nextSquare - startNum;
                startNum = nextSquare;
            }
        }
 
        return count;
	}
}
