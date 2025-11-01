import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        String[] num = new String[] {"000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010"};

        // N개의 문자 검사
        for (int i = 0; i < N; i++)
        {
            int check = 0;  // 일치하는 문자가 없을 경우 체크

            // 8개 문자와 비교하기
            for (int j = 0; j < 8; j++)
            {
                int count = 0;  // 각 자리가 일치하지 않는 경우 카운트

                // 각 자리마다 비교하기
                for (int k = 0; k < 6; k++) {
                    if (str.charAt(k) != num[j].charAt(k)) {
                        count++;
                        if (count > 1) break;
                    }
                }

                if (count == 0 || count == 1) {
                    sb.append((char)(j+'A'));
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                System.out.println(i+1);
                return;
            }
            else str = str.substring(6);
        }
        System.out.println(sb);
    }
}