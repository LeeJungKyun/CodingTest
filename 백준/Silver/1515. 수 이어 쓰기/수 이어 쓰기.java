import java.io.*;
import java.util.*;

public class Main {
  static String num;
  static int result;
  static int pt;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    num = br.readLine();

    result = 0;
    pt = 0;

    while (result++ <= 30000) {
      String tmp = String.valueOf(result);
      for (int i = 0; i < tmp.length(); i++) {
        if (tmp.charAt(i) == num.charAt(pt)) {
          pt++;
        }
        if (pt == num.length()) {
          System.out.println(result);
          return;
        }
      }
    }
  }
}
