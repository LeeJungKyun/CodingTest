import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
		int N = Integer.parseInt(br.readLine());
 
		for (int i = 0; i < N; i++) {	// 1 ~ N
 
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
 
			for (int k = 0; k < (2 * N - 1) - (2 * i); k++) {
				System.out.print("*");
			}
 
			System.out.println();
		}
 
		for (int i = 0; i < N - 1; i++) {
        
			for (int j = 1; j < (N - 1) - i; j++) {	
				System.out.print(" ");
			}
 
			for (int k = 0; k < 3 + 2 * i; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
 
}
 