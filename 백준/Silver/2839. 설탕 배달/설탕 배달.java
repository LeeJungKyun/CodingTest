/**
 * @author 이정균
 * 정확히 N kg 배달
 * 3키로 봉지, 5키로 봉지
 * 최대 적은 개수 봉지 배달
 * 
 * 백트래킹
 * 
 * 1. n입력
 * 2. 100_000 크기의 checked 배열을 최대로 채우기
 * 3. 0, 0부터 백트래킹 시작
 *  3-1. 현재까지의 합이 n 보다 크면 종료
 *  3-2. 방문한 곳의 checked 값이 현재 내 count보다 작으면 종료
 *  3-3. 현재 내 무게의 합에 카운트 입력
 *  3-4. 3짜리, 5짜리 각각 재귀
 * 4. 출력(만약에 checked[n]이 최대면 방문 못한거니깐 -1 출력)
 */

import java.io.*;
import java.util.*;
public class Main {
	static int n, result = Integer.MAX_VALUE;
	static int[] checked = new int[100_000];
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		//1. n입력
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		//2. 100_000 크기의 checked 배열을 최대로 채우기
		Arrays.fill(checked, Integer.MAX_VALUE);
		//3. 0, 0부터 백트래킹 시작
		makeN(0, 0);
		//4. 출력(만약에 checked[n]이 최대면 방문 못한거니깐 -1 출력)
		System.out.println((checked[n] == Integer.MAX_VALUE) ? -1 : checked[n]);
	}
	
	public static void makeN(int currentSum, int count) {
		//3-1. 현재까지의 합이 n 보다 크면 종료
		if (currentSum > n) return;
		//3-2. 방문한 곳의 checked 값이 현재 내 count보다 작으면 종료
        if (count >= checked[currentSum]) return;

        //3-3. 현재 내 무게의 합에 카운트 입력
        checked[currentSum] = count;

        //3-4. 3짜리, 5짜리 각각 재귀
        makeN(currentSum + 3, count + 1);
        makeN(currentSum + 5, count + 1);
	}
}
