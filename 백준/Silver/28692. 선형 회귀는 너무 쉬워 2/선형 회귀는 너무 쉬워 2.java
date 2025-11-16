import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        long S_x = 0;
        long S_y = 0;
        long S_xy = 0;
        long S_xx = 0;
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            long x_i = Long.parseLong(st.nextToken());
            long y_i = Long.parseLong(st.nextToken());
            
            S_x += x_i;
            S_y += y_i;
            S_xy += x_i * y_i;
            S_xx += x_i * x_i;
        }
        
        long denominator = N * S_xx - S_x * S_x; 
        
        if (denominator != 0) {
            double numerator_a = N * S_xy - S_x * S_y; 
            
            double a_2 = numerator_a / denominator;
            
            double b_2 = (S_y - a_2 * S_x) / (double)N;
            
            System.out.println(a_2 + " " + b_2);
        } else {
            System.out.println("EZPZ");
        }
    }
}