import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        char[] ch = new char[N];
        Arrays.fill(ch,'?');

        for(int i = 0; i < H; i++){
            String line = br.readLine();
            for(int j = 0; j < N * W; j++){
                char c = line.charAt(j);
                if(c != '?'){
                    ch[j / W] = c;
                }
            }
        }

        for(int i = 0; i < N; i++){
            bw.write(String.valueOf(ch[i]));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}