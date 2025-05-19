import java.io.*;
import java.util.*;

public class Main {
    static class Pillar implements Comparable<Pillar> {
        int x, h;
        public Pillar(int x, int h) {
            this.x = x;
            this.h = h;
        }
        public int compareTo(Pillar o) {
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Pillar> list = new ArrayList<>();
        int maxH = 0;
        int maxX = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            list.add(new Pillar(x, h));
            if (h > maxH) {
                maxH = h;
                maxX = x;
            } else if (h == maxH && x < maxX) {
                maxX = x;
            }
        }

        Collections.sort(list);

        int[] heightMap = new int[1001];

        for (Pillar p : list) {
            heightMap[p.x] = p.h;
        }

        int max = 0;
        int area = 0;
        for (int i = 0; i <= maxX; i++) {
            max = Math.max(max, heightMap[i]);
            area += max;
        }

        max = 0;
        for (int i = 1000; i > maxX; i--) {
            max = Math.max(max, heightMap[i]);
            area += max;
        }

        System.out.println(area);
    }
}