import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author 이정균
 * 
 * 서로 다른 두 에너지 드링크를 골라서 다 붓는데 그 중에서 원래 양의 절반을 바닥에 흘림
 * A -> B = A / 2 + B 가 된다
 * 
 * 에너지 드링크가 하나만 남을 때까지 반복
 * 
 * 합쳐진 에너지 드링크의 양을 최대로 하려고 한다.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        double[] energyDrinks = new double[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            energyDrinks[i] = Double.parseDouble(st.nextToken());
        }

        Arrays.sort(energyDrinks);

        double total = energyDrinks[n - 1];

        for (int i = 0; i < n - 1; i++) {
            total += energyDrinks[i] / 2.0;
        }

        // DecimalFormat을 사용하여 소수점 출력 형식 지정
        DecimalFormat df = new DecimalFormat("#.0");
        String result = df.format(total);

        // 결과가 '.0'으로 끝나면 정수 부분만 출력
        if (result.endsWith(".0")) {
            System.out.println(result.substring(0, result.length() - 2));
        } else {
            System.out.println(result);
        }
    }
}