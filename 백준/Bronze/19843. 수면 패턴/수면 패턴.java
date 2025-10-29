import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Main {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int t, n;
	static HashMap<String, Integer> weekDay;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		weekDay = new HashMap<>();
		weekDay.put("Mon", 0);
		weekDay.put("Tue", 1);
		weekDay.put("Wed", 2);
		weekDay.put("Thu", 3);
		weekDay.put("Fri", 4);
		
		//1. 일주일 자야 할 시간 t, 평일 동안 잠든 횟수 n
		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		//2. 잠든 시간 입력
		int sleepHour = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			//잠들기 시작한 시간 입력
			String day1 = st.nextToken();
			int day1ToInteger = weekDay.get(day1);
			int hour1 = Integer.parseInt(st.nextToken());
			
			//깨어난 시간 입력
			String day2 = st.nextToken();
			int day2ToInteger = weekDay.get(day2);
			int hour2 = Integer.parseInt(st.nextToken());
			
			if(day1ToInteger == day2ToInteger) {
				sleepHour += (hour2 - hour1);
			} else {
				int dayCount = day2ToInteger - day1ToInteger;
				sleepHour += ((24 * (dayCount - 1) + (24 - hour1) + hour2));
			}
		}
		
		int moreSleep = t - sleepHour;
		
		if(moreSleep <= 0) {
			System.out.println(0);
		} else if(moreSleep - 48 > 0) {
			System.out.println(-1);
		} else System.out.println(moreSleep);
	}
}