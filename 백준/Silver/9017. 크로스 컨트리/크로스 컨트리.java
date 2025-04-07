import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      int n = Integer.parseInt(br.readLine());

      int[] rawInput = new int[n];      // 입력 순서 보관
      int[] teamCount = new int[201];   // 팀별 인원 수 카운트

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        int team = Integer.parseInt(st.nextToken());
        rawInput[i] = team;
        teamCount[team]++;
      }

      // 등수를 기록할 리스트
      ArrayList<Integer>[] list = new ArrayList[201];
      for (int i = 1; i <= 200; i++) {
        list[i] = new ArrayList<>();
      }

      // 유효한 팀(정확히 6명인 팀)에게만 등수를 부여
      int rank = 1;
      for (int i = 0; i < n; i++) {
        int team = rawInput[i];
        if (teamCount[team] == 6) {
          list[team].add(rank++);
        }
      }

      int minScore = Integer.MAX_VALUE;
      int answer = 0;

      for (int i = 1; i <= 200; i++) {
        if (list[i].size() < 6) continue;

        int score = 0;
        for (int j = 0; j < 4; j++) {
          score += list[i].get(j);
        }

        if (score < minScore) {
          minScore = score;
          answer = i;
        } else if (score == minScore) {
          if (list[i].get(4) < list[answer].get(4)) {
            answer = i;
          }
        }
      }

      System.out.println(answer);
    }
  }
}