import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Depressed {
        int startIndex;
        int length;
        int index2T;
        int index3T;

        public Depressed(int startIndex, int length) {
            this.startIndex = startIndex;
            this.length = length;
            this.index2T = Math.max(startIndex - length * 2, 0);
            this.index3T = Math.max(startIndex - length * 3, 0);
        }
    }

    static BufferedReader br;
    static int n;
    static int[] arr;
    static ArrayList<Depressed> depressedList;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) < 0 ? 0 : 1;
        }

        depressedList = new ArrayList<>();
        int tMax = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == 1) continue;

            int length = 0;
            int startIndex = i;
            while (i < n && arr[i] == 0) {
                length++;
                i++;
            }
            i--;

            Depressed d = new Depressed(startIndex, length);
            depressedList.add(d);
            tMax = Math.max(tMax, length);
        }

        boolean[] flowerDay = new boolean[n];

        for (Depressed d : depressedList) {
            for (int i = d.index2T; i < d.startIndex; i++) {
                flowerDay[i] = true;
            }
        }

        int baseFlowerCount = 0;
        for (boolean b : flowerDay) {
            if (b) baseFlowerCount++;
        }

        int maxAdditionalFlowers = 0;

        for (Depressed d : depressedList) {
            if (d.length == tMax) {
                int currentAdditionalFlowers = 0;

                for (int i = d.index3T; i < d.index2T; i++) {
                    if (!flowerDay[i]) {
                        currentAdditionalFlowers++;
                    }
                }
                maxAdditionalFlowers = Math.max(maxAdditionalFlowers, currentAdditionalFlowers);
            }
        }

        System.out.println(baseFlowerCount + maxAdditionalFlowers);
    }
}