import java.io.*;
import java.util.*;

public class Main {
  static int T;
  static int k;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      StringBuilder sb = new StringBuilder();

      k = Integer.parseInt(br.readLine());
      //오름차순 정렬
      TreeMap<Long, Integer> map = new TreeMap<>();

      for(int i = 0; i < k; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        String cmd = st.nextToken();
        long num = Long.parseLong(st.nextToken());
        switch(cmd){
          case "I" :
            map.put(num, map.getOrDefault(num, 0) + 1);
            break;
          case "D" :
            if (map.isEmpty()) {
              continue;
            }
            long key = (num == 1) ? map.lastKey() : map.firstKey();
            int count = map.get(key);

            if (count == 1) {
              map.remove(key);
            } else{
              map.put(key, count - 1);
            }
        }
      }
      if(map.isEmpty()){
        sb.append("EMPTY");
      }
      else{
        sb.append(map.lastKey()).append(" ").append(map.firstKey());
      }
      System.out.println(sb);
    }
  }
}
