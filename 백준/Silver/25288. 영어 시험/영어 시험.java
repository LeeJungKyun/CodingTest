import java.io.*;
import java.util.*;

public class Main {
  static int n;
  static String str;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    str = br.readLine();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append(str);
    }
    System.out.println(sb);
  }
}
