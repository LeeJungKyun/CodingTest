import java.io.*;
import java.util.*;

public class Main {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int targetChannel, disableCount;
	static String targetChannelString;
	static ArrayList<Integer> disabledButton;
	static int currentChannel = 100;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		//코드 작성 시작
		targetChannel = Integer.parseInt(br.readLine());
		disableCount = Integer.parseInt(br.readLine());
		targetChannelString = String.valueOf(disableCount);
		
		//끝낼 수 있으면 끝내기
		if(targetChannel == currentChannel) {
			System.out.println(0);
			return;
		}
		
		
		disabledButton = new ArrayList<>();
		if(disableCount > 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < disableCount; i++) {
				disabledButton.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		
		int result = Math.abs(currentChannel - targetChannel);
		
		int count = 0;
		int min = MAX;
		
		for(int channel = 0; channel <= 999999; channel++) {
			String channelString = String.valueOf(channel);
			boolean check = true;
			
			for(int digit = 0; digit < channelString.length(); digit++) {
				if(disabledButton.contains(channelString.charAt(digit) - '0')){
					check = false;
					break;
				}
			}
			
			if(check == false) continue;
			
			count = channelString.length() + Math.abs(channel - targetChannel);
			
			if(count < min)
				min = count;
		}
		
		if(min < result)
			result = min;
		System.out.print(result);
	}
}