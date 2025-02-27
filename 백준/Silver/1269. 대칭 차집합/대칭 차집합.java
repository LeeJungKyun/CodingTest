import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int aSum, bSum;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        HashSet<Integer> aHash = new HashSet<>();
        HashSet<Integer> bHash = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            aHash.add(Integer.parseInt(st.nextToken()));
        }
        aSum = aHash.size();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            bHash.add(Integer.parseInt(st.nextToken()));
        }
        bSum = bHash.size();

        for (Integer hash : aHash) {
            if (bHash.contains(hash)) {
                aSum--;
            }
        }

        for (Integer hash : bHash) {
            if (aHash.contains(hash)) {
                bSum--;
            }
        }

        System.out.println(aSum + bSum);
    }
}
