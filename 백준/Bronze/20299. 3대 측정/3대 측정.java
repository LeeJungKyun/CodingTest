import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * n, k, l 이주어짐
 * n은 팀의 수, k는 팀원 3명의 레이팅 합에 대한 클럽 가입 조건, L은 개인 레이팅에 대한 클럽 가입 조건
 *
 */
public class Main {
	static int n, k, l;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int third = Integer.parseInt(st.nextToken());
			
			int sum = first + second + third;
			
			if(sum < k) continue;
			
			if(first < l || second < l || third < l) continue;
			
			cnt++;
			sb.append(first).append(' ').append(second).append(' ').append(third).append(' ');
		}
		
		StringBuilder finalSb = new StringBuilder();
		finalSb.append(cnt).append('\n').append(sb);
		
		System.out.println(finalSb);
	}
}