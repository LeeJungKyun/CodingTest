import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 밑면이 정사각형인 직육면체 벽돌들
 * 벽돌은 회전 X, 밑면의 넓이는 서로 다 다르며, 무게가 같은 벽돌은 없음
 * 벽돌들의 높이는 같을 수 있음
 * 위로 갈수록 밑면은 좁아져야하고, 무게는 가벼워져야함
 * 
 * 가장 높이 쌓았을 때 가장 위부터 아래까지 출력
 * 
 * 벽돌 클래스
 * 밑면의 넓이, 높이, 무게
 * 
 * dp[i] = Math.max(dp[i], dp[j] + 현재 벽돌의 높이) 
 * 여기서 j는 i번째 벽돌 위에 쌓인 벽돌들
 * dp[j]는 j번째 벽돌이 아래로 깔렸을 때의 최대 높이
 * 
 * 1. 벽돌의 수 blockCount 입력
 * 2. blockCount만큼 벽돌의 정보 입력(최대 100개)
 * 3. 벽돌 배열을 넓이 순으로 정렬
 * 4. DP테이블 채우기
 * 	4-1. 일단 자신의 높이로 초기화
 * 	4-2. 정렬은 보장되어있으니 2중 for로 i 번째 아래에 깔릴 수 있는 배열을 초기화
 * 5. 출력은 큐를 사용해서 최대 DP인 값에서부터 높이를 빼면서 해당 블록의 번호를 큐에 넣고 출력
 * 
 */
public class Main {
	static class Block implements Comparable<Block> {
		int num, area, height, weight;

		public Block(int num, int area, int height, int weight) {
			this.num = num;
			this.area = area;
			this.height = height;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Block o) {
			return Integer.compare(o.area, this.area);
		}
		
		@Override
		public String toString() {
			return "Block [area=" + area + ", height=" + height + ", weight=" + weight + "]";
		}
	}
	
	static int blockCount, heightSum;
	static Block[] blockArr;
	static int[] dp;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 벽돌의 수 입력
		blockCount = Integer.parseInt(br.readLine());
		
		//2. 벽돌의 정보 입력
		blockArr = new Block[blockCount];
		for(int i = 0; i < blockCount; i++) {
			st = new StringTokenizer(br.readLine());
			
			int area = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
				
			blockArr[i] = new Block(i + 1, area, height, weight);
		}
		
		//3. 벽돌 배열을 넓이 순으로 정렬
		Arrays.sort(blockArr);
		
		//4. DP테이블 채우기
		dp = new int[blockCount];
		//4-1. 일단 자신의 높이로 초기화
		for(int i = 0; i < blockCount; i++) {
			dp[i] = blockArr[i].height;
		}
		
		//4-2. 정렬은 보장되어있으니 2중 for로 i 번째 아래에 깔릴 수 있는 배열을 초기화
		int max = 0;
		for(int i = 0; i < blockCount; i++) {
			for(int j = 0; j < i; j++) {
				//i 가 더 가벼워야함
				if(blockArr[i].weight < blockArr[j].weight) {
					dp[i] = Math.max(dp[i], dp[j] + blockArr[i].height);
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		//5. 출력은 큐를 사용해서 최대 DP인 값에서부터 높이를 빼면서 해당 블록의 번호를 큐에 넣고 출력
		Queue<Integer> result = new LinkedList<>();
		while(blockCount > 0) {
			if(max == dp[blockCount - 1]) {
				result.add(blockArr[blockCount - 1].num);
				max -= blockArr[blockCount - 1].height;
			}
			blockCount--;
		}
		
		System.out.println(result.size());
		while(!result.isEmpty())
			System.out.println(result.poll());
	}
}