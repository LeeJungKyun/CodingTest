import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) > Math.abs(o2)) {
                    return Math.abs(o1) - Math.abs(o2);
                } else if (Math.abs(o1) == Math.abs(o2)) {
                    return o1 - o2;
                } else return -1;
            }
        });

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if (x == 0) {
                if (pq.isEmpty()) {
                    sb.append("0").append('\n');
                } else {
                    sb.append(pq.poll()).append('\n');
                }
            } else {
                pq.offer(x);
            }
        }

        System.out.println(sb);

    }
}
