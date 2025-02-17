import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String S = br.readLine().toUpperCase();
			int count = 0;
			
			if(S.equals("#")) break;
			
			for(int i = 0; i < S.length(); i++) {
				if(S.charAt(i) == 'A' || S.charAt(i) == 'E' 
						|| S.charAt(i) == 'I' || S.charAt(i) == 'O' 
						|| S.charAt(i) == 'U') count++;
			}
			System.out.println(count);
		}
	}

}