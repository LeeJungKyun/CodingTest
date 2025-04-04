import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int n, m;
  static String[] arr;
  static int maxSongCount = -1;
  static int minGuitarCount = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    arr = new String[n];

    for (int idx = 0; idx < n; idx++){
      st = new StringTokenizer(br.readLine());
      st.nextToken();
      String str = st.nextToken();
      StringBuilder sb = new StringBuilder();

      //기타 길이
      for (int i = 0; i < str.length(); i++) {
        sb.append(str.charAt(i) == 'Y' ? "1" : "0");
      }

      arr[idx] = String.valueOf(sb);
    }

    //조합 확인 1 ~ n 까지 2진수로 백트래킹
    for (long mask = 1; mask < (1L << n); mask++) {
      long songMask = 0;
      int guitarCount = 0;

      //각 기타에 대해서
      for (int i = 0; i < n; i++) {
        //i번째 기타가 켜져 있으면
        if ((mask & (1L << i)) != 0) {
          //i번째 기타랑 OR 연산을 통해 나올 수 있는 곡의 개수 확인
          songMask |= Long.parseLong(arr[i], 2);
          guitarCount++;
        }
      }

      //bitCount로 몇개 칠 수 있는지 확인
      int songCount = Long.bitCount(songMask);
      if (songCount > maxSongCount) {
        maxSongCount = songCount;
        minGuitarCount = guitarCount;
      } else if (songCount == maxSongCount) {
        minGuitarCount = Math.min(minGuitarCount, guitarCount);
      }
    }
    System.out.println(maxSongCount == 0 ? -1 : minGuitarCount);
  }
}
