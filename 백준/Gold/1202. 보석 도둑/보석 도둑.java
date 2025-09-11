import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 보석 N개
 * 각 보석은 무게 M, 가격 V
 * 가방은 K개, 각 가방의 수용 무개 C
 * 가방에는 최대 한 개의 보석
 * 
 * 훔칠 수 있는 보석의 최대 가격 (long)
 * 1 <= N, K <= 300,000
 * 0 <= m, v <= 1,000,000
 * 1 <= C <= 100,000,000
 * 
 * 1. n, bagCount 입력
 * 2. N개의 줄에 무게 m, v 입력
 * 3. 가방의 무게 C가 한줄에 하나씩 입력
 *
 */
public class Main {
	static class Jewel implements Comparable<Jewel> {
		int weight;
		int price;
		
		public Jewel(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}
		
		@Override
		public int compareTo(Jewel o) {
			if(this.weight == o.weight) {
				return Integer.compare(o.price, this.price);
			}
			return Integer.compare(this.weight, o.weight);
		}
	}
	static long maxStealingValue;
	static int n, bagCount;
	static Jewel[] jewels;
	static int[] bagArr;
	static boolean[] stolen;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n, bagCount 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		bagCount = Integer.parseInt(st.nextToken());
		
		//2. 보석의 정보 입력
		jewels = new Jewel[n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			jewels[i] = new Jewel(weight, price);
		}
        
        Arrays.sort(jewels);
		
		//3. 가방의 무게 C가 한줄에 하나씩 입력
		bagArr = new int[bagCount];
		for(int i = 0; i < bagCount; i++) {
			bagArr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(bagArr);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		
		maxStealingValue = 0L;
		for (int i = 0, j = 0; i < bagCount; i++) {
            // 현재 가방의 무게 이하인 보석을 모두 우선순위 큐에 넣음
            while (j < n && jewels[j].weight <= bagArr[i]) {
                pq.offer(jewels[j++].price);
            }
 
            // 우선순위 큐에 있는 요소를 하나 빼서 가방에 넣음.
            if (!pq.isEmpty()) {
            	maxStealingValue += pq.poll();
            }
        }
		
		System.out.println(maxStealingValue);
	}
}