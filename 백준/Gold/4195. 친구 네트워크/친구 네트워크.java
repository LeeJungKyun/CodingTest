import java.io.*;
import java.util.*;

public class Main {
	static int testCase;
	static int f;
	static int[] parent, depth;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= testCase; tc++) {
			f = Integer.parseInt(br.readLine());

			parent = new int[f * 2];
			depth = new int[f * 2];

			for (int i = 0; i < 2 * f; i++) {
				parent[i] = i;
				depth[i] = 1;
			}

			int idx = 0;
			Map<String, Integer> map = new HashMap<>();

			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();

				if (!map.containsKey(f1)) {
					map.put(f1, idx++);
				}

				if (!map.containsKey(f2)) {
					map.put(f2, idx++);
				}

				sb.append(union(map.get(f1), map.get(f2)) + "\n");
			}
		}
		
		System.out.println(sb);
	}

	public static int union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
			depth[x] += depth[y];
		}

		return depth[x];
	}

	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}
}