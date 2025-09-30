import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] arr;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. 한 말의 개수 입력
		n = Integer.parseInt(br.readLine());
		
		//2. 입력
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = -1;
		
		for(int i = 0; i <= n; i++) {
			int trueCount = 0;
			
			for(int j = 0; j < n; j++) {
				if(arr[j] == i)
					trueCount++;
			}
			if(trueCount == i)
				max = Math.max(max, i);
		}
		System.out.println(max);
	}
}