import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int maxWidth, maxHeight;
    static int maxWidthIdx = -1, maxHeightIdx = -1;
    static int[] dir, dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        dir = new int[6];
        dist = new int[6];

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            //1,  2, 3,  4
            //동, 서, 남, 북
            dir[i] = Integer.parseInt(st.nextToken());
            dist[i] = Integer.parseInt(st.nextToken());

            //동쪽이나 서쪽으로 움직일때 최대 가로 길이 구하기
            if(dir[i] == 1 || dir[i] == 2){
                if (dist[i] > maxWidth) {
                    maxWidth = Math.max(maxWidth, dist[i]);
                    maxWidthIdx = i;
                }
            }
            //남쪽이나 북쪽으로 움직일때 최대 높이 구하기
            else{
                if (dist[i] > maxHeight) {
                    maxHeight = Math.max(maxHeight, dist[i]);
                    maxHeightIdx = i;
                }
            }
        }

        int wholeArea = maxWidth * maxHeight;

        int smallArea = dist[(maxWidthIdx + 3) % 6] * dist[(maxHeightIdx + 3) % 6];

        System.out.println((wholeArea - smallArea) * k);
    }
}
