import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] a, b, c, d;
    static int[] ab, cd;
    static StringTokenizer st;
    static int left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        a = new int[n];
        b = new int[n];
        c = new int[n];
        d = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            c[i] = Integer.parseInt(st.nextToken());
            d[i] = Integer.parseInt(st.nextToken());
        }

        ab = new int[n * n];
        cd = new int[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ab[i * n + j] = a[i] + b[j];
                cd[i * n + j] = c[i] + d[j];
            }
        }

        Arrays.sort(ab);
        Arrays.sort(cd);

        left = 0;
        right = n * n - 1;

        long count = 0;

        while (left < n * n && right > -1) {
            long leftVal = ab[left];
            long rightVal = cd[right];

            long sum = leftVal + rightVal;

            if(sum < 0)
                left++;
            else if(sum > 0)
                right--;
            else{
                long leftCount = 0, rightCount = 0;
                while(left < n * n && leftVal == ab[left]){
                    left++;
                    leftCount++;
                }
                while(right > -1 && rightVal == cd[right]){
                    right--;
                    rightCount++;
                }
                count += leftCount * rightCount;
            }
        }
        System.out.println(count);
        br.close();
    }
}
