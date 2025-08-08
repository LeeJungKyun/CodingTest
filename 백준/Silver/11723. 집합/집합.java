/**
 * @author 이정균
 * 공집합 S
 * 연산 add, remove, check, toggle, all(1~20으로 변경), empty(공집합)
 * 연산의 수 M이 주어졌을 때 check연산이 주어질 때마다 결과 출력
 * 비트 연산으로 풀기
 * 
 * 구현 방향
 * 1. M입력
 * 2. bit = 0 선언후 M반복문 내에서 switch 문 실행 후 command와 num 입력
 * 	2-1. add: 1 << (num - 1) 비트 켜주기
 * 	2-2. remove: 해당 비트만 0으로 바꿔야 함 -> 기존비트와 제거할 비트만 꺼진 비트 AND 연산
 *  2-3. check: 해당 비트가 켜져있는지 여부(있으면 1, 없으면 0) -> 기존 비트 >> (num - 1) 과 1을 AND 연산 
 *  2-4. toggle: 해당 비트 상태를 변경 (1 << (num - 1))과 XOR
 *  2-5. all: 전부다 1로 초기화
 *  2-6. empty: 전부다 0으로 초기화
 */

import java.io.*;
import java.util.*;

public class Main {
	static int m;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//1. M입력
		m = Integer.parseInt(br.readLine());
		//2. bit = 0 선언후 M반복문 내에서 switch 문 실행 후 command와 num 입력
		int bit = 0;
		while(m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int num;
			switch(cmd){
				//2-1. add: 1 << (num - 1) 비트 켜주기
				case "add":
					num = Integer.parseInt(st.nextToken());
					bit |= (1 << (num - 1));
					break;
				//2-2. remove: 해당 비트만 0으로 바꿔야 함 -> 기존비트와 제거할 비트만 꺼진 비트 AND 연산
				case "remove":
					num = Integer.parseInt(st.nextToken());
					bit &= (~(1 << (num - 1)));
					break;
				//2-3. check: 해당 비트가 켜져있는지 여부(있으면 1, 없으면 0) -> 기존 비트 >> (num - 1) 과 1을 AND 연산 
				case "check":
					num = Integer.parseInt(st.nextToken());
					sb.append((bit & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
					break;
				//2-4. toggle: 해당 비트 상태를 변경 (1 << (num - 1))과 XOR
				case "toggle":
					num = Integer.parseInt(st.nextToken());
					bit ^= (1 << (num - 1));
					break;
				//2-5. all: 전부다 1로 초기화
				case "all":
					bit |= (~0);
					break;
				//2-6. empty: 전부다 0으로 초기화
				case "empty":
					bit &= 0;
					break;
			}
		}
		System.out.println(sb);
	}
}
