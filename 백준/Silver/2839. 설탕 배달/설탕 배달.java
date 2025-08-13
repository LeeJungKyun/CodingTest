import java.io.*;
public class Main {
	static BufferedReader br;
	static int n;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		if(n == 4 || n == 7) {
			System.out.println(-1);
		}
		else if (n % 5 == 0)
			System.out.println(n / 5);
	    else if (n % 5 == 1 || n % 5 == 3)
	    	System.out.println((n / 5) + 1);
	    else if (n % 5 == 2 || n % 5 == 4)
	    	System.out.println((n / 5) + 2);
	}

}
