import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr;
    static int leftVal, rightVal;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int near = arr[left] + arr[right];
            if (Math.abs(near) < min) {
                min = Math.abs(near);
                leftVal = arr[left];
                rightVal = arr[right];
            } else if(near > 0) {
                right--;
            } else {
                left++;
            }
        }
        System.out.println(leftVal + " " + rightVal);
    }
}
