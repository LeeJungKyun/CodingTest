import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[] arr;
    static int startCount;
    static int redCount, blueCount;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        String line = br.readLine();
        arr = line.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'R')
                redCount++;
            else blueCount++;
        }

        /**
         * 1. R을 옮길 경우
         *  1.1 R_B_
         *  1.2 B_R_
         * 2. B를 옮길 경우
         *  2.1 R_B_
         *  2.2 B_R
         */

        //1.1 케이스
        startCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 'R')
                startCount++;
            else break;
        }

        ans = Math.min(ans, redCount - startCount);

        //1.2 케이스
        startCount = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 'R')
                startCount++;
            else break;
        }

        ans = Math.min(ans, redCount - startCount);

        //2.1 케이스
        startCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 'B')
                startCount++;
            else break;
        }

        ans = Math.min(ans, blueCount - startCount);

        //2.2 케이스
        startCount = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 'B')
                startCount++;
            else break;
        }

        ans = Math.min(ans, blueCount - startCount);

        System.out.println(ans);
    }
}
