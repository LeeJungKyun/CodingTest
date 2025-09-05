import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 출구는 꼭짓점에 위치한 칸
 * 큐브가 생겨먹을 수 있는 경우는 한면이 4가지의 모양을 가질 수 있고 그게 5층이니깐 4^5 = 1024
 * 
 * 
 */
public class Main {
	static final int SIZE = 5;
	static final int STATUS = 4;
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	
	//좌표랑 이동 횟수 저장
	static class Point {
		int x, y, z, count;

		public Point(int x, int y, int z, int count) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.count = count;
		}
	}
	
	
	//층, x, y
	static int[][][] maze;
	static int[][][] currentMaze;
	static int[][][] currentFloor;
	static boolean[][][] visited;
	static int[] floorOrder;
	static boolean[] isUsed;
	static int minMoveCount = MAX;
	//순서대로 층 이동(상), 층 이동(하), 상, 하, 좌, 우
	static int[] dx = {0, 0, 1, -1, 0, 0};
	static int[] dy = {0, 0, 0, 0, -1, 1};
	static int[] dz = {1, -1, 0, 0, 0, 0};
	
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		//3차원 미로 입력
		maze = new int[SIZE][SIZE][SIZE];
		for(int floor = 0; floor < SIZE; floor++) {
			for(int row = 0; row < SIZE; row++) {
				st = new StringTokenizer(br.readLine());
				for(int col = 0; col < SIZE; col++) {
					maze[floor][row][col] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		//각 층의 모든 상태에 따라서 면을 정하고 탈출 시작
		for(int I = 0; I < STATUS; I++) {
			for(int II = 0; II < STATUS; II++) {
				for(int III = 0; III < STATUS; III++) {
					for(int IV = 0; IV < STATUS; IV++) {
						for(int V = 0; V < STATUS; V++) {
							int[] rotateDir = {I, II, III, IV, V};
							//각층을 돌려돌려 돌림판
							makeFloorState(rotateDir);
							//층 순서 정하고 순서 정하는 함수 내에서 탈출 함수 실행
							floorOrder = new int[SIZE];
							isUsed = new boolean[SIZE];
							makeFloorOrder(0);
						}
					}
				}
			}
		}
		
		System.out.println(minMoveCount == MAX ? -1 : minMoveCount);
	}
	
	public static void makeFloorOrder(int depth) {
		if(depth == SIZE) {
				// 현재 순서대로 currentMaze 구성하기
				currentMaze = new int[SIZE][SIZE][SIZE];
				for(int floor = 0; floor < SIZE; floor++) {
					currentMaze[floor] = currentFloor[floorOrder[floor]];
				}
				
				// 첫 번째 층의 시작점과 마지막 층의 도착점이 모두 통로일 경우만 탈출 시도
				// 0층과 4층의 꼭짓점들이 모두 통로일 경우만 탈출 시도
			    if (currentMaze[0][0][0] == 1 && currentMaze[SIZE - 1][SIZE - 1][SIZE - 1] == 1) {
			        startEscape(0, 0, SIZE - 1, SIZE - 1);
			    }
			    if (currentMaze[0][0][SIZE - 1] == 1 && currentMaze[SIZE - 1][SIZE - 1][0] == 1) {
			        startEscape(0, SIZE - 1, SIZE - 1, 0);
			    }
			    if (currentMaze[0][SIZE - 1][0] == 1 && currentMaze[SIZE - 1][0][SIZE - 1] == 1) {
			        startEscape(SIZE - 1, 0, 0, SIZE - 1);
			    }
			    if (currentMaze[0][SIZE - 1][SIZE - 1] == 1 && currentMaze[SIZE - 1][0][0] == 1) { // 수정된 부분
			        startEscape(SIZE - 1, SIZE - 1, 0, 0);
			    }
				
				return;
		}
		
		for(int i = 0; i < SIZE; i++) {
			if(!isUsed[i]) {
				isUsed[i] = true;
				floorOrder[depth] = i;
				makeFloorOrder(depth + 1);
				isUsed[i] = false;
			}
		}
		return;
	}
	
	//탈출하고싶어요
	//1이 통로 0이 벽 
	//최단경로니깐 일단 BFS로 하긴 할게요
	//근데 중요한거는 시작한 곳의 면을 확인해서 반대 면에서 나와야함
	//IMPORTANT : currentMaze만 쓰세요!!
	public static void startEscape(int startX, int startY, int endX, int endY) {
		//0층의 꼭짓점들을 확인해서 1이면 큐에 넣고 시작
		ArrayDeque<Point> queue = new ArrayDeque<>();
		visited = new boolean[SIZE][SIZE][SIZE];
		
		queue.add(new Point(startX, startY, 0, 0));
		visited[0][startX][startY] = true;
		
		//BFS 시작
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			//끝나는 조건 : 마지막 층이고, 내가 나가기로 했던 꼭짓점 이면 최단경로로 탈출한거임
			if(cur.z == SIZE - 1 && (cur.x == endX && cur.y == endY)){
				minMoveCount = Math.min(cur.count, minMoveCount);
				return;
			}
			
			if(cur.count >= minMoveCount)
				continue;
			
			for(int dir = 0; dir < 6; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				int nz = cur.z + dz[dir];
				
				//범위밖 확인
				if(nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE || nz < 0 || nz >= SIZE) continue;
				
				//방문여부 및 갈 수 있는 곳인지 확인
				if(visited[nz][nx][ny] || currentMaze[nz][nx][ny] == 0) continue;
				
				//이제 확인 다 했음
				visited[nz][nx][ny] = true;
				queue.add(new Point(nx, ny, nz, cur.count + 1));
			}
		}
	}
	
	//각층을 돌리기 이거 끝나고 탈출 할거임
	//0: 그대로
	//1: 1회전 (x, y) -> (y, (SIZE - 1) - x)
	//2: 2회전 (x, y) -> ((SIZE - 1) - x, (SIZE - 1) - y)
	//3: 3회전(x, y) -> ((SIZE - 1) - y, x)
	public static void makeFloorState(int[] rotateDir) {
		currentFloor = new int[SIZE][SIZE][SIZE];
		//각 층에 대해서 currentMaze를 채우기
		for(int floor = 0; floor < SIZE; floor++) {
			switch(rotateDir[floor]) {
			//그대로
			case 0:
				for(int row = 0; row < SIZE; row++) {
					for(int col = 0; col < SIZE; col++) {
						currentFloor[floor][row][col] = maze[floor][row][col];
					}
				}
				break;
			//1: 1회전 (x, y) -> (y, (SIZE - 1) - x)
			case 1:
				for(int row = 0; row < SIZE; row++) {
					for(int col = 0; col < SIZE; col++) {
						currentFloor[floor][col][(SIZE - 1) - row] = maze[floor][row][col];
					}
				}
				break;
			//2: 2회전 (x, y) -> ((SIZE - 1) - x, (SIZE - 1) - y)
			case 2:
				for(int row = 0; row < SIZE; row++) {
					for(int col = 0; col < SIZE; col++) {
						currentFloor[floor][(SIZE - 1) - row][(SIZE - 1) - col] = maze[floor][row][col];
					}
				}
				break;
			//3: 3회전(x, y) -> ((SIZE - 1) - y, x)
			case 3:
				for(int row = 0; row < SIZE; row++) {
					for(int col = 0; col < SIZE; col++) {
						currentFloor[floor][(SIZE - 1) - col][row] = maze[floor][row][col];
					}
				}
				break;
			}
		}
	}
}