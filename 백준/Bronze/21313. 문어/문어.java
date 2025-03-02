import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static BufferedWriter bw = 
			new BufferedWriter( new OutputStreamWriter(System.out));
	private static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt( br.readLine() );
		String ans = "";
		
		if( n%2==0 ) 
			for( int i=0; i<n/2; i++ )
				ans += "1 2 ";
		else if( n%2==1 )
			for( int i=0; i<(n/2)+1; i++ )
				if( i==(n/2) ) ans += "3";
				else ans += "1 2 ";
		
		bw.write( ans + "\n" );
		bw.flush();
		bw.close();
		br.close();
	}
}