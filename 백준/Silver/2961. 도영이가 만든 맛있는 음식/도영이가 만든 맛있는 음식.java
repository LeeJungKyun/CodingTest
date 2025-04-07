import java.util.*;
import java.io.*;

public class Main {
  static int n;
  static int taste[][];
  static int answer = Integer.MAX_VALUE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    taste = new int[n][2];
    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      taste[i][0] = s;
      taste[i][1] = b;
    }

    //재료 조합 고르는 mask 변수
    for (int mask = 1; mask <= (1 << n) - 1; mask++) {
      //어떤 재료가 들어갔는지 확인
      int sour = 1;
      int bitter = 0;
      for (int i = 0; i < n; i++) {
        //재료가 골라졌다면
        if ((mask & (1 << i)) != 0) {
          sour *= taste[i][0];
          bitter += taste[i][1];
        }
      }

      int flavor = Math.abs(sour - bitter);
      answer = Math.min(answer, flavor);
    }

    System.out.println(answer);
  }
}
