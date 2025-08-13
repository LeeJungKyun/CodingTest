/**
 * @author 이정균
 * 과일들의 높이 h배열 정렬해서 내 키부터 한개씩 늘리고 인덱스 늘리다가 안되면 출력
 * 
 * 1. n, l 입력
 * 2. 과일 높이 height 배열 입력
 * 3. 정렬 후 계산
 */

import java.io.*;
import java.util.*;
public class Main {
	static int n, l;
	static int[] height;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. n, l 입력
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		//2. 과일 높이 height 배열 입력
		height = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++)
			height[i] = Integer.parseInt(st.nextToken());
		
		//3. 정렬 후 계산
		Arrays.sort(height);
		
		for(int curHeight : height) {
			if(curHeight <= l)
				l++;
			else break;
		}
		System.out.println(l);
	}
}
