import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * BBB를 EEE로 옮길거에용
 * 
 * 통나무의 상태를 확인하는 클래스도 있어야할거같음
 * 통나무의 중심점과 얘가 지금 가로인지 세로인지 확인해봐야함
 * start 통나무를 만들고, end통나무를 만들어
 * 
 * 그리고 start의 중심점이 end의 중심점으로 가야겠지?
 * 최단거리니깐 BFS 써야겠지?
 * 
 * BFS를 움직이는 조건이 좀 복잡할텐데 가면서 막 회전도 해보고 해야해
 * 
 * 더 이상 못간다고 멈추면 안되고 돌려서 큐에 넣어봐야지
 * 그러려면 visited는 3차원으로 만들어야겠지?
 * 
 * 구현 ㄱㄱ
 * 
 */
public class Main {
	static class TongNamu {
		Point center;
		int state;
		int moveCount;
		
		public TongNamu(Point center, int state, int moveCount) {
			this.center = center;
			this.state = state;
			this.moveCount = moveCount;
		}
	}
	
	static class Point implements Comparable<Point> {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.x == o.x) {
				return Integer.compare(this.y, o.y);
			} else return Integer.compare(this.x, o.x);
		}
	}
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static final int VERTICAL = 0;
	static final int HORIZONTAL = 1;
	
	static int n;
	static int minMoveCount;
	static char[][] arr;
	//3차원인 이유는 내가 거길 가로인 상태에서 방문했는지, 세로인 상태에서 방문했는지 알아야하니깐?
	static boolean[][][] visited;
	static ArrayList<Point> startPointList, endPointList;
	static TongNamu start, dest;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0,-1, 1};
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];
		
		startPointList = new ArrayList<>();
		endPointList = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < n; j++) {
				arr[i][j] = line.charAt(j);
				//시작 좌표들 저장해봐요
				if(arr[i][j] == 'B') startPointList.add(new Point(i, j));
				else if(arr[i][j] == 'E') endPointList.add(new Point(i, j));
			}
		}
		//입력 끝났음
		
		Collections.sort(startPointList);
		Collections.sort(endPointList);
		
		//다 정렬했으니깐 얘가 똑바로 서있는지, 안서있는지 확인하고 통나무 객체 생성
		//어떻게 정렬이 되든 일단 생겨먹은거로는 1번 인덱스의 값이 무조건 센터고 0번, 1번 비교해서 둘의 x좌표가 같으면 가로, 아니면 세로
		Point startFirstPoint = startPointList.get(0);
		Point startCenterPoint = startPointList.get(1);
		
		//x좌표가 같으면 가로
		if(startFirstPoint.x == startCenterPoint.x) {
			start = new TongNamu(startCenterPoint, HORIZONTAL, 0);
		}
		//아니면 세로
		else {
			start = new TongNamu(startCenterPoint, VERTICAL, 0);
		}
		
		//목적지 통나무도 마찬가지임
		Point endFirstPoint = endPointList.get(0);
		Point endCenterPoint = endPointList.get(1);
		
		//x좌표가 같으면 가로
		if(endFirstPoint.x == endCenterPoint.x) {
			dest = new TongNamu(endCenterPoint, HORIZONTAL, 0);
		}
		//아니면 세로
		else {
			dest = new TongNamu(endCenterPoint, VERTICAL, 0);
		}
		
		
		//사전 작업은 다 끝난 것 같으니 이제 BFS시작해보면 될듯?		
		visited = new boolean[n][n][2];
		ArrayDeque<TongNamu> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.center.x][start.center.y][start.state] = true;
		
		while(!queue.isEmpty()) {
			TongNamu curNamu = queue.poll();
			int curX = curNamu.center.x;
			int curY = curNamu.center.y;
			int curState = curNamu.state;
			
			//목적지의 통나무와 똑같이 생겼다? 드디어 탈출
			if(curX == dest.center.x && curY == dest.center.y && curState == dest.state) {
				System.out.println(curNamu.moveCount);
				return;
			}
			
			//가로인 경우
			if(curState == HORIZONTAL) {
				//1. 그냥 상하좌우로 이동하는 거 확인
				for(int dir = 0; dir < 4; dir++) {
					if(canMove(curX, curY, curState, dir)) {
						//일단 나무는 없는거임
						
						//방문했는지 봐줘야지
						int nx = curX + dx[dir];
						int ny = curY + dy[dir];
						
						if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
						
						//이 모양 그대로 방문한 적이 있으면 넘어가세요
						if(visited[nx][ny][curState]) continue;
						
						//이제 가도 됩니다
						visited[nx][ny][curState] = true;
						queue.add(new TongNamu(new Point(nx, ny), curState, curNamu.moveCount + 1));
					}
				}
				
				//2. 회전 하는 경우에는 자신의 좌표에서 회전한 곳의 방문 여부를 확인해야함
				//회전할때는 범위가 더 타이트하게 줄어야함
				if(curX < 1 || curX > n - 1 || curY < 1 || curY > n - 1) continue;
				if(canRotate(curX, curY)) {
					if(visited[curX][curY][VERTICAL]) continue;
					
					visited[curX][curY][VERTICAL] = true;
					queue.add(new TongNamu(new Point(curX, curY), VERTICAL, curNamu.moveCount + 1));
				}
			}
			
			//세로인 경우 위와 조금만 다르게 생각하면 됨
			else if(curState == VERTICAL) {
				//1. 그냥 상하좌우로 이동하는 거 확인
				for(int dir = 0; dir < 4; dir++) {
					if(canMove(curX, curY, curState, dir)) {
						//일단 나무는 없는거임
						
						//방문했는지 봐줘야지
						int nx = curX + dx[dir];
						int ny = curY + dy[dir];
						
						if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
						
						//이 모양 그대로 방문한 적이 있으면 넘어가세요
						if(visited[nx][ny][curState]) continue;
						
						//이제 가도 됩니다
						visited[nx][ny][curState] = true;
						queue.add(new TongNamu(new Point(nx, ny), curState, curNamu.moveCount + 1));
					}
				}
				
				//2. 회전 하는 경우에는 자신의 좌표에서 회전한 곳의 방문 여부를 확인해야함
				//회전할때는 범위가 더 타이트하게 줄어야함
				if(curX < 1 || curX > n - 1 || curY < 1 || curY > n - 1) continue;
				if(canRotate(curX, curY)) {
					if(visited[curX][curY][HORIZONTAL]) continue;
					
					visited[curX][curY][HORIZONTAL] = true;
					queue.add(new TongNamu(new Point(curX, curY), HORIZONTAL, curNamu.moveCount + 1));
				}
			}
		}
		//BFS끝났는데 여기까지 왔다? 실패임 ㅠㅠ
		System.out.println(0);
		
	}
	
	//중심의 x, y 좌표를 받고 방향이 주어졌을 때 움직일 수 있는지 없는지 여부를 리턴해줘야할듯
	public static boolean canMove(int x, int y, int state, int dir) {
		ArrayList<Point> list = new ArrayList<>();
		Point first, center, third;
		
		
		if(state == VERTICAL) {
			//수직으로 서있는데 X좌표가 n - 2 에서 아래로 가려고 하거나 1에서 위로 가려고 하면 안됩니다
			if((x == n - 2 && dir == DOWN) || (x == 1 && dir == UP)) return false;
			
			switch(dir) {
			//y좌표는 변동 없음

			case UP:
				first = new Point((x - 1) + dx[dir], y);
				center = new Point(x + dx[dir], y);
				third = new Point((x + 1) + dx[dir], y);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			//y좌표 변동 없음
			case DOWN:
				first = new Point((x - 1) + dx[dir], y);
				center = new Point(x + dx[dir], y);
				third = new Point((x + 1) + dx[dir], y);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			//x좌표 변동 없음
			case RIGHT:
				first = new Point(x - 1 , y + dy[dir]);
				center = new Point(x, y + dy[dir]);
				third = new Point(x + 1, y + dy[dir]);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			//x좌표 변동 없음
			case LEFT:
				first = new Point(x - 1 , y + dy[dir]);
				center = new Point(x, y + dy[dir]);
				third = new Point(x + 1, y + dy[dir]);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			}
			//여기서 list에 있는 좌표들의 유효성 검사
			// 이 함수는 그냥 이 좌표에 나무가 없는지만 확인할거에요
			
			for(Point point : list) {
				//좌표를 벗어나는지 확인
				if(!isInBound(point)) return false;
				
				//나무가 있는지 확인
				if(arr[point.x][point.y] == '1')
					return false;
			}
				
			return true;
		}
		//수평으로 누워 있을 때
		//x는 일정, y - 1, y , y + 1
		else if(state == HORIZONTAL) {
			list = new ArrayList<>();
			//수평으로 누워있는데 Y좌표가 n - 2 에서 오른쪽으로 가려고 하거나 1에서 왼쪽으로 가려고 하면 안됩니다
			if((y == n - 2 && dir == RIGHT) || (y == 1 && dir == LEFT)) return false;
			
			switch(dir) {
			//x좌표만 변동
			case UP:
				first = new Point(x + dx[dir], y - 1);
				center = new Point(x + dx[dir], y);
				third = new Point(x + dx[dir], y + 1);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			case DOWN:
			//x좌표만 변동
				first = new Point(x + dx[dir], y - 1);
				center = new Point(x + dx[dir], y);
				third = new Point(x + dx[dir], y + 1);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			case RIGHT:
			//y좌표만 변동
				first = new Point(x, (y - 1) + dy[dir]);
				center = new Point(x, y + dy[dir]);
				third = new Point(x, (y + 1) + dy[dir]);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			case LEFT:
			//y좌표만 변동
				first = new Point(x, (y - 1) + dy[dir]);
				center = new Point(x, y + dy[dir]);
				third = new Point(x, (y + 1) + dy[dir]);
				list.add(first);
				list.add(center);
				list.add(third);
				break;
			}
			
			//list유효성 검사
			for(Point point : list) {
				//좌표를 벗어나는지 확인
				if(!isInBound(point)) return false;
				
				//나무가 있는지 확인
				if(arr[point.x][point.y] == '1')
					return false;
			}
		}
		return true;
	}
	
	//내 기준 정사각형 을 확인해서 나무가 하나도 없어야함
	public static boolean canRotate(int x, int y) {
		for(int nx = x - 1; nx <= x + 1; nx++) {
			for(int ny = y - 1; ny <= y + 1; ny++) {
				if(isInBound(new Point(nx, ny))) {
					if(arr[nx][ny] == '1') return false;
				} else{
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isInBound(Point point) {
		return (0 <= point.x && point.x < n && 0 <= point.y && point.y < n);
	}
}