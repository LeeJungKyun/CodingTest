import java.io.*;
import java.util.*;

public class Main {
  static int k, l;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    k = Integer.parseInt(st.nextToken()); // 수강 가능 인원
    l = Integer.parseInt(st.nextToken()); // 총 신청 인원

    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

    for (int i = 0; i < l; i++) {
      String studentId = br.readLine();
      // 기존에 있으면 제거하고 다시 넣어 최신으로
      map.remove(studentId);
      map.put(studentId, i); // value는 중요하지 않음
    }

    int count = 0;
    for (String id : map.keySet()) {
      if (count++ == k) break;
      System.out.println(id);
    }
  }
}