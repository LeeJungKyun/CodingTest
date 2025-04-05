import java.util.*;
import java.io.*;

public class Main {
  static class Camera{
    int x;
    int y;

    public Camera(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static int n, m;
  static int[][] arr;
  static int answer = Integer.MAX_VALUE;
  static int[][] tempArr;
  static ArrayList<Camera> cctvList;
  static int[] dx = {1, 0, -1, 0};
  static int[] dy = {0, 1, 0, -1};
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new int[n][m];
    tempArr = new int[n][m];
    cctvList = new ArrayList<>();

    for(int i = 0; i < n; i++){
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < m; j++){
        arr[i][j] = Integer.parseInt(st.nextToken());
        //CCTV 인경우
        if (arr[i][j] != 0 && arr[i][j] != 6) {
          cctvList.add(new Camera(i, j));
        }
      }
    }

    //모든 경우의 수를 확인 -> CCTV의 개수별로 4진수가 정해짐
    for (int mask = 0; mask < (1 << (2 * cctvList.size())); mask++) {
      //배열 복사
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          tempArr[i][j] = arr[i][j];
        }
      }

      int temp = mask;
      for (int i = 0; i < cctvList.size(); i++) {
        int dir = temp % 4;
        temp /= 4;
        //i 번째 CCTV의 좌표를 가져와서 무슨 CCTV 인지 확인
        int x = cctvList.get(i).x;
        int y = cctvList.get(i).y;

        //CCTV 감시범위 체크
        switch(arr[x][y]) {
          //한쪽 방향
          case 1:
            watch(x, y, dir);
            break;

          //양쪽방향
          case 2:
            watch(x, y, dir);
            watch(x, y, dir + 2);
            break;
          //ㄱ자 방향
          case 3:
            watch(x, y, dir);
            watch(x, y, dir + 1);
            break;

          //세방향
          case 4:
            watch(x, y, dir);
            watch(x, y, dir + 1);
            watch(x, y, dir + 2);
            break;
          //전방향
          case 5:
            watch(x, y, dir);
            watch(x, y, dir + 1);
            watch(x, y, dir + 2);
            watch(x, y, dir + 3);
            break;
        }
      }

      //사각지대 카운트해주기
      int count = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (tempArr[i][j] == 0) {
            count++;
          }
        }
      }
      answer = Math.min(answer, count);
    }
    System.out.println(answer);
  }

  public static void watch(int x, int y, int dir) {
    //dir이 4를 넘어갈 가능성이 있음
    dir %= 4;

    while (true) {
      x += dx[dir];
      y += dy[dir];

      //격자를 벗어났거나 벽에 부딪히면
      if (x < 0 || x >= n || y < 0 || y >= m || arr[x][y] == 6) {
        return;
      }
      //0이 아니면 다음곳으로 이동
      if (tempArr[x][y] != 0) {
        continue;
      }
      tempArr[x][y] = 7;
    }
  }
}
