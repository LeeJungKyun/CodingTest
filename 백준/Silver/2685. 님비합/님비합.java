import java.io.*;
import java.util.*;

public class Main {
    static int testCase;
    static int b, x, y;
    static ArrayList<Integer> xList, yList, resultList;
    static BufferedReader br;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(br.readLine());

            b = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            xList = new ArrayList<>();
            yList = new ArrayList<>();

            // x를 b진수로 변환
            int tempX = x;
            if (tempX == 0) {
                xList.add(0);
            } else {
                while (tempX > 0) {
                    xList.add(0, tempX % b);
                    tempX /= b;
                }
            }

            // y를 b진수로 변환
            int tempY = y;
            if (tempY == 0) {
                yList.add(0);
            } else {
                while (tempY > 0) {
                    yList.add(0, tempY % b);
                    tempY /= b;
                }
            }
            
            // 두 리스트의 길이를 맞춰주기
            int maxLength = Math.max(xList.size(), yList.size());
            while (xList.size() < maxLength) {
                xList.add(0, 0);
            }
            while (yList.size() < maxLength) {
                yList.add(0, 0);
            }
            
            resultList = new ArrayList<>();
            // 님비 합 계산
            for (int i = 0; i < maxLength; i++) {
                int sum = xList.get(i) + yList.get(i);
                resultList.add(sum % b);
            }
            
            // 10진수 최종 결과 계산
            long result = 0;
            long pow = 1;
            
            for (int i = resultList.size() - 1; i >= 0; i--) {
                result += resultList.get(i) * pow;
                pow *= b;
            }
            
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}