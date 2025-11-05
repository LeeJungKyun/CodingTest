import java.io.*;

public class Main {
	static final String str = ":fan:";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        
        for(int i = 0; i < 3; i++) {
        	for(int j = 0; j < 3; j++) {
        		if(i == 1 && j == 1)
        			System.out.print(":" + input + ":");
        		else System.out.print(str);
        	}
        	System.out.println();
        }
    }
}
