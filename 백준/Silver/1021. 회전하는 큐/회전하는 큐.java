import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static LinkedList<Integer> list;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new LinkedList<>();
        int count = 0;

        for (int i = 1; i <= n; i++) {
            list.offer(i);
        }

        arr = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            int targetIdx = list.indexOf(arr[i]);
            int half;

            if(list.size() % 2 == 0)
                half = list.size() / 2 - 1;
            else half = list.size() / 2;

            if (targetIdx <= half) {
                for (int j = 0; j < targetIdx; j++) {
                    int temp = list.pollFirst();
                    list.offerLast(temp);
                    count++;
                }
            } else{
                for (int j = 0; j < list.size() - targetIdx; j++) {
                    int temp = list.pollLast();
                    list.offerFirst(temp);
                    count++;
                }
            }
            list.pollFirst();
        }
        System.out.println(count);
    }
}
