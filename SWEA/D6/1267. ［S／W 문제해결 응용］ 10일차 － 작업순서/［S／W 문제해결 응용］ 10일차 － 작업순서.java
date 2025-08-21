import java.io.*;
import java.util.*;

/**
 * @author 이정균 해야 할 V개의 작업 선행관계
 * 
 *         위상정렬
 *
 */
public class Solution {
	static final int testCase = 10;
	static int[] indegree;
	static ArrayList<Integer>[] list;
	static int v, e;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			// 인접리스트 선언
			list = new ArrayList[v + 1];
			for (int i = 1; i <= v; i++) {
				list[i] = new ArrayList<>();
			}

			// 인디그리 배열 선언
			indegree = new int[v + 1];

			// 간선 입력
			for (int i = 0; i < e; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				list[from].add(to);
				indegree[to]++;
			}

			sb.append('#').append(tc).append(' ');
			topologicalSort();
		}
		System.out.println(sb);
	}

	public static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();

		for (int i = 1; i <= v; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}

		while (!queue.isEmpty()) {
			// 들어오는 간선이 없는 경우 우선순위가 없으니 바로 출력
			int cur = queue.poll();
			sb.append(cur).append(" ");

			// 현재 숫자보다 뒤에 와야 하는 숫자들을 탐색
			for (int next : list[cur]) {
				indegree[next]--;
				if (indegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		sb.append('\n');
	}
}