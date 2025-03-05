import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        HashMap<Long, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Long num = Long.parseLong(br.readLine());
            map.putIfAbsent(num, 0);
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
        }

        Long result = Long.MAX_VALUE;
        int max = 0;

        for (Long val : map.keySet()) {
            if (map.get(val) > max) {
                max = map.get(val);
                result = val;
            } else if (map.get(val) == max) {
                result = Math.min(result, val);
            }
        }
        System.out.println(result);
    }
}
