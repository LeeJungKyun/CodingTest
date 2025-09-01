import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 분할정복
 *
 */
public class Main {
	static int[][] arr;
	static int n;
	static int normalCount = 0, weirdCount = 0;
	static BufferedReader br;
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		//--------------솔루션 코드를 작성하세요.---------------------------
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//N입력
		n = Integer.parseInt(br.readLine());
		
		//배열 입력
		arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	 
			}
		}
		
		//정원 나누기 
		divideGarden(0, 0, n);
		
		System.out.println(normalCount);
		System.out.println(weirdCount);
	}
	
	//분할정복으로 정원 나누기 모두 같은 색이 아니면 4등분해서 재귀 ㄱㄱ
	
	public static void divideGarden(int x, int y, int size) {
		//모두 다 같은 색이면
		if(isValid(x, y, size)) {
			//정상 타일
			if(arr[x][y] == 0)
				normalCount++;
			else weirdCount++;
			return;
		}
		
		
		int newSize = size / 2;
		divideGarden(x, y, newSize);
		divideGarden(x, y + newSize, newSize);
		divideGarden(x + newSize, y, newSize);
		divideGarden(x + newSize, y + newSize, newSize);
	}
	
	public static boolean isValid(int x, int y, int size) {
		int std = arr[x][y];
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				//종류가 다르면 false
				if(arr[i][j] != std)
					return false;
			}
		}
		//나머지는 true
		return true;
	}
}
