import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Main {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        List<Long> v = new ArrayList<>();
        long[] fact = new long[d];
        fact[0] = 1;
        
        for (int i = 1; i < d; i++) {
            fact[i] = fact[i - 1] * d;
        }

        for (int i = 0; i < d; i++) {
            v.add((long) i);
        }

        boolean flag = true;
        long ans = -1;

        while (flag) {
            if (v.get(0) != 0) {
                long tmp = 0;
                for (int i = 0; i < d; i++) {
                    tmp += v.get(i) * fact[d - 1 - i];
                }
                if (tmp > N) {
                    ans = tmp;
                    break;
                }
            }
            flag = nextPermutation(v);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(ans);
        System.out.println(sb.toString());
    }

    static boolean nextPermutation(List<Long> v) {
        int i = v.size() - 2;
        while (i >= 0 && v.get(i) >= v.get(i + 1))
        	i--;
        if (i < 0)
        	return false;
        int j = v.size() - 1;
        while (v.get(i) >= v.get(j))
        	j--;
        Collections.swap(v, i, j);
        Collections.reverse(v.subList(i + 1, v.size()));
        return true;
    }
}