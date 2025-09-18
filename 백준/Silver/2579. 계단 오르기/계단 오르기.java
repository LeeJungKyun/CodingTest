import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[] arr = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int[] dp = new int[n+1];
		
		dp[0] = arr[0];
		dp[1] = arr[1];
		
		if(n >= 2) {
			dp[2] = arr[1] + arr[2];
		}
		
		for(int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
		}
		System.out.println(dp[n]);
	}
}
