import java.io.*;
import java.util.*;

public class Main {
	static int r, b;
	static int l, w;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		l = 0;
		w = 0;
		
		for(int i = 1; i <= 5000; i++) {
			for(int j = 1; j <= 4000; j++) {
				if(r + b == i * j) {
					if(r == (i * 2) + (j - 2) * 2) {
						l = i;
						w = j;
						break;
					}
					else if(r == (j * 2) + (i - 2) * 2) {
						l = i;
						w = j;
						break;
					}
				}
			}
		}
		
		System.out.printf("%d %d", l, w);
	}
}