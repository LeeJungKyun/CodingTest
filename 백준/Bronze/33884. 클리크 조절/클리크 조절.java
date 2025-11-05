import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point>{
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.x == o.x) return Integer.compare(this.y, o.y);
			else return Integer.compare(this.x, o.x);
		}
	}
	static int n;
	static ArrayList<Point> firstBulletPoints, secondBulletPoints;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        firstBulletPoints = new ArrayList<>();
        secondBulletPoints = new ArrayList<>();
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	firstBulletPoints.add(new Point(x, y));
        }
        
        for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	secondBulletPoints.add(new Point(x, y));
        }
        
        Collections.sort(firstBulletPoints);
        Collections.sort(secondBulletPoints);
        
        int dx = secondBulletPoints.get(0).x - firstBulletPoints.get(0).x;
        int dy = secondBulletPoints.get(0).y - firstBulletPoints.get(0).y;
        
        System.out.printf("%d %d", dx, dy);
    }
}
