
/**
 * @author 이정균
 * 1~N번 책
 * M번에 걸쳐 구간을 선택해서 M번 반복한 이후 책장의 상태
 */

import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] arr;
	
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		for(int i = 0; i < n; i++)
			arr[i] = i;
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			//0번인덱스부터 하고 나중에 + 1
			swap(start - 1, end - 1);
		}
		
		for(int i = 0; i < n; i++)
			System.out.print((arr[i] + 1) + " ");
	}
	
	public static void swap(int startIdx, int endIdx) {
		int mid = (endIdx - startIdx) / 2;
		
		for(int i = 0; i <= mid; i++) {
			int temp = arr[startIdx + i];
			arr[startIdx + i] = arr[endIdx - i];
			arr[endIdx - i] = temp;
		}
	}

}
