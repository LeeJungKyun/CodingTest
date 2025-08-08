/**
 * @author 이정균
 * N개의 재료들의 신맛 S와 쓴맛 B
 * N자리의 비트를 선언해서 어떤 재료를 선택했는지 확인후에 조합을 계산후 갱신하면 된다.
 * 
 * 1. 재료의 개수 N 입력 받기
 * 2. 재료 별 맛을 입력받기 (Taste 클래스 리스트 또는 배열)
 * 3. mask 변수를 1로 초기화하고 2^N - 1 가지의 경우수가 있으니 (1 << N) - 1까지 반복
 * 4. 각 경우의 수 별로 신맛은 1, 쓴맛은 0으로 초기화
 * 5. 조합은 다 골랐으니깐 1을 N까지 한 칸씩 left shift를 하면서 & 연산을 해서 0이 아니라면 해당 재료를 골랐다는 뜻이니깐 계산에 추가
 * 6. 둘의 차이에 절댓값을 씌워서 최소차이 갱신
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
	static class Taste {
		int sour, bitter;

		public Taste(int sour, int bitter) {
			this.sour = sour;
			this.bitter = bitter;
		}
		
	}
	static BufferedReader br;
	static int n;
	static int minDiff = Integer.MAX_VALUE;
	static ArrayList<Taste> tasteList;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 재료의 개수 N 입력받기
		n = Integer.parseInt(br.readLine());
		
		//2. 재료 별 맛을 입력받기
		tasteList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sour = Integer.parseInt(st.nextToken());
			int bitter = Integer.parseInt(st.nextToken());
			tasteList.add(new Taste(sour, bitter));
		}
		
		//3. mask 변수를 1로 초기화하고 2^N - 1 가지의 경우수가 있으니 (1 << N) - 1까지 반복
		for(int mask = 1; mask <= (1 << n) - 1; mask++) {
			//4. 각 경우의 수 별로 신맛은 1, 쓴맛은 0으로 초기화
			int sourTaste = 1;
			int bitterTaste = 0;
			//5. 조합은 다 골랐으니깐 1을 N까지 한 칸씩 left shift를 하면서 & 연산을 해서 0이 아니라면 해당 재료를 골랐다는 뜻이니깐 계산에 추가
			for(int i = 0; i < n; i++) {
				//i번째 재료 고름
				if((mask & (1 << i)) != 0) {
					sourTaste *= tasteList.get(i).sour;
					bitterTaste += tasteList.get(i).bitter;
				}
			}
			//6. 둘의 차이에 절댓값을 씌워서 최소차이 갱신
			int diff = Math.abs(sourTaste - bitterTaste);
			minDiff = Math.min(minDiff, diff);
		}
		
		System.out.println(minDiff);
	}
}
