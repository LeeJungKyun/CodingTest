import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] arrA;
    static int[] arrB;
    static LinkedHashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arrA = new int[n];
        arrB = new int[m];

        set = new LinkedHashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrA);

        for (int num : arrA) {
            set.add(num);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
            set.remove(arrB[i]);
        }

        if (set.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(set.size());
            for (int num : set) {
                System.out.print(num + " ");
            }
        }
    }
}
