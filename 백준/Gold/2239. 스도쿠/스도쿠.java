import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[9][9];
    static boolean [][][] check = new boolean[3][9][10];
    static boolean finish;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String line = br.readLine();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));

                if (arr[i][j] != 0) {
                    check[0][i][arr[i][j]] = true;
                    check[1][j][arr[i][j]] = true;
                    check[2][(i / 3) * 3 + j / 3][arr[i][j]] = true;
                }
            }
        }
        dfs(0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static void dfs(int x, int y) {
        //마지막 칸일 때
        if (x == 8 && y == 8) {
            //마지막 칸에 들어갈 번호 확인
            for (int i = 1; i <= 9; i++) {
                if (!check[0][8][i]) {
                    arr[8][8] = i;
                    break;
                }
            }
            finish = true;
            return;
        }

        if (arr[y][x] != 0) {
            if(x == 8)
                dfs(0, y + 1);
            else
                dfs(x + 1, y);
        } else{
            for (int i = 1; i <= 9; i++) {
                if (!check[0][y][i] && !check[1][x][i] && !check[2][(y / 3) * 3 + x / 3][i]) {
                    check[0][y][i] = true;
                    check[1][x][i] = true;
                    check[2][(y / 3) * 3 + x / 3][i] = true;

                    arr[y][x] = i;
                    if(x + 1 == 9)
                        dfs(0, y + 1);
                    else dfs(x + 1, y);

                    if(finish)
                        return;

                    arr[y][x] = 0;
                    check[0][y][i] = false;
                    check[1][x][i] = false;
                    check[2][(y / 3) * 3 + x / 3][i] = false;

                }
            }
        }
    }
}
