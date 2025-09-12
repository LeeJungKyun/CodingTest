import java.io.*;
import java.util.*;

/**
 * @author 이정균
 *
 * 렁이가 소개팅을 함ㅋㅋ
 * 
 * 임의의 두마리 렁이를 매칭해서 하나가 다른 지렁이로 가는데 벡터의 합이 적어야함
 * |V| = x * x + y * y
 * 
 * 모든 지렁이들을 매칭시켜서 소개팅을 시켰을 때, 벡터의 크기의 합이 최소
 * 
 * 이것도 완탐인가?
 * 
 * 그냥 두그룹으로 나누어서 ㄱㄱ
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
	static int n; //지렁이 개수
	static long minVectorSum;
	static Point[] wormList;
	static boolean[] isUsed;
	static ArrayList<int[]> matchedWorms = new ArrayList<>();

	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			minVectorSum = Long.MAX_VALUE;
			
			//1. 지렁이 개수 입력
			n = Integer.parseInt(br.readLine());
			
			//2. 지렁이 좌표 입력
			//좌표는 절대값이 100,000 이하의 정수
			wormList = new Point[n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				wormList[i] = new Point(x, y);
			}
			
			//3. 지렁이를 그냥 반으로 나눠서 한 그룹은 + 벡터, 한 그룹은 -벡터로 생각
			isUsed = new boolean[n];
			selectingWorm(0, 0);
			
			sb.append('#').append(tc).append(' ').append(minVectorSum).append('\n');
		}
		System.out.println(sb);
	}
	
    public static void selectingWorm(int idx, int count) {
    	if (count == n / 2) {
            long sumX = 0, sumY = 0;
            for (int i = 0; i < n; i++) {
                if (isUsed[i]) {
                    sumX += wormList[i].x;
                    sumY += wormList[i].y;
                } else {
                    sumX -= wormList[i].x;
                    sumY -= wormList[i].y;
                }
            }
            long vectorSum = sumX * sumX + sumY * sumY;
            if (vectorSum < minVectorSum) {
            	minVectorSum = vectorSum;
            }
            return;
        }
    	
    	// 모든 지렁이를 다 봤는데도 count가 부족하면 return
        if (idx == n) {
            return;
        }

        // idx 번째 지렁이를 그룹1
        isUsed[idx] = true;
        selectingWorm(idx + 1, count + 1);

        // idx 번째 지렁이를 그룹1에 안 넣는 경우
        isUsed[idx] = false;
        selectingWorm(idx + 1, count);
    }
}