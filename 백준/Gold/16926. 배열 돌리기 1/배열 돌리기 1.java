import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, r;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //겹 개수
        int layer = Math.min(n, m) / 2;

        //전체 배열을 r번 회전시킬건데
        for (int i = 0; i < r; i++) {
            //레이어의 개수만큼 반복
            for (int j = 0; j < layer; j++) {
                //j번째 layer에 대해서 j, j부터 시작
                int temp = arr[j][j];

                //위쪽 행
                for (int k = j + 1; k < m - j; k++) {
                    arr[j][k - 1] = arr[j][k];
                }
                //오른쪽 행
                for (int k = j + 1; k < n - j; k++) {
                    arr[k - 1][m - 1 - j] = arr[k][m - 1 - j];
                }
                //아래쪽 행
                for (int k = m - 2 - j; k >= j; k--) {
                    arr[n - 1 - j][k + 1] = arr[n- 1 - j][k];
                }
                //왼쪽 열
                for (int k = n - 2 - j; k >= j; k--) {
                    arr[k + 1][j] = arr[k][j];
                }
                arr[j+1][j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
