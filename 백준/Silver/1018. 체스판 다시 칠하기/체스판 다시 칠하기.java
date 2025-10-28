import java.util.Scanner;

public class Main {

    public static boolean[][] chess;
    public static int min = 64;

    public static void find(int x, int y){
        int x_end = x + 8;
        int y_end = y + 8;
        int cnt = 0;

        boolean arr = chess[x][y];

        for(int i = x; i < x_end; i++){
            for(int j = y; j < y_end; j++){
                if(chess[i][j] != arr){
                    cnt++;
                }
                arr = !arr;
            }
            arr = !arr;
        }
        cnt = Math.min(cnt,64- cnt);
        min = Math.min(min,cnt);
    }

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = keyboard.nextInt();
        int m = keyboard.nextInt();
        chess = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String str = keyboard.next();

            for (int j = 0; j < m; j++) {
                if (str.charAt(j) == 'W') {
                    chess[i][j] = true;
                } else {
                    chess[i][j] = false;
                }
            }
        }
        for (int i = 0; i < n - 7; i++) {
            for (int j = 0; j < m - 7; j++) {
                find(i, j);
            }
        }
        System.out.println(min);
    }
}
