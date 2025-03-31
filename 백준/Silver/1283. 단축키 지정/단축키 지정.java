import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

  static int n;
  static boolean flag;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    HashSet<Character> set = new HashSet<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      String input = br.readLine();
      String[] words = input.split(" ");
      int wordIndex = -1;
      int charIndex = -1;
      flag = false;

      // 각 단어의 첫 글자 탐색
      for (int j = 0; j < words.length; j++) {
        char c = Character.toUpperCase(words[j].charAt(0));
        //단축키 지정이 가능하면 해당 단어의 인덱스와 글자의 인덱스를 각각 저장
        if (!set.contains(c)) {
          set.add(c);
          charIndex = 0;
          wordIndex = j;
          flag = true;
          break;
        }
      }
      // 못 찾았으면 전체 문자열 탐색
      if (!flag) {
        outer:
        for (int j = 0; j < words.length; j++) {
          for (int k = 0; k < words[j].length(); k++) {
            char c = Character.toUpperCase(words[j].charAt(k));
            if (!set.contains(c)) {
              set.add(c);
              charIndex = k;
              wordIndex = j;
              flag = true;
              break outer;
            }
          }
        }
      }

      //출력문

      for (int j = 0; j < words.length; j++) {
        if (j > 0) {
          sb.append(' ');
        }
        if (j == wordIndex) {
          for (int k = 0; k < words[j].length(); k++) {
            if (k == charIndex) {
              sb.append('[');
            }
            sb.append(words[j].charAt(k));
            if (k == charIndex) {
              sb.append(']');
            }
          }
        } else {
          sb.append(words[j]);
        }
      }
      sb.append('\n');
    }
    System.out.println(sb);
  }
}
