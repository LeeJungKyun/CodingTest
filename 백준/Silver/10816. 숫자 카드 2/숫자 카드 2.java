/**
 * @author 이정균
 * N개의 숫자 카드, 정수 M개, 이 수가 적혀있는 숫자 카드를 몇 개 가지고 있을까
 * 1 <= N <= 500,000
 * HashMap 사용
 * 
 * 1. N입력
 * 2. 각 숫자별로 HashMap에 key값과 value는 개수
 * 3. M입력
 * 4. M개의 수에 대해서 value 출력
 */

import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static HashMap<Integer, Integer> map;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. N입력
		n = Integer.parseInt(br.readLine());
		
		//2. 각 숫자별로 HashMap에 key값과 value는 개수
		map = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		
		//3. M입력
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(map.getOrDefault(num, 0)).append(' ');
		}
		System.out.println(sb);
	}
}
