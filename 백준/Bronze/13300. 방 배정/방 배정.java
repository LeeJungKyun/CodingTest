import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n; //수학여행 참가 인원 수
    static int k; //한 방 최대 인원 수
    static int[][] arr; //학년 배열
    static int cnt = 0;

    /**
     * 성별 S와 학년 이 N줄 주어짐 여자 0, 남자 1
     * 한 방에는 같은 학년
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[6][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[y - 1][s]++;
        }

//        for (int i = 0; i < 6; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if (arr[i][j] > 0) {
                    if (arr[i][j] % 2 == 0)
                        cnt += (arr[i][j] / 2);
                    else cnt += (arr[i][j] / 2 + 1);
                }
            }
        }
        System.out.println(cnt);
    }
}
