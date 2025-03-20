import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Map<String, Integer> wordCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            if (word.length() < m)
                continue;

            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<String> words = new ArrayList<>(wordCount.keySet());

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //등장 횟수
                if (!wordCount.get(o1).equals(wordCount.get(o2))) {
                    return wordCount.get(o2) - wordCount.get(o1);
                }
                //단어 길이
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                }
                //사전 순
                return o1.compareTo(o2);
            }
        });

        for (String word : words) {
            sb.append(word).append("\n");
        }
        System.out.println(sb);
    }
}