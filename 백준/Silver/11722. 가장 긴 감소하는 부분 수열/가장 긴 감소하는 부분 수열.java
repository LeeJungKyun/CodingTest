import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int result = 0;
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] D = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
			D[i] = 1;
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<i;j++) {
				if(A[j] > A[i]) {
					D[i] = Math.max(D[i], D[j]+1);
				}
			}
			result = Math.max(result, D[i]);
		}
		
		bw.write(result+"");
		bw.flush();
		bw.close();
	}
}