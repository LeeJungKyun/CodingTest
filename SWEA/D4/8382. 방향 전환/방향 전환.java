import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 */

public class Solution {
	//클래스 선언부
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static Point start, end;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//1. 시작점과 끝점 입력
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			int endY = Integer.parseInt(st.nextToken());
			
			start = new Point(startX, startY);
			end = new Point(endX, endY);
			
			//필요한 변화량
			int dx = Math.abs(endX - startX);
			int dy = Math.abs(endY - startY);
			
			int max = Math.max(dx, dy);
			int min = Math.min(dx, dy);
			
			int diff = max - min;
			
			int result = 2 * min;
			// 남은 거리가 짝수일 때
			if (diff % 2 == 0) { 
                result += 2 * diff;
            }
			// 남은 거리가 홀수일 때
			else { 
                result += 2 * diff - 1;
            }
			
			
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}