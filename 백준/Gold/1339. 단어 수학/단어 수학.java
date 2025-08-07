/**
 * @author 이정균
 * N개의 알파벳 대문자 단어
 * 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꿔서 N 개의 수를 합하는 문제
 * 두 개 이상의 알파벳이 같은 숫자로 바뀌어지면 안된다.
 * GCF + ACDEB -> 783 + 98654
 *
 * 1. 단어 수 입력
 * 2. 단어 입력 받기 + 사용된 알파벳 추출
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static String[] words;
    static List<Character> letterList = new ArrayList<>();
    static boolean[] isSelected = new boolean[10];
    static int[] charToDigit = new int[26]; // 알파벳 -> 숫자 매핑
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 단어 수 입력
        n = Integer.parseInt(br.readLine());
        words = new String[n];

        // 2. 단어 입력 받기 + 사용된 알파벳 추출
        boolean[] used = new boolean[26];
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
            for (char c : words[i].toCharArray()) {
                if (!used[c - 'A']) {
                    used[c - 'A'] = true;
                    letterList.add(c);
                }
            }
        }

        getPermutations(0, new int[letterList.size()]);
        System.out.println(max);
    }

    // 순열 생성: letterList.size()개의 알파벳에 대해 0~9 중에서 숫자를 부여하는 모든 경우 탐색
    static void getPermutations(int depth, int[] perm) {
        if (depth == letterList.size()) {
            // 매핑 생성
            for (int i = 0; i < perm.length; i++) {
                charToDigit[letterList.get(i) - 'A'] = perm[i];
            }

            // 단어 계산
            int sum = 0;
            for (String word : words) {
                int wordVal = 0;
                for (char c : word.toCharArray()) {
                    wordVal = wordVal * 10 + charToDigit[c - 'A'];
                }
                sum += wordVal;
            }

            max = Math.max(max, sum);
            return;
        }

        // 0~9 숫자 중 선택 (중복 없이)
        for (int i = 0; i < 10; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                perm[depth] = i;
                getPermutations(depth + 1, perm);
                isSelected[i] = false;
            }
        }
    }
}
