import java.io.*;
import java.util.*;

public class Main {
    static int testCase;
    static int n, m;
    static int[] indegree;
    static ArrayList<Integer>[] list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            n = Integer.parseInt(br.readLine());
            indegree = new int[n + 1];
            list = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            int[] arr = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    list[arr[i]].add(arr[j]);
                    indegree[arr[j]]++;
                }
            }

            m = Integer.parseInt(br.readLine());
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                //제대로 된 정보이면
                if (list[a].contains(b)) {
                    list[a].remove((Integer) b);
                    list[b].add(a);
                    indegree[b]--;
                    indegree[a]++;
                } else {
                    list[b].remove((Integer) a);
                    list[a].add(b);
                    indegree[a]--;
                    indegree[b]++;
                }
            }

            //위상정렬 시작
            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                if(indegree[i] == 0)
                    queue.add(i);
            }

            boolean isAmbiguous = false;

            List<Integer> result = new ArrayList<>();

            while(!queue.isEmpty()){
                if(queue.size() > 1){
                    isAmbiguous = true;
                    break;
                }
                int cur = queue.poll();
                result.add(cur);

                for (int next : list[cur]) {
                    indegree[next]--;
                    if (indegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }

            if(result.size() < n){
                sb.append("IMPOSSIBLE\n");
            } else if (isAmbiguous) {
                sb.append("?\n");
            } else{
                for(int num : result)
                    sb.append(num).append(" ");
                sb.append('\n');
            }
        }

        System.out.println(sb);
    }
}
