import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 
 * 이 문제는 N개의 점들이 모두 하나의 평면에 속해있냐고 물어보는거임
 * 
 * 점 세개를 골라서 법선 벡터를 구하고, N개의 점들에 대해서 고른 점들 중 하나를 고른 것과 구한 벡터와 법선벡터의 내적 값이 0이면 된다.
 * 
 * 1. N입력
 * 2. 파리들의 좌표 입력
 * 3. 파리들의 좌표 중 적절히 세개 골라서 법선 벡터를 구하기
 * 4. 나머지 좌표들에 대해서 법선벡터와 내적을 구하기
 * 		4-1. 하나라도 0이 아니면 바로 false
 */

public class Solution {
	//클래스 선언부
	static class Point {
		long x, y, z;

		public Point(long x, long y, long z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		//벡터 차
		Point subtract(Point other) {
			return new Point(x - other.x, y - other.y, z - other.z);
		}
		
		//외적
		Point crossProduct(Point other) {
			long cx = this.y * other.z - this.z * other.y;
			long cy = this.z * other.x - this.x * other.z;
			long cz = this.x * other.y - this.y * other.x;
			
			return new Point(cx, cy, cz);
		}
		
		// 내적
		long dot(Point other) {
			return this.x * other.x + this.y * other.y + this.z * other.z;
		}
	}
	
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n;
	static ArrayList<Point> pointList;
	
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//1. n입력
			n = Integer.parseInt(br.readLine());
			
			//2. 파리들의 좌표 입력
			pointList = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				
				pointList.add(new Point(x, y, z));
			}
			
			//3. 파리들의 좌표 중 세개 골라서 법선 벡터를 구하기
			Point[] basePoints = findThreePoints();
			boolean isValid = true;

			if (basePoints == null) {
				// 모든 점이 한 직선 위에 있다 -> 한 평면 못 만듦
				isValid = true;
			} else {
				Point A = basePoints[0];
				Point B = basePoints[1];
				Point C = basePoints[2];

				Point AB = B.subtract(A);
				Point AC = C.subtract(A);
				Point normalVector = AB.crossProduct(AC);

				// 나머지 점들 검사
				for (Point currentPoint : pointList) {
					if (currentPoint == A || currentPoint == B || currentPoint == C) continue;

					Point AP = currentPoint.subtract(A);
					if (AP.dot(normalVector) != 0) {
						isValid = false;
						break;
					}
				}
			}

			
			sb.append('#').append(tc).append(' ').append(isValid ? "TAK" : "NIE").append('\n');
		}
		System.out.println(sb);
	}
	
	public static Point[] findThreePoints() {
		int size = pointList.size();
		
		Point A = pointList.get(0);
		
		for (int i = 1; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				Point B = pointList.get(i);
				Point C = pointList.get(j);

				Point AB = B.subtract(A);
				Point AC = C.subtract(A);
				Point normal = AB.crossProduct(AC);

				// 벡터가 0이 아니면 세 점이 일직선이 아님
				if (!(normal.x == 0 && normal.y == 0 && normal.z == 0)) {
					return new Point[]{A, B, C};
				}
			}
		}
		return null;
	}


}