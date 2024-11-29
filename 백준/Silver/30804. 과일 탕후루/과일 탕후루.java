import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static boolean[] exist;
    static int count;
    static int start, end;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        exist = new boolean[10];
        count = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(maxFruits());

    }

    private static int maxFruits() {
        Map<Integer, Integer> fruitCount = new HashMap<>();
        int maxLength = 0;
        int start = 0;

        for (int end = 0; end < n; end++) {
            fruitCount.put(arr[end], fruitCount.getOrDefault(arr[end], 0) + 1);

            while (fruitCount.size() > 2) {
                fruitCount.put(arr[start], fruitCount.get(arr[start]) - 1);
                if (fruitCount.get(arr[start]) == 0) {
                    fruitCount.remove(arr[start]);
                }
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }
}