import java.io.*;

/**
 * @author 이정균
 * 그리디 풀이 방식
 * 
 * 1. 5가 나올 수 있는 최대개수부터 시작
 * 2. 남은 무게 계산
 * 3. 3으로 나눠지면 성공이므로 종료 후 출력
 * 4. 안나눠지면 다음 5단위 계산
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = -1;  // 기본값: 불가능할 경우 -1

        //1. 5가 나올 수 있는 최대개수부터 시작
        for (int five = n / 5; five >= 0; five--) {
        	//2. 남은 무게 계산
            int remain = n - (five * 5); 
            //3. 3으로 나눠지면 성공이므로 출력
            if (remain % 3 == 0) { 
                int three = remain / 3;
                count = five + three;
                break; 
            }
            //4. 안나눠지면 다음 5단위 계산
        }

        System.out.println(count);
    }
}
