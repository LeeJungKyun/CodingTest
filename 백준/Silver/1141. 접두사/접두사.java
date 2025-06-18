import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        words = new String[n];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        Arrays.sort(words, new Comparator<String>(){
            public int compare(String s1, String s2) {
                return s2.length() - s1.length();
            }
        });

        HashSet<String> set = new HashSet<>();
        for(String word : words){
            if (set.size() == 0) {
                set.add(word);
                continue;
            }

            boolean available = true;
            for (String other : set) {
                if(other.indexOf(word) == 0){
                    available = false;
                    break;
                }
            }
            if (available) {
                set.add(word);
            }
        }

        System.out.println(set.size());
    }
}
