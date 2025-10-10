import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * 수열의 부분수열 -> 수열의 연속한 일부 항을 원래 순서대로 나열해 얻을 수 있는 수열
 * 
 * 부분 수열 중 (부분수열 내 최대) - (부분 수열 내 최소) 가 최대가 되게 하는 부분수열 중 가장짧은 수열의 길이
 */

public class Main {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int n;
	static int[] arr;
	static int min, max;
	static ArrayList<Integer> minIndexList, maxIndexList;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n입력
		n = Integer.parseInt(br.readLine());
		
		//2. N개의 수 배열 입력하면서 배열의 최대, 최소 입력
		min = MAX;
		max = MIN;
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		
		minIndexList = new ArrayList<>();
		maxIndexList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			if(arr[i] == min)
				minIndexList.add(i);
			if(arr[i] == max) {
				maxIndexList.add(i);
			}
		}
		
		int result = MAX;
		
		int i = 0, j = 0;

		while (i < minIndexList.size() && j < maxIndexList.size()) {
			int minIdx = minIndexList.get(i);
			int maxIdx = maxIndexList.get(j);

			result = Math.min(result, Math.abs(minIdx - maxIdx));

			if (minIdx < maxIdx) {
				i++;
			} else {
				j++;
			}
		}
		
		System.out.println(result + 1);
		
	}
}