/**
 * @author SSAFY
 *
 * 1. 배열의 크기 N, M과 연산 수 R 입력
 * 2. 배열 입력
 * 3. 연산 입력 후 computeArr @param cmd
 * 
 * <연산과정>
 * 1번 연산
 * 1. 
 */
import java.io.*;
import java.util.*;

public class Main {
	static int n, m, r;
	static int temp;
	static int[][] arr;
	static int[][] computedArr;
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		//1. 배열의 크기 N, M과 연산 수 R 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		//2. 배열 입력
		arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//3. 연산 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < r; i++) {
			computeArr(Integer.parseInt(st.nextToken()));
		}
		
		printArr(arr);
		System.out.println(sb);
	}
	
	public static void computeArr(int command) {
		switch(command) {
		//상하 반전
		case 1:
			computedArr = new int[n][m];
			for(int i = 0; i < n; i++) 
				for(int j = 0; j < m; j++) 
					computedArr[n - i - 1][j] = arr[i][j];

			arr = new int[n][m];
		    for (int i = 0; i < n; i++) 
		        for (int j = 0; j < m; j++) 
		            arr[i][j] = computedArr[i][j];

			break;
		case 2:
			computedArr = new int[n][m];
			for(int i = 0; i < n; i++)
				for(int j = 0; j < m; j++)
					computedArr[i][m - j - 1] = arr[i][j];
			
			arr = new int[n][m];
		    for (int i = 0; i < n; i++)
		        for (int j = 0; j < m; j++) 
		            arr[i][j] = computedArr[i][j];

			break;
		case 3:
			computedArr = new int[m][n];  // 크기 바뀜 (행, 열 바뀜)
		    for (int i = 0; i < n; i++)
		        for (int j = 0; j < m; j++) 
		            computedArr[j][n - i - 1] = arr[i][j];

		    temp = n;
		    n = m;
		    m = temp;

		    arr = new int[n][m];
		    for (int i = 0; i < n; i++) {
		        for (int j = 0; j < m; j++) {
		            arr[i][j] = computedArr[i][j];
		        }
		    }
			break;
		case 4:
			 computedArr = new int[m][n];
			    for (int i = 0; i < n; i++)
			        for (int j = 0; j < m; j++)
			            computedArr[m - j - 1][i] = arr[i][j];

			    temp = n;
			    n = m;
			    m = temp;

			    arr = new int[n][m];
			    for (int i = 0; i < n; i++)
			        for (int j = 0; j < m; j++) 
			            arr[i][j] = computedArr[i][j];       
			break;
		case 5:
			computedArr = new int[n][m];
		    int halfN = n / 2;
		    int halfM = m / 2;

		    // 1 -> 2
		    for (int i = 0; i < halfN; i++) 
		        for (int j = 0; j < halfM; j++) 
		            computedArr[i][j + halfM] = arr[i][j];

		    // 2 -> 3
		    for (int i = 0; i < halfN; i++) 
		        for (int j = halfM; j < m; j++) 
		            computedArr[i + halfN][j] = arr[i][j];

		    // 3 -> 4
		    for (int i = halfN; i < n; i++) 
		        for (int j = halfM; j < m; j++) 
		            computedArr[i][j - halfM] = arr[i][j];

		    // 4 -> 1
		    for (int i = halfN; i < n; i++) 
		        for (int j = 0; j < halfM; j++) 
		            computedArr[i - halfN][j] = arr[i][j];


		    // arr 갱신
		    arr = new int[n][m];
		    for (int i = 0; i < n; i++) 
		        for (int j = 0; j < m; j++) 
		            arr[i][j] = computedArr[i][j];

			break;
		case 6:
			computedArr = new int[n][m];
		    halfN = n / 2;
		    halfM = m / 2;

		    // 1 -> 4
		    for (int i = 0; i < halfN; i++) 
		        for (int j = 0; j < halfM; j++) 
		            computedArr[i + halfN][j] = arr[i][j];

		    // 4 -> 3
		    for (int i = halfN; i < n; i++)
		        for (int j = 0; j < halfM; j++) 
		            computedArr[i][j + halfM] = arr[i][j];

		    // 3 -> 2
		    for (int i = halfN; i < n; i++)
		        for (int j = halfM; j < m; j++)
		            computedArr[i - halfN][j] = arr[i][j];

		    // 2 -> 1
		    for (int i = 0; i < halfN; i++) 
		        for (int j = halfM; j < m; j++) 
		            computedArr[i][j - halfM] = arr[i][j];


		    // arr 갱신
		    arr = new int[n][m];
		    for (int i = 0; i < n; i++)
		        for (int j = 0; j < m; j++) 
		            arr[i][j] = computedArr[i][j];

			break;
		}
	}
	
	public static void printArr(int[][] arr) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++)
				sb.append(arr[i][j]).append(' ');
			sb.append('\n');
		}
	}
}
