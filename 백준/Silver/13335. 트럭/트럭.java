import java.io.*;
import java.util.*;

public class Main {
  static int n, w, l;
  static Queue<Integer> waiting;
  static Queue<Integer> bridge;
  static int totalWeight;
  static int count;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    w = Integer.parseInt(st.nextToken());
    l = Integer.parseInt(st.nextToken());

    waiting = new LinkedList<>();
    bridge = new LinkedList<>();

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      waiting.add(Integer.parseInt(st.nextToken()));
    }

    //다리에 모두 0을 채워놓고 계속 pop 하고 넣으면 큐의 크기는 유지되고 무게에 영향 없음
    for (int i = 0; i < w; i++) {
      bridge.add(0);
    }

    while (!bridge.isEmpty()) {
      count++;
      totalWeight -= bridge.poll();
      if(!waiting.isEmpty()){
        int next = waiting.peek();
        //다리에 올라가 있는 트럭들과 다음 트럭의 무게합을 견딜 수 있으면
        if (totalWeight + next <= l) {
          bridge.add(waiting.poll());
          totalWeight += next;
        }
        else{
          bridge.add(0);
        }
      }
    }
    System.out.println(count);
  }
}
