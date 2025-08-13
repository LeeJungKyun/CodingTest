import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 1. n, m, r입력
 * 2. n x m 배열 입력
 * 3. 내가 회전시켜야 하는 겹의 개수 layer -> min(n, m) / 2
 * 4. r번 회전시키는 반복문
 * 5. 레이어의 개수만큼 반복
 * 6. 레이어의 인덱스 [0, 0] ~ [layer, layer] 부터 시작해서 시작 지점을 temp로 잡아두고 각 행 밀기 위 -> 오른쪽 -> 아래 -> 왼쪽
 * 7. 배열 출력
 */
public class Main {
    static int n, m, r;
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //1. n, m, r입력
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        //2. n x m 배열 입력
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //3. 내가 회전시켜야 하는 겹의 개수 layer -> min(n, m) / 2
        int layer = Math.min(n, m) / 2;

        //4. r번 회전시키는 반복문
        for (int i = 0; i < r; i++) {
            //5. 레이어의 개수만큼 반복
            for (int j = 0; j < layer; j++) {
                //6. 레이어의 인덱스 [0, 0] ~ [layer, layer] 부터 시작해서 시작 지점을 temp로 잡아두고
            	//각 행 밀기 위 -> 오른쪽 -> 아래 -> 왼쪽
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
                arr[j + 1][j] = temp;
            }
        }

        //7. 배열 출력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
