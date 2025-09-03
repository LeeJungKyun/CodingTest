import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * DFS로 경우 탐색하면서 1나오면 끝
 * 
 */
public class Main {
	static int n;
	static int result = Integer.MAX_VALUE;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		makeOne(n, 0);
		
		System.out.println(result);
	}
	
	public static void makeOne(int num, int count) {
		if(num == 1) {
			result = Math.min(result, count);
			return;
		}
        
        //가지치기
		if(count >= result)
			return;
		
		if(num % 3 == 0)
			makeOne(num / 3, count + 1);
		
		if(num % 2 == 0)
			makeOne(num / 2, count + 1);
		
		makeOne(num - 1, count + 1);
	}
}