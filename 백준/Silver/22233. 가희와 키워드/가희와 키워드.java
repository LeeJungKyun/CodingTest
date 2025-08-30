import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<String, Integer> keyword = new HashMap<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) keyword.put(br.readLine(), 1);

        for(int i=0; i<m; i++){
            String [] str = br.readLine().split(",");

            for(String post : str){
                if(keyword.containsKey(post) && keyword.get(post) == 1){
                    n--;
                    keyword.put(post, 0);
                }
            }

            System.out.println(n);
        }
    }
}