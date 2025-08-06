import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static ArrayList<Integer> friends[];
	static boolean[] visited;
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//동기의 수 n
		n = Integer.parseInt(br.readLine());
		friends = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		for(int i = 1; i <= n; i++)
			friends[i] = new ArrayList<>();
		
		//리스트의 길이 m
		m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			friends[a].add(b);
			friends[b].add(a);
		}
		
		//1번인 상근이의 친구와, 친구의 친구까지 결혼식에 초대
		//최대 depth 2까지 초대
		inviteFriends(1, 0);
		
		for(boolean visit : visited) {
			if(visit)
				result++;
		}
		
		//본인 빼고 카운트
		System.out.println(result - 1);
	}
	
	public static void inviteFriends(int studentNum, int depth) {
		if(depth == 2)
			return;
		visited[studentNum] = true;
		
		for(int friend : friends[studentNum]) {
			visited[friend] = true;
			inviteFriends(friend, depth + 1);
		}
	}
}
