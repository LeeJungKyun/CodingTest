import java.io.*;

/**
 * @author 이정균
 * 
 * 1. n입력
 * 2. 배열 입력
 * 3. divide재귀함수 @param x, y, size
 * 	3-1. 색깔 확인해서 x ~ x + size, y ~ y + size가 다 같은 색이면 해당 색깔 출력
 * 	3-2. 다르면 ( 출력
 * 	3-3. size /= 2해서 재귀
 *  3-4. 다 끝나면 ) 출력
 */
public class Main {
    static int n;
    static int[][] arr;
    static BufferedReader br;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	//1. n입력
        n = Integer.parseInt(br.readLine());
        //2. 배열 입력
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        //3. divide재귀함수 @param x, y, size
        divide(0, 0, n);

        System.out.println(sb);

    }

    public static void divide(int x, int y, int size) {
    	//3-1. 색깔 확인해서 x ~ x + size, y ~ y + size가 다 같은 색이면 해당 색깔 출력
        if (checkColor(x, y, size)) {
            if (arr[x][y] == 1) {
                sb.append(1);
            } else if (arr[x][y] == 0) {
                sb.append(0);
            }
        } else {
        	//3-2. 다르면 ( 출력
            sb.append('(');
            //3-3. size /= 2해서 재귀
            int newSize = size / 2;

            divide(x, y, newSize); //좌상
            divide(x, y + newSize, newSize); //우상
            divide(x + newSize, y, newSize); //좌하
            divide(x + newSize, y + newSize, newSize); //우하
            //3-4. 다 끝나면 ) 출력
            sb.append(')');

        }
    }

    //x ~ x + size, y ~ y + size가 다 같은 색인지 확인
    public static boolean checkColor(int x, int y, int size) {
        int color = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
