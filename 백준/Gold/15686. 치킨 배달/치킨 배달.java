import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * N x N 도시
 * 0 : 빈칸
 * 1 : 집
 * 2 : 치킨집
 * 
 * 치킨 거리 : 집에서 가장 가까운 치킨집까지의 맨해튼 거리
 * 치킨집 중 M개를 골라서 나머지 폐업하고 도시의 치킨 거리가 가장 작게 될지 구하는 프로그램
 * 
 * 
 * 1. n, m 입력
 * 2. 도시 정보 입력
 *  2-1. 집 정보 입력
 *	2-2. 치킨집 정보 입력
 * 3. 치킨집 중 M개 선택 조합
 */
public class Main {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	static int n, m;
	static int minCost = Integer.MAX_VALUE;
	static int[][] arr;
	static int[] selectedChicken;
	static ArrayList<Point> chickenList, homeList;
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//1. n, m 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//2. 도시 정보 입력
		arr = new int[n][n];
		homeList = new ArrayList<>();
		chickenList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				//2-1. 집 정보 입력
				if(arr[i][j] == 1)
					homeList.add(new Point(i, j));
				//2-2. 치킨집 정보 입력
				else if(arr[i][j] == 2)
					chickenList.add(new Point(i, j));
			}
		}
		
		
		//3. 치킨집 중 M개 선택 조합
		selectedChicken = new int[m];
		makeCombination(0, 0);
		
		System.out.println(minCost);
	}
	
	public static void makeCombination(int start, int depth) {
		//m개 선택했으면 치킨 거리 계산해서 최소값 갱신
		if(depth == m) {
			getMinimumWeight();
			return;
		}
		
		for(int i = start; i < chickenList.size(); i++) {
			selectedChicken[depth] = i;
	        makeCombination(i + 1, depth + 1);
	    }
	}
	
	public static void getMinimumWeight() {
		//각 집에 대해서 선택된 치킨집까지의 최소 거리를 구하고 그걸 다 더하고 그걸 갱신
		int totalCost = 0;
		for(Point home : homeList) {
			int cost = Integer.MAX_VALUE;
			for(int i = 0; i < m; i++) {
				cost = Math.min(cost, calculateDistance(home, chickenList.get(selectedChicken[i])));
			}
			totalCost += cost;
		}
		
		minCost = Math.min(totalCost, minCost);
	}
	
	//맨해튼 거리 계산 메소드
	public static int calculateDistance(Point p1, Point p2) {
		int dx = Math.abs(p1.x - p2.x);
		int dy = Math.abs(p1.y - p2.y);
		
		return dx + dy;
	}
}