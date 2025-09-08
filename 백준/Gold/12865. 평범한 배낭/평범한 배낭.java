import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N개의 물건은 무게 W와 가치 V를 가짐
 * 최대 수용 무게 K
 * 물건 쪼개기 X
 * 
 * 1. 물건개수, 버틸 수 있는 무게 입력
 * 2. N개의 줄에 물건 정보 입력
 * 3. DP배열 선언 ( k + 1 )
 * 4. DP테이블 채우기 DP[i] 는 총 무게가 i 일때 얻을 수 있는 최대 가치를 뜻함
 * 	4-1. DP의 0 값은 0
 * 	4-2. 2중 for문을 사용하는데 i는 이번에 뽑을 물건, j는 목표 무게로 설정
 * -> i 번째 물건을 배낭에 넣는 경우의 가치를 구하는데 i 번째 물건의 무게를 제외한 나머지 무게에서 얻을 수 있는 값에 i 번째 물건의 가치를 더함
 */
public class Main {
	static class Item {
		int weight, value;

		public Item(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
	
	static int n, k;
	static ArrayList<Item> itemList;
	static int[] dp;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 물건 개수, 버틸 수 있는 무게 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//2. N개의 줄에 물건 정보 입력
		itemList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			itemList.add(new Item(weight, value));
		}
		
		//3. DP배열 선언 ( k + 1 )
		dp = new int[k + 1];
		
		//4. DP테이블 채우기
		
		//4-1. DP[0] = 0;
		dp[0] = 0;
		
		//4-2. 2중 for문을 사용하는데 i는 이번에 뽑을 물건, j는 목표 무게로 설정
		for(int i = 0; i < n; i++) {
			for(int j = k; j >= itemList.get(i).weight; j--) {
				//-> i 번째 물건을 배낭에 넣는 경우의 가치를 구하는데 i 번째 물건의 무게를 제외한 나머지 무게에서 얻을 수 있는 값에 i 번째 물건의 가치를 더함
				dp[j] = Math.max(dp[j], dp[j - itemList.get(i).weight] + itemList.get(i).value);
			}
		}
		
		System.out.println(dp[k]);
	}
}