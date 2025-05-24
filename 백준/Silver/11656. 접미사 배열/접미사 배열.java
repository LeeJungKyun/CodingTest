import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        List<String> suffixes = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            suffixes.add(str.substring(i));
        }

        Collections.sort(suffixes);
        
        for (String s : suffixes) {
            System.out.println(s);
        }
    }
}