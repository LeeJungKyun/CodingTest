import java.io.*;
import java.util.*;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        LinkedList<Integer> rank1 = new LinkedList<>();
        rank1.add(0);

        int currentRank;

        for (int i = 1; i <= n; i++) { // i는 선수 번호
            currentRank = Integer.parseInt(br.readLine());

            if (currentRank < rank1.size()) {
                rank1.add(currentRank, i);
            } else {
                rank1.add(i);
            }
        }
        
        LinkedList<Integer> rank2 = new LinkedList<>();
        rank2.add(0);
        
        for (int mRank = m; mRank >= 1; mRank--) {
            int playerNumber = rank1.get(mRank);
            currentRank = Integer.parseInt(br.readLine());

            if (currentRank < rank2.size()) {
                rank2.add(currentRank, playerNumber);
            } else {
                rank2.add(playerNumber);
            }
        }

        System.out.println(rank2.get(1));
        System.out.println(rank2.get(2));
        System.out.println(rank2.get(3));
    }
}