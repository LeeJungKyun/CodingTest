import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int num, count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
    static int n, m;
    static int[] indegree_x, indegree_y;
    static ArrayList<Node>[] list;
    static Queue<Node> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        indegree_x = new int[n + 1];
        indegree_y = new int[n + 1];

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            list[x].add(new Node(y, k));
            indegree_x[x]++;
            indegree_y[y]++;
        }

        int[] result = topologicalSort(n);

        for (int i = 1; i <= n; i++) {
            if(indegree_x[i] == 0)
                System.out.println(i + " " + result[i]);
        }
    }

    public static int[] topologicalSort(int n) {
        queue = new LinkedList<>();
        queue.offer(new Node(n, 1));
        int[] count = new int[n + 1];
        count[n] = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (Node next : list[cur.num]) {
                count[next.num] += count[cur.num] * next.count;
                indegree_y[next.num]--;
                if(indegree_y[next.num] == 0)
                    queue.add(new Node(next.num, count[next.num]));
            }
        }
        return count;
    }
}
