import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 에어컨은 매일 15, 18, 21시정각에 자동으로 꺼지고
 * 세번째로 꺼질때마다 K분간 멈춤
 * 
 * N일째의 에어컨이 꺼지는 시각을 예측해서 전원을 켜는 기계를 만들기
 * 
 * n일이 지나면 꺼지는 시간은 n * k 만큼 딜레이가 된다
 * 
 * 출력
 * N일째에 에어컨이 켜진 횟수 출력과
 * 해당 시각을 hh:mm형식으로 출력
 *
 */
public class Main {
	static class Aircon {
		int dayMinutes;
		int day;
		public Aircon(int dayMinutes, int day) {
			this.dayMinutes = dayMinutes;
			this.day = day;
		}
	}
	static final int DAY = 24 * 60;
	static int n, k;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		//1. 날짜 N과 지연되는 분 K 입력
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//0일째의 15:00
		Aircon aircon = new Aircon(900, 0);
		
		int[] offsetTime = {180, 180, 1080 + k};
		
		int idx = 0;
		
		List<Integer> resultList = new ArrayList<>();
		
		while(aircon.day <= n) {
			if(aircon.day == n) {
				resultList.add(aircon.dayMinutes);
			}
			
			if(aircon.day > n) {
				break;
			}
			
			aircon = calculateNext(aircon, offsetTime[idx]);
			
			idx = (idx + 1) % 3;
		}
		
		System.out.println(resultList.size());
		
		for(int dayMinutes : resultList) {
			System.out.println(format(dayMinutes));
		}
	}
	
	public static String format(int dayMinutes) {
		int hour = dayMinutes / 60;
		int minutes = dayMinutes % 60;
		return String.format("%02d:%02d", hour, minutes);
	}

	public static Aircon calculateNext(Aircon current, int addMinutes) {
		int nextMinutes = current.dayMinutes + addMinutes;
		
		int nextDay = current.day;
		if(nextMinutes >= DAY) {
			nextDay += nextMinutes / DAY;
		}
		
		int nextDayMinutes = nextMinutes % DAY;
		return new Aircon(nextDayMinutes, nextDay);
	}
}