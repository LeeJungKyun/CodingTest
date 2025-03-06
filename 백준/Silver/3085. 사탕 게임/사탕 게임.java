import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static char[][] arr;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                swap(i, j);
            }
        }
        System.out.println(max);
    }

    public static int findMax(char[][] tempArr) {
        int n = tempArr.length;
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 1; j < n; j++) {
                if (tempArr[i][j] == tempArr[i][j - 1]) {
                    count++;
                } else {
                    count = 1;
                }
                maxLen = Math.max(maxLen, count);
            }
        }

        for (int j = 0; j < n; j++) {
            int count = 1;
            for (int i = 1; i < n; i++) {
                if (tempArr[i][j] == tempArr[i - 1][j]) {
                    count++;
                } else {
                    count = 1;
                }
                maxLen = Math.max(maxLen, count);
            }
        }
        return maxLen;
    }

    public static void swap(int y, int x) {
        if (y - 1 >= 0) max = Math.max(max, swapAndCheck(y, x, y - 1, x));
        if (y + 1 < n) max = Math.max(max, swapAndCheck(y, x, y + 1, x));
        if (x - 1 >= 0) max = Math.max(max, swapAndCheck(y, x, y, x - 1));
        if (x + 1 < n) max = Math.max(max, swapAndCheck(y, x, y, x + 1));
    }

    private static int swapAndCheck(int y1, int x1, int y2, int x2) {
        char[][] tempArr = copyArray(arr);

        char temp = tempArr[y1][x1];
        tempArr[y1][x1] = tempArr[y2][x2];
        tempArr[y2][x2] = temp;

        return findMax(tempArr);
    }

    private static char[][] copyArray(char[][] original) {
        int size = original.length;
        char[][] copy = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, size);
        }
        return copy;
    }
}