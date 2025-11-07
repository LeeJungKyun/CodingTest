import java.io.*;
import java.util.*;

public class Main {
	static int n, t;
	static int[] arr;
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int result = 0;
		for(int i = 0; i < n; i++) {
			result += findNear(arr[i], t);
		}
		
		System.out.println(result);
	}
	
	public static int findNear(int num, int t) {
		int left = num;
		int right = num;
		int cnt = 0;
		
		if(t % num == 0)
			return cnt;
		
		for(int i = 0;; i++) {
			left += 1;
			right -= 1;
			cnt++;
			
			if(t % left == 0) return cnt;
			if(t % right == 0) return cnt;
		}
	}
}