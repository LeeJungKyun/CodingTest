import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] arr;
    static int[] result;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        result = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        backTrack(0);
        System.out.println(sb);
    }

    public static void backTrack(int depth) {
        //m개를 다 채웠으면 출력
        if(depth == m){
            for(int i = 0; i < m; i++){
                sb.append(result[i] + " ");
            }
            sb.append('\n');
            return;
        }

        int before = -1;
        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            if (before != cur) {
                before = cur;
                result[depth] = arr[i];
                backTrack(depth + 1);
            }
        }
    }
}
