import java.io.*;
import java.util.*;

public class Main {
  static String S;
  static String T;
  static int result;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    S = br.readLine();
    T = br.readLine();

    dfs(T);

    System.out.println(result);
  }

  public static void dfs(String str) {
    int length = str.length();

    if (length == S.length()) {
      if (str.equals(S)) {
        result = 1;
      }
      return;
    }
    if (str.endsWith("A")) {
      dfs(str.substring(0, length - 1));
    }
    if (str.startsWith("B")) {
      dfs(new StringBuilder(str.substring(1)).reverse().toString());
    }
  }
}
