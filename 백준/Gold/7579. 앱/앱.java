import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * N개의 앱이 활성화 되어있음
 * 앱은 각각 m바이트의 메모리를 사용, 비활성화 했다가 다시 실행하면 c만큼 비용
 * 
 * 내가 새로 M바이트의 메모리가 필요함
 * 현재 활성화되어있는 앱 중 몇개를 비활성화해서 비용은 최소로 M 메모리를 확보하고싶음
 *
 *
 * 1. 앱의 개수 N과 필요한 메모리 M 입력
 * 2. 메모리 입력
 * 3. 비용 입력
 * 4. DP의 크기는 총 비용만큼 선언하기
 */
public class Main {
	static int appCount, requiredMemory;
	static int totalCost;
	static int[] appMemory, appCost;
	static int[] costDP;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 앱의 개수 N과 필요한 메모리 M 입력
		st = new StringTokenizer(br.readLine());
		
		appCount = Integer.parseInt(st.nextToken());
		requiredMemory = Integer.parseInt(st.nextToken());
		
		//2. 메모리 입력
		appMemory = new int[appCount];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < appCount; i++) {
			appMemory[i] = Integer.parseInt(st.nextToken());
		}
		
		//3. 비용 입력
		appCost = new int[appCount];
		totalCost = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < appCount; i++) {
			appCost[i] = Integer.parseInt(st.nextToken());
			totalCost += appCost[i];
		}
		
		//4. DP의 크기는 총 비용만큼 선언하기
		costDP = new int[totalCost + 1];
		costDP[0] = 0;
		
		for(int i = 0; i < appCount; i++) {
			for(int j = totalCost; j >= appCost[i]; j--) {
				costDP[j] = Math.max(costDP[j], costDP[j - appCost[i]] + appMemory[i]);
			}
		}
		
		int minCost = Integer.MAX_VALUE;
		for(int i = 0; i <= totalCost; i++) {
			if(costDP[i] >= requiredMemory) {
				minCost = i;
				break; 
			}
		}
		
		System.out.println(minCost);

	}
}