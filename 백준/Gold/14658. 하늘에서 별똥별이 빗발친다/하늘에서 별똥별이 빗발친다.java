import java.io.*;
import java.util.*;


public class Main {
	//클래스 선언부
	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		
		
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}



		@Override
		public int compareTo(Point o) {
			if(this.y == o.y)
				return Integer.compare(this.x, o.x);
			return Integer.compare(this.y, o.y);
		}
	}
	
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	//전체 범위의 가로, 세로 <= 500,000
	//별똥별 100개
	static int n, m, l, k;
	static ArrayList<Point> starList;
	static int maxCatch = MIN;
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n, m, l, k 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		//2. 떨어지는 별똥별들의 좌표 저장
		starList = new ArrayList<>();
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			starList.add(new Point(x, y));
		}
		
		Collections.sort(starList);		
		/**
		 * 한 별을 좌측 상단꼭짓점에 놨을 때, x, y 좌표가 해당 좌표보다 크면서 구한 거리가 사각형 내부에 존재하는지 판단?
		 */
		
		//X가 가로 -> n, Y가 세로 -> m
		for (Point px : starList) {
            for (Point py : starList) {
                int startX = px.x;
                int startY = py.y;
                int endX = startX + l;
                int endY = startY + l;

                int catchCount = 0;
                for (Point s : starList) {
                    if (startX <= s.x && s.x <= endX && startY <= s.y && s.y <= endY) {
                        catchCount++;
                    }
                }
                maxCatch = Math.max(maxCatch, catchCount);
            }
        }
		
		System.out.println(k - maxCatch);
	}
}