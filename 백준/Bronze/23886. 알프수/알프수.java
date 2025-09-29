import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String X = br.readLine();
        
        List<Integer> v = new ArrayList<>();
        int lenX = X.length();
        
        for (int i = 0; i < lenX - 1; i++) {
            v.add((int)X.charAt(i + 1) - (int)X.charAt(i));
        }
        
        boolean isAlpsoo = true;
        int lenV = v.size();

        if (lenV == 0) {
            isAlpsoo = false;
        } else {
            for (int val : v) {
                if (val == 0) {
                    isAlpsoo = false;
                    break;
                }
            }
            
            if (isAlpsoo) {
                if (v.get(0) < 0) {
                    isAlpsoo = false;
                }
            }
            
            if (isAlpsoo) {
                if (v.get(lenV - 1) > 0) {
                    isAlpsoo = false;
                }
            }
            
            if (isAlpsoo) {
                for (int i = 0; i < lenV - 1; i++) {
                    int current = v.get(i);
                    int next = v.get(i + 1);
                    
                    if ((current > 0 && next > 0) || (current < 0 && next < 0)) {
                        if (current != next) {
                            isAlpsoo = false;
                            break;
                        }
                    }
                }
            }
        }

        if (isAlpsoo) {
            System.out.println("ALPSOO");
        } else {
            System.out.println("NON ALPSOO");
        }
    }
}