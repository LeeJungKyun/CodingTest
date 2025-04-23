import java.io.*;
import java.util.*;
public class Main {
  static LinkedHashSet<Long> set = new LinkedHashSet<>();
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    while (st.hasMoreTokens()) {
      set.add(Long.parseLong(st.nextToken()));
    }

    for (Long num : set) {
      sb.append(num).append(" ");
    }
    System.out.println(sb);
  }
}
