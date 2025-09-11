import java.io.*;
import java.util.*;

/**
 * @author 이정균
 * 그리드에 줄기세포는 정사각형 형태로 존재
 * <줄기세포>
 * 생명력이라는 수치
 * 초기에는 비활성 -> 생명력이 X인 줄기세포는 X시간 동안 비활성, X시간이 지나는 순간 활성 -> X시간동안 생존 -> X시간이 지나면 죽은 상태로 그 자리 그대로
 * 활성화되면 첫 1시간 동안 번식 -> 번식된 줄기 세포는 비활성
 * 이미 있는 곳에는 번식 X
 * 두 개 이상의 줄기세포가하 동시 번식하려고 하는 경우 생명력 수치가 높은 줄기세포가 차지
 * 
 * K시간 후 살아있는 줄기 세포(비활성 + 활성) 의 총 개수
 * 
 * 1. 초기 상태에서!! 세로 N, 가로 M, 시간 K 입력
 * 2. 초기의 줄기 세포 N개의 좌표 입력
 */

public class Solution {
	//클래스 선언부
	
	//줄기세포 클래스
	static class Cell implements Comparable<Cell> {
		//좌표, 생명력, 활성화가 될 시간, 죽을 시간
		int x, y, lifeTime, activateTime, deadTime;

		public Cell(int x, int y, int lifeTime, int activateTime, int deadTime) {
			this.x = x;
			this.y = y;
			this.lifeTime = lifeTime;
			this.activateTime = activateTime;
			this.deadTime = deadTime;
		}
		
		@Override
		public int compareTo(Cell o) {
			return Integer.compare(o.lifeTime, this.lifeTime);
		}
		
		@Override
		public boolean equals(Object o) {
		    if (this == o) return true;
		    if (o == null || getClass() != o.getClass()) return false;
		    Cell cell = (Cell) o;
		    return x == cell.x && y == cell.y;
		}

		@Override
		public int hashCode() {
		    return Objects.hash(x, y);
		}

		@Override
		public String toString() {
			return "세포 (x=" + x + ", y=" + y  + ")";
		}
		
		
	}
	//상수 선언부
	static final int MAX = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;

	//변수 선언부
	static int testCase;
	static int n, m, timer;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static HashSet<Cell> deactivateCell;
	static HashSet<Cell> activateCell;
	static HashSet<Cell> deadCell;
	static HashSet<String> usedCell;
	
	
	//입출력 선언부
	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		testCase = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= testCase; tc++) {
			//필요 변수 초기화
			deactivateCell = new HashSet<>();
			activateCell = new HashSet<>();
			deadCell = new HashSet<>();
			usedCell = new HashSet<>();
			
			//1. 초기상태의 세로 N, 가로M, 시간K 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			timer = Integer.parseInt(st.nextToken());
			
			//2. 초기의 줄기 세포들의 상태 입력
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					int lifeTime = Integer.parseInt(st.nextToken());
					if(lifeTime == 0) continue;
					//초기의 세포는 생명력 시간 후에 활성화, 2 * 생명력 시간 후에 죽을 시간
					Cell cell = new Cell(i, j, lifeTime, lifeTime, lifeTime * 2);
					//좌표의 중복사용을 방지하기 위해 usedCell HaseSet에 저장
					String cellCoordinate = coordinateToString(i, j);
					usedCell.add(cellCoordinate);
					deactivateCell.add(cell);
				}
			}
			
			for(int time = 0; time <= timer; time++) {
				growCell(time);
				//printGrid(time);
			}
			
			
			
			//3. 비활성화 + 활성 총 개수 구하기
			int count = 0;
			count += deactivateCell.size();
			count += activateCell.size();
			
			sb.append('#').append(tc).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void growCell(int currentTime) {
		
		//3. 활성화 되어있는 친구들에 대해서는 HashMap으로 관리해야함
		// 같은 좌표에 들어오려고 하면 생명력 수치가 높은 애가 들어가야함
		HashMap<String, Cell> nextDeactivateCellMap = new HashMap<>();
		for(Cell cell : activateCell) {
			if(cell.activateTime + 1== currentTime) {
				//각 줄기세포가 다음에 번식할 좌표에다가 번식 조져야함
				for(int dir = 0; dir < 4; dir++) {
					int nx = cell.x + dx[dir];
					int ny = cell.y + dy[dir];
							
					Cell newCell = new Cell(nx, ny, cell.lifeTime, currentTime + cell.lifeTime, currentTime + 2 * cell.lifeTime);
					String nextCoordinate = coordinateToString(nx, ny);
							
					//만약에 이미 이전시간에 쓴 좌표라면
					if(usedCell.contains(nextCoordinate)) continue;
							
					//기존에 그 좌표에 값이 있는 경우
					if (nextDeactivateCellMap.containsKey(nextCoordinate)) {
						Cell existingCell = nextDeactivateCellMap.get(nextCoordinate);
					    // lifeTime이 더 큰 셀로 골라서 넣기
					    if (newCell.lifeTime > existingCell.lifeTime) {
					    	nextDeactivateCellMap.put(nextCoordinate, newCell);
					    }
					} else {
						// 없으면 그냥 넣기
						nextDeactivateCellMap.put(nextCoordinate, newCell);
					}
				}
			}
		}
		
		//1. 일단 활성화 된 애들 중에서 죽을애들은 먼저 죽어야함 그냥 바로 죽여도 됨
		ArrayList<Cell> tempCellList = new ArrayList<>();
		for(Cell cell : activateCell) {
			//죽을때가 됐으면 deadCell로 옮기기
			if(cell.deadTime == currentTime) {
				deadCell.add(cell);
				tempCellList.add(cell);
			}
		}
		
		activateCell.removeAll(tempCellList);
		
		//2. 비활성화 -> 활성화 확인
		//activateTime이 currentTime이랑 같으면 활성화 될 시간임
		//바로 활성화 시키면 번식도 해서 그러면 안되니깐 nextActivateCell에 저장
		HashSet<Cell> nextActivateCell = new HashSet<>();
		tempCellList = new ArrayList<>();
		for(Cell cell : deactivateCell) {
			if(cell.activateTime == currentTime) {
				//다음에 활성화되니깐 추가해주고
				nextActivateCell.add(cell);
				//비활성화 에서 지우려고 저장
				tempCellList.add(cell);
			}
		}
		deactivateCell.removeAll(tempCellList);
		
		
		
		
		//번식이 끝났으니깐 줄기세포 정리 해줘야함
		//1. 활성화되어있는 셀에다가 다음에 활성화될 애들 추가
		for(Cell cell : nextActivateCell) {
			activateCell.add(cell);
		}
		
		//2. 비활성화셀에다가 nextDeactivateCellMap에 있는 Cell넣어주기
		for(Cell cell : nextDeactivateCellMap.values()) {
			deactivateCell.add(cell);
			usedCell.add(coordinateToString(cell.x, cell.y));
		}
		
	}
	
	public static void printGrid(int currentTime) {
	    // 좌표 평면의 범위를 충분히 크게 잡기 위해 전체 셀들의 x, y 좌표 중 최솟값/최댓값 구함
	    int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
	    int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

	    // 모든 셀들을 확인하여 그리드 범위 설정
	    for (Cell cell : deactivateCell) {
	        minX = Math.min(minX, cell.x);
	        maxX = Math.max(maxX, cell.x);
	        minY = Math.min(minY, cell.y);
	        maxY = Math.max(maxY, cell.y);
	    }
	    for (Cell cell : activateCell) {
	        minX = Math.min(minX, cell.x);
	        maxX = Math.max(maxX, cell.x);
	        minY = Math.min(minY, cell.y);
	        maxY = Math.max(maxY, cell.y);
	    }
	    for (Cell cell : deadCell) {
	        minX = Math.min(minX, cell.x);
	        maxX = Math.max(maxX, cell.x);
	        minY = Math.min(minY, cell.y);
	        maxY = Math.max(maxY, cell.y);
	    }

	    // 상하좌우 여유 공간 추가
	    minX -= 2;
	    maxX += 2;
	    minY -= 2;
	    maxY += 2;

	    // 2차원 배열로 셀 상태 저장
	    char[][] grid = new char[maxX - minX + 1][maxY - minY + 1];
	    for (char[] row : grid) Arrays.fill(row, '.');

	    for (Cell cell : deactivateCell) {
	        grid[cell.x - minX][cell.y - minY] = 'D';
	    }
	    for (Cell cell : activateCell) {
	        grid[cell.x - minX][cell.y - minY] = 'A';
	    }
	    for (Cell cell : deadCell) {
	        grid[cell.x - minX][cell.y - minY] = 'X';
	    }

	    System.out.printf("현재 시간: %d\n", currentTime);
	    for (int i = minX; i <= maxX; i++) {
	        for (int j = minY; j <= maxY; j++) {
	            System.out.print(grid[i - minX][j - minY]);
	        }
	        System.out.println();
	    }
	    System.out.println();
	}

	
	public static String coordinateToString(int x, int y) {
		return x + " " + y;
	}
}