import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Solution {
	//클래스 선언부

	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int cardCount;
	static String[] cardArray;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//1. 카드 개수 입력
			cardCount = Integer.parseInt(br.readLine());
			
			//2. 카드 정보 입력
			cardArray = new String[cardCount];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < cardCount; i++) {
				cardArray[i] = st.nextToken();
			}
			
			sb.append('#').append(tc).append(' ');
			
			ArrayDeque<String> aboveCards = new ArrayDeque<>();
			ArrayDeque<String> downCards = new ArrayDeque<>();
			
			int mid = (cardCount + 1) / 2;
			for(int i = 0; i < mid; i++) {
				aboveCards.add(cardArray[i]);
			}
			
			for(int i = mid; i < cardCount; i++) {
				downCards.add(cardArray[i]);
			}
			
			for(int i = 0; i < cardCount; i++) {
				if(i % 2 == 0)
					sb.append(aboveCards.poll()).append(' ');
				else sb.append(downCards.poll()).append(' ');
			}
			
			sb.append('\n');
		}
		System.out.println(sb);
	}
}