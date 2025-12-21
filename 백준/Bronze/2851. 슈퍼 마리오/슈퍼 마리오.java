import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] arr = new int[11];
        for(int i=1;i<=10;i++)
            arr[i] = arr[i-1] + Integer.parseInt(br.readLine());
        int dif = Integer.MAX_VALUE;
        int answer = 0;
        for(int i=10;i>0;i--){
            int temp = Math.abs(100 - arr[i]);
            if(dif > temp){
                dif = temp;
                answer = arr[i];
            }
        }
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}